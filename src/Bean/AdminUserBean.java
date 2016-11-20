package Bean;

public class AdminUserBean {
	public AdminUserBean(){
		
	}
	 private String LoginID; private String Pwd; private String Name; private String Authorizations;
	
	public String getLoginID() {
		return LoginID;
	}
	public void setLoginID(String loginID) {
		LoginID = loginID;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAuthorizations() {
		return Authorizations;
	}
	public void setAuthorizations(String authorizations) {
		Authorizations = authorizations;
	}
}
