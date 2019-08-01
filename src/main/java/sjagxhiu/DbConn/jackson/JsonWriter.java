package sjagxhiu.DbConn.jackson;

import java.io.File;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sjagxhiu.DbConn.JPA.UserORM;
import sjagxhiu.DbConn.model.Data;
import sjagxhiu.DbConn.model.UserJPA;
import sjagxhiu.DbConn.model.Users;

public class JsonWriter {
	
	public static String writeAndGetString(File file, Object o) 
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		om.writerWithDefaultPrettyPrinter().writeValue(file,o);
		String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
		System.out.println(json);
		return json;
	}

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("jpa_unit");
		EntityManager em = emf.createEntityManager();
		UserORM orm = new UserORM(em);
		System.out.println();
		//UserJPA user = orm.getById(2);
		Users users = new Users();
		users.setUsers(orm.getAll());
		try {
			writeAndGetString(new File(Data.PATH+"/users.json"),
					users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();

	}

}
