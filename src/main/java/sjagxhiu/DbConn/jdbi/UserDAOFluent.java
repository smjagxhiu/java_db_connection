package sjagxhiu.DbConn.jdbi;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleCallback;
import org.jdbi.v3.core.HandleConsumer;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Batch;
import org.jdbi.v3.core.statement.PreparedBatch;

import sjagxhiu.DbConn.model.Data;
import sjagxhiu.DbConn.model.Phone;
import sjagxhiu.DbConn.model.User;

public class UserDAOFluent {
	
	private Jdbi jdbi;

	public UserDAOFluent(Jdbi jdbi) {
		super();
		this.jdbi = jdbi;
	}
	
	public List<String> getUsernames() throws Exception{
		List<String> usernames = jdbi.withHandle(
				new HandleCallback<List<String>, Exception>() {

					public List<String> withHandle(Handle handle) throws Exception {
						return handle.createQuery("select username from user")
						.mapTo(String.class)
						.list();
					}
		});
		System.out.println(usernames);
		return usernames;
	}
	
	public List<User> getUsersWithoutPhone() throws Exception{
		List<User> usernames = jdbi.withHandle(
				new HandleCallback<List<User>, Exception>() {

					public List<User> withHandle(Handle handle) throws Exception {
						return handle.createQuery("select * from user")
						.mapToBean(User.class)
						.list();
					}
		});
		//System.out.println(usernames);
		return usernames;
	}
	
	public Set<User> getUsers() throws Exception{
		System.out.println("------------");
		Set<User> users = jdbi.withHandle(
				new HandleCallback<Set<User>, Exception>() {
					Collector<User,?,Set<User>> c = Collectors.toSet();
					public Set<User> withHandle(Handle handle) throws Exception {
						return handle.createQuery(Data.SELECT_QUERY)
						.map(new UserRowMapper())
						.collect(c);
					}
		});
		return users;
	}
	
	public User getUserById(final long id) throws Exception{
		System.out.println("------------");
		User users = jdbi.withHandle(
				new HandleCallback<User, Exception>() {
					public User withHandle(Handle handle) throws Exception {
						return handle.createQuery(Data.SELECT_QUERY + 
								" where user_id = :id")
						.bind("id",id)
						.map(new UserRowMapper())
						.findFirst().get();
					}
		});
		return users;
	}
	
	public void insert(final User user) throws Exception {
		System.out.println(user);
		jdbi.useHandle(new HandleConsumer<Exception>() {

			public void useHandle(Handle handle) throws Exception {
				long userId = handle.createUpdate(Data.insertUserSQL2)
				.bindBean(user)
				.executeAndReturnGeneratedKeys(Data.User.ID)
				.mapTo(Long.class)
				.findOnly();		
				System.out.println(userId);
				PreparedBatch batch = 
						handle.prepareBatch(Data.insertPhoneSQL2);
				for (Phone p : user.getPhones()) {			
					batch.bindBean(p);
					batch.bind(Data.Phone.USERID,userId);				
				}
				batch.execute();
					
			}
		});
	}
	

}
