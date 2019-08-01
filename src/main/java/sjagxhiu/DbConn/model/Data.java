package sjagxhiu.DbConn.model;

public class Data {
	
	public class User{
		public static final String ID = "user_id";
		public static final String FULLNAME = "fullname";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String EMAIL = "email";
		public static final String DOB = "dob";	
	}
	
	public class Phone{
		public static final String ID = "phone_id";
		public static final String PHONE = "phone";
		public static final String TYPE = "type";
		public static final String USERID = "u_id";
	}
	
	public static final String SELECT_QUERY = 
			"select * from user left join phone on user.user_id = phone.u_id ";
	
	public static final String insertUserSQL = 
			String.format(
					"INSERT INTO user (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
					Data.User.FULLNAME,Data.User.USERNAME,Data.User.PASSWORD, 
					Data.User.EMAIL,Data.User.DOB);
	
	public static final String insertUserSQL2 = String.format(
				"INSERT INTO user (%s,%s,%s,%s,%s) "
				+ "VALUES (:%s,:%s,:%s,:%s,:%s)",
				Data.User.FULLNAME,Data.User.USERNAME,Data.User.PASSWORD, 
				Data.User.EMAIL,Data.User.DOB,Data.User.FULLNAME,
				Data.User.USERNAME,Data.User.PASSWORD, 
				Data.User.EMAIL,Data.User.DOB);
	
	
	public static final String insertPhoneSQL
		=   String.format("INSERT INTO phone (%s,%s,%s) VALUES (?,?,?)"
				,Data.Phone.PHONE,Data.Phone.TYPE,Data.Phone.USERID);
	
	
	
	
	public static final String insertPhoneSQL2 =  String.format(
				"INSERT INTO phone (%s,%s,%s) VALUES (:%s,:%s,:%s)"
				,Data.Phone.PHONE,Data.Phone.TYPE,Data.Phone.USERID,
				Data.Phone.PHONE,Data.Phone.TYPE,Data.Phone.USERID);
	
	public static final String JPA_UNIT = "jpa_unit" ;
	
	public static final String PROJECT_PATH = 
			System.getProperty("user.dir");
	

	public static String insertUserSQL() {
		return String.format(
				"INSERT INTO user (%s,%s,%s,%s,%s) VALUES (?,?,?,?,?)",
				Data.User.FULLNAME,Data.User.USERNAME,Data.User.PASSWORD, 
				Data.User.EMAIL,Data.User.DOB);
	}
	
	public static String insertUserSQL2() {
		return String.format(
				"INSERT INTO user (%s,%s,%s,%s,%s) "
				+ "VALUES (:%s,:%s,:%s,:%s,:%s)",
				Data.User.FULLNAME,Data.User.USERNAME,Data.User.PASSWORD, 
				Data.User.EMAIL,Data.User.DOB,Data.User.FULLNAME,
				Data.User.USERNAME,Data.User.PASSWORD, 
				Data.User.EMAIL,Data.User.DOB);
	}
	
	public static String insertPhoneSQL() {
		return String.format(
				"INSERT INTO phone (%s,%s,%s) VALUES (?,?,?)"
				,Data.Phone.PHONE,Data.Phone.TYPE,Data.Phone.USERID);
	}
	
	public static String insertPhoneSQL2() {
		return String.format(
				"INSERT INTO phone (%s,%s,%s) VALUES (:%s,:%s,:%s)"
				,Data.Phone.PHONE,Data.Phone.TYPE,Data.Phone.USERID,
				Data.Phone.PHONE,Data.Phone.TYPE,Data.Phone.USERID);
	}
	
	public static final String PATH = 
			System.getProperty("user.dir");

}
