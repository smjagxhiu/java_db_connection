package sjagxhiu.DbConn.jdbi;

import java.util.List;
import java.util.Set;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import sjagxhiu.DbConn.model.Data;
import sjagxhiu.DbConn.model.Phone;
import sjagxhiu.DbConn.model.User;

public interface UserDAO {
	
	@SqlUpdate(
"INSERT INTO user VALUES (NULL,:fullname, :username, :password, :email, :dob) ")
	@GetGeneratedKeys("id")
	public long insert(@BindBean User user);
	
	@SqlBatch(
"INSERT INTO phone VALUES (NULL, :p.phone, :p.type, :u_id)")
	public void insert(@BindBean("p") List<Phone> p, 
			@Bind("u_id") long u_id);
	
	@SqlQuery(Data.SELECT_QUERY)
	@RegisterRowMapper(UserRowMapper.class)
	public Set<User> getAll();
	
	@SqlQuery(Data.SELECT_QUERY + " where user_id = :id")
	@RegisterRowMapper(UserRowMapper.class)
	public User getById(@Bind("id") long userId);
}



