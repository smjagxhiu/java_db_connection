package sjagxhiu.DbConn.model;

public class Phone {

	private long id;
	private String phone;
	private String type;
	
	
	public Phone() {
		super();
	}

	public Phone(String phone, String type) {
		super();
		this.phone = phone;
		this.type = type;
	}
	
	

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
		if (!(obj instanceof Phone))
			return false;
		Phone other = (Phone) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
