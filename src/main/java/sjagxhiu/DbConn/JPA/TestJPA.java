package sjagxhiu.DbConn.JPA;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sjagxhiu.DbConn.model.PhoneJPA;
import sjagxhiu.DbConn.model.UserJPA;


public class TestJPA {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("jpa_unit");
		EntityManager em = emf.createEntityManager();
		UserORM orm = new UserORM(em);
		orm.insert(create());
		System.out.println();
		//print(orm.getAll());
		System.out.println(orm.getById(2));
		em.close();

	}
	
	public static void print(Collection<UserJPA> users) {
		for (UserJPA user : users) {
			System.out.println(user);
			for (PhoneJPA p : user.getPhones())
				System.out.println("\t" + p);
		}
		// System.out.println();
	}
    
    private static UserJPA create() {
    	int i = 1 + (int)(Math.random() * 30);
    	UserJPA user = new UserJPA("test"+i,"test"+i,"TeSt123",
    			"test"+i+"@test.com");
    	long time = new GregorianCalendar(1987, 10, i).getTimeInMillis();
    	user.setDob(new Timestamp(time));
    	String phone1 = "",phone2 = "";
    	for (int j = 0; j < 4; j++) {
    		phone1 += i ;
    		phone2 += (i + 3);
    	}
    	user.addPhone(new PhoneJPA(phone1,"Home"));
    	user.addPhone(new PhoneJPA(phone2,"Mobile"));
    	
    	return user;
    }

}
