package sjagxhiu.DbConn.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import sjagxhiu.DbConn.jaxb.DateAdapter;

@Entity(name = "User")
@Table(name = "user")
@NamedQuery(name = "findById", 
		query = "select u from User u where u.id = :id")
@XmlRootElement(name = "user")
//@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(propOrder = {Data.User.FULLNAME, 
		Data.User.USERNAME, Data.User.PASSWORD,Data.User.EMAIL,
		Data.User.DOB,"phones"} )
@JsonRootName(value = "user")
@JsonPropertyOrder(value = {"id", Data.User.FULLNAME, 
		Data.User.USERNAME, Data.User.PASSWORD,Data.User.EMAIL,
		Data.User.DOB,"phones"})
public class UserJPA {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Data.User.ID)
	@JsonProperty(value = "id")
	private long id;
	
	@Column(name = Data.User.FULLNAME)
	private String fullname;
	
	@Column(name = Data.User.USERNAME)
	private String username;
	
	@Column(name = Data.User.PASSWORD)
	private String password;
	
	@Column(name = Data.User.EMAIL)
	private String email;
	
	
	@Column(name = Data.User.DOB)
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private Timestamp dob;
	
	
	@OneToMany(cascade = CascadeType.ALL, 
			orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinColumn(name = Data.Phone.USERID, 
		referencedColumnName = Data.User.ID,nullable = false)
	@JsonProperty(value = "contact")
	private List<PhoneJPA> phones;
	
	public UserJPA() {
		super();
		phones = new ArrayList<PhoneJPA>();
	}
	
	
	
	
	
	public UserJPA(String fullname, String username, String password, String email) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		phones = new ArrayList<PhoneJPA>();
	}



	@XmlAttribute(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@XmlElement(name = Data.User.FULLNAME)
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
	
	@XmlJavaTypeAdapter(DateAdapter.class)
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
	
	public void addPhone(PhoneJPA phone) {
		phones.add(phone);
	}

	@XmlElementWrapper(name="contact", required = false)
	@XmlElement(name = "phone")
	public List<PhoneJPA> getPhones() {
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
		if (!(obj instanceof UserJPA))
			return false;
		UserJPA other = (UserJPA) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
