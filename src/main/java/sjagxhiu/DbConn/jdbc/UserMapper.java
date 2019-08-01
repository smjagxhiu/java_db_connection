package sjagxhiu.DbConn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import sjagxhiu.DbConn.model.Data;
import sjagxhiu.DbConn.model.Phone;
import sjagxhiu.DbConn.model.User;

public class UserMapper {
	
	private Connection conn;
	
	public UserMapper(Connection conn) {
		this.conn = conn;
	}
	
	public List<User> getAllUsers() throws SQLException{
		Statement st = conn.createStatement();
		List<User> users = new ArrayList<User>();
		ResultSet rs = st.executeQuery(
				Data.SELECT_QUERY);
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getLong(Data.User.ID));
			if (!users.contains(user)) {
				user.setFullname(rs.getString(Data.User.FULLNAME));
				user.setUsername(rs.getString(Data.User.USERNAME));
				user.setEmail(rs.getString(Data.User.EMAIL));
				user.setPassword(rs.getString(Data.User.PASSWORD));
				user.setDob(rs.getTimestamp(Data.User.DOB));
				users.add(user);
			}
			long phoneId = rs.getLong(Data.Phone.ID);
			if (phoneId > 0 ) {
				Phone phone = new Phone( 
						rs.getString(Data.Phone.PHONE),
								rs.getString(Data.Phone.TYPE) );
				phone.setId(phoneId);
				users.get(users.indexOf(user)).addPhone(phone);
			}
		}
		st.close();
		return users;
	}
	
	public void insert(User user) throws SQLException {
		System.out.println(user);
		PreparedStatement ps = 
				conn.prepareStatement(Data.insertUserSQL(),
						Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getFullname());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getEmail());
		ps.setTimestamp(5, user.getDob());
		int affectedRows = ps.executeUpdate();
		if (affectedRows > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				long userId = rs.getLong(1);
				user.setId(userId);
				//System.out.println(userId);
				ps = conn.prepareStatement(Data.insertPhoneSQL);
				for (Phone p : user.getPhones()) {
					System.out.println(p);
					System.out.println(userId);					
					ps.setString(1, p.getPhone());
					ps.setString(2, p.getType());
					ps.setLong(3, userId);
					ps.addBatch();
				}
				ps.executeBatch();
			}
		}
		ps.close();
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC",
					"Java", "java");
			UserMapper um = new UserMapper(conn);
			System.out.println();
			um.insert(createUser());
			//List<User> users = um.getAllUsers();
			///print(users);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void print(List<User> users) {
		for (User user : users) {
			System.out.println(user);
			for (Phone p : user.getPhones())
				System.out.println("\t" + p);
		}
		// System.out.println();
	}
    
    private static User createUser() {
    	int i = 1 + (int)(Math.random() * 30);
    	User user = new User("test"+i,"test"+i,"TeSt123",
    			"test"+i+"@test.com");
    	long time = new GregorianCalendar(1987, 10, i).getTimeInMillis();
    	user.setDob(new Timestamp(time));
    	String phone1 = "",phone2 = "";
    	for (int j = 0; j < 4; j++) {
    		phone1 += i ;
    		phone2 += (i + 3);
    	}
    	user.addPhone(new Phone(phone1,"Home"));
    	user.addPhone(new Phone(phone2,"Mobile"));
    	
    	return user;
    }

}
