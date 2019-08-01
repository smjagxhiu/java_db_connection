package sjagxhiu.DbConn.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class User {
	
	
	private long id;
	private String fullname;
	private String username;
	private String password;
	private String email;
	private Timestamp dob;
	private List<Phone> phones;
	
	public User() {
		super();
		phones = new ArrayList<Phone>();
	}
	
	
	
	
	
	public User(String fullname, String username, String password, String email) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		phones = new ArrayList<Phone>();
	}




	@ColumnName("user_id")
	public long getId() {
		return id;
	}
	@ColumnName("user_id")
	public void setId(long id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@ColumnName(Data.User.DOB)
	public Timestamp getDob() {
		return dob;
	}

	public void setDob(Timestamp timestamp) {
		this.dob = timestamp;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullname=" + fullname + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", timestamp=" + dob + "]";
	}
	
	public void addPhone(Phone phone) {
		phones.add(phone);
	}


	public List<Phone> getPhones() {
		return phones;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
