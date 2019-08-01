package sjagxhiu.DbConn.JPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sjagxhiu.DbConn.model.UserJPA;

public class UserORM {
	
	private EntityManager em;

	public UserORM(EntityManager em) {
		super();
		this.em = em;
	}
	
	public UserJPA insert(UserJPA user) {
		System.out.println(user);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return user;
	}
	
	public List<UserJPA> getAll(){
		em.getTransaction().begin();
		List<UserJPA> users = 
				em.createQuery("select u from User u",
						UserJPA.class).getResultList();
		em.getTransaction().commit();
		return users;
	}
	
	public UserJPA getById(long id){
		em.getTransaction().begin();
		Query query = em.createNamedQuery(
				"findById").setParameter("id", id);
		UserJPA user = (UserJPA) query.getSingleResult();
		em.getTransaction().commit();
		return user;
	}

	
	
	
}
