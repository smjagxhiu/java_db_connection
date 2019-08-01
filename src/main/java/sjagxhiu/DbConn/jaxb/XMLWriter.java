package sjagxhiu.DbConn.jaxb;

import java.io.File;
import java.io.StringWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import sjagxhiu.DbConn.JPA.UserORM;
import sjagxhiu.DbConn.model.Data;
import sjagxhiu.DbConn.model.Users;

public class XMLWriter {
	
	public static String writeAndGetString(File file, Object o) 
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(o.getClass());	
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, 
				true);
		marshaller.marshal(o, System.out); 
		marshaller.marshal(o, file);
		StringWriter sw = new StringWriter();
		marshaller.marshal(o, sw);
		return sw.toString();
	}
	
	public static void main(String[] args) {
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("jpa_unit");
		EntityManager em = emf.createEntityManager();
		UserORM orm = new UserORM(em);
		System.out.println();
		//print(orm.getAll());
		//UserJPA user = orm.getById(2);
		Users users = new Users();
		users.setUsers(orm.getAll());
		try {
			writeAndGetString(new File(Data.PATH+"/users.xml"),
					users);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.close();
	}

}
