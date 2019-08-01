package sjagxhiu.DbConn.jdbi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import sjagxhiu.DbConn.model.Data;
import sjagxhiu.DbConn.model.Phone;
import sjagxhiu.DbConn.model.User;

public class UserRowMapper implements RowMapper<User> {
	
	private User user;

	public User map(ResultSet rs, StatementContext ctx) throws SQLException {
		long userId = rs.getLong(Data.User.ID);
		if (rs.getRow() == 1 || userId != user.getId()) {
			user = new User();
			user.setId(userId);
			user.setFullname(rs.getString(Data.User.FULLNAME));
			user.setUsername(rs.getString(Data.User.USERNAME));
			user.setEmail(rs.getString(Data.User.EMAIL));
			user.setPassword(rs.getString(Data.User.PASSWORD));
			user.setDob(rs.getTimestamp(Data.User.DOB));
		}
		long phoneId = rs.getLong(Data.Phone.ID);
		//System.out.println(user + " "+ phoneId);
		if (phoneId > 0 ) {
			Phone phone = new Phone( 
					rs.getString(Data.Phone.PHONE),
							rs.getString(Data.Phone.TYPE) );
			phone.setId(phoneId);
			user.addPhone(phone);
		}
		return user;
	}

}
