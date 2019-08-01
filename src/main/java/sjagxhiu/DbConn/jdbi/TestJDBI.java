package sjagxhiu.DbConn.jdbi;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Set;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import sjagxhiu.DbConn.model.Phone;
import sjagxhiu.DbConn.model.User;

public class TestJDBI {

	public static void main(String[] args) {
		
		Jdbi jdbi = Jdbi.create
("jdbc:mysql://localhost:3306/users?useSSL=false&serverTimezone=UTC", 
		"Java","java");
		
		System.out.println("Connectedd...");

		//fluentAPI(jdbi);
		objectAPI(jdbi);

	}
	
	private static void objectAPI(Jdbi jdbi) {
		jdbi.installPlugin(new SqlObjectPlugin());
		UserDAO udao = jdbi.onDemand(UserDAO.class);
		User user = create();
		
		long userId = udao.insert(user);
		user.setId(userId);
		System.out.println(user);
		udao.insert(user.getPhones(), userId);
		
		Set<User> users = udao.getAll();
		print(users);
		user = udao.getById(2);
		System.out.println(user);
		for (Phone p : user.getPhones())
			System.out.println("\t" + p);
	}

	private static void fluentAPI(Jdbi jdbi) {
		UserDAOFluent udao = new UserDAOFluent(jdbi);
		try {
			//udao.getUsernames();
			//Set<User> users = udao.getUsers();
			//print(users);
			//User user = udao.getUserById(4);
			//System.out.println(user);
			udao.insert(create());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void print(Collection<User> users) {
		for (User user : users) {
			System.out.println(user);
			for (Phone p : user.getPhones())
				System.out.println("\t" + p);
		}
		// System.out.println();
	}
    
    private static User create() {
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
