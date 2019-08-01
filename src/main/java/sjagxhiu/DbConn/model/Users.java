package sjagxhiu.DbConn.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "users")
@JsonRootName(value = "users")
public class Users {
	
	
	private List<UserJPA> users;

	@XmlElement(name = "user")
	public List<UserJPA> getUsers() {
		return users;
	}

	public void setUsers(List<UserJPA> users) {
		this.users = users;
	}
	
	

}
