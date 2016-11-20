package DAO;

import java.sql.ResultSet;

import DB.sqlDB;

public class LoginDAO {

	public LoginDAO(){
		
	}
	
	/**
	 * 当 pwd != null 时验证登陆,当 pwd = null 时，应用于查询账户是否存在
	 * @param LoginID
	 * @param pwd 
	 * @return 返回1代表有记录，返回0代表无记录，返回2代表出错
	 */
	public int selectAdminUser(String  LoginID,String pwd) {
		try {
			
			sqlDB objsqlDB = new sqlDB();
			String _sql = "select * from Adminuser where LoginID= '" + LoginID + "' ";
			if(pwd != null){
			_sql = _sql + "and pwd='" + pwd + "'";
			}
			
			
			ResultSet rs = objsqlDB.select_2(_sql);
			if(rs.next()){
				return 1;
			}else{
			return 0;
			}
			

		} catch (Exception ex) {
			return 2;
		}
	}
}
