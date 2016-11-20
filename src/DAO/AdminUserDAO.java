package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.AdminUserBean;
import DB.sqlDB;
import zjxTool.Security;

public class AdminUserDAO {

	/**
	 * 对user表进行插入
	 * @param objAdminUserBean
	 * @return 返回1表示插入成功,0表示不成功
	 */
	public int AddAdminUser(AdminUserBean objAdminUserBean) {
		
		sqlDB objsqlDB = new sqlDB();
		Security objSecurity = new Security();
		String _sql = "insert into Adminuser (LoginID, Pwd, Name, Authorizations) Values (?,?,?,?)";
		String LoginID = objAdminUserBean.getLoginID();
		String Pwd = objSecurity.SSL_MD5(objAdminUserBean.getPwd());
		String Name = objAdminUserBean.getName();
		String Authorizations = objAdminUserBean.getAuthorizations();

		String[] a = { LoginID, Pwd, Name, Authorizations };

		if (objsqlDB.insert(_sql, a) == true) {
			System.out.println("baocun");
			return 1;
		}
		else{
		return 0;
		}
	}
	
	/**
	 * 对AdminUser表进行更新操作
	 * @param objAdminUserBean
	 * @return 返回1代表成功，0表示失败
	 */
	public int updateAdminUser(AdminUserBean objAdminUserBean) {
		
		sqlDB objsqlDB = new sqlDB();
		Security objSecurity = new Security();
		String _sql = "update Adminuser set  Pwd  = ?, Name  = ?, Authorizations  = ? Where LoginID = ?";
		String LoginID = objAdminUserBean.getLoginID();
		String Pwd = objSecurity.SSL_MD5(objAdminUserBean.getPwd());
		String Name = objAdminUserBean.getName();
		String Authorizations = objAdminUserBean.getAuthorizations();
		String[] a = {  Pwd, Name, Authorizations ,LoginID};
		System.out.println(_sql);
		if (objsqlDB.update_2(_sql, a) == 1) {
			
			return 1;
		}
		else{
		return 0;
		}
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
	
	/**
	 * 使用存储过程查询User表，并进行分页处理
	 * @param pageindex 当前为第几页
	 * @param pagesize 每页的记录数
	 * @return 返回结果集，使用json解析
	 */
	public ResultSet selectuserinfo(String pageindex,String pagesize,String where){
	String _Sql = "execute up_Pagination @Tables ='Adminuser', @PrimaryKey ='LoginID', @Sort='LoginID desc', @PageSize=" + pagesize + ", @Fields ='*', @Group='', @CurrentPage = "  + pageindex + ",  @Filter=' 1=1 ";
			
			if(!where.isEmpty() || !"".equals(where) || where != null)
			{
				_Sql +=  " and LoginID + Name  like ''%" + where + "%'' ";
			}
			_Sql += "'";
		sqlDB objsqlDB = new sqlDB();
		//System.out.println(_Sql);
		return objsqlDB.select_2(_Sql);
	
	}
	
	/**
	 * 获得表的总记录数
	 * @param where
	 * @return 记录数
	 */
	public int GetTotal(String where)
	{
		try{
			String _Sql = "select Count(*) AS TotalCount from Adminuser  where 1=1";
			if(!where.isEmpty() || !"".equals(where)){
				_Sql += "and LoginID + Name  like '%" + where + "%' ";
			}
			//System.out.println(_Sql);
			sqlDB objsqlDB = new sqlDB();
			ResultSet objRS = objsqlDB.select_2(_Sql);
	
			if(objRS.next())
			{
				return objRS.getInt("TotalCount");
			}
			else
			{
				return 0;
			}
		}catch(Exception ex)
		{
			return 0;
		}
	}
	/**
	 * 根据账号查询权限
	 * @param LoginID
	 * @param pwd
	 * @return 成功返回权限数值0-3，逐级增加，失败返回0，错误返回2
	 */
	public int selectAuthorizations(String  LoginID) {		
		sqlDB objsqlDB = new sqlDB();
		String _sql = "select * from Adminuser where LoginID= '" + LoginID + "' ";
		ResultSet rs = objsqlDB.select_2(_sql);
		try {
			if(rs.next()){
				return rs.getInt("Authorizations");
			}else{
			return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 2;
		}
	}
	
	/**
	 * 根据权限删除管理员用户
	 * @param LoginID
	 * @param pwd
	 * @return 返回1成功删除，返回0删除失败，返回2错误
	 */
	public int delAdminUser(String  LoginID) {
		
		try {
			
			sqlDB objsqlDB = new sqlDB();
			String _sql = "delete  from Adminuser where LoginID= '" + LoginID.replace(" ", "") + "' ";						
			
			System.out.println(_sql);
			if(objsqlDB.deldata(_sql) == 1){
				return 1;
			}else{
			return 0;
			}
			

		} catch (Exception ex) {
			return 2;
		}
	}
}
