package sjagxhiu.DbConn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

@Entity(name = "Phone")
@Table(name = "phone")
public class PhoneJPA implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Data.Phone.ID)
	private long id;
	@Column(name = Data.Phone.PHONE)
	private String phone;
	@Column(name = Data.Phone.TYPE)
	private String type;
	
	
	public PhoneJPA() {
		super();
	}

	public PhoneJPA(String phone, String type) {
		super();
		this.phone = phone;
		this.type = type;
	}
	
	
	@XmlAttribute(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Phone [id=" + id + ", phone=" + phone + ", type=" + type + "]";
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
		if (!(obj instanceof PhoneJPA))
			return false;
		PhoneJPA other = (PhoneJPA) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
