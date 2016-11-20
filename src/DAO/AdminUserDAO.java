package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.AdminUserBean;
import DB.sqlDB;
import zjxTool.Security;

public class AdminUserDAO {

	/**
	 * ��user����в���
	 * @param objAdminUserBean
	 * @return ����1��ʾ����ɹ�,0��ʾ���ɹ�
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
	 * ��AdminUser����и��²���
	 * @param objAdminUserBean
	 * @return ����1����ɹ���0��ʾʧ��
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
	 * �� pwd != null ʱ��֤��½,�� pwd = null ʱ��Ӧ���ڲ�ѯ�˻��Ƿ����
	 * @param LoginID
	 * @param pwd 
	 * @return ����1�����м�¼������0�����޼�¼������2�������
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
	 * ʹ�ô洢���̲�ѯUser�������з�ҳ����
	 * @param pageindex ��ǰΪ�ڼ�ҳ
	 * @param pagesize ÿҳ�ļ�¼��
	 * @return ���ؽ������ʹ��json����
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
	 * ��ñ���ܼ�¼��
	 * @param where
	 * @return ��¼��
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
	 * �����˺Ų�ѯȨ��
	 * @param LoginID
	 * @param pwd
	 * @return �ɹ�����Ȩ����ֵ0-3�������ӣ�ʧ�ܷ���0�����󷵻�2
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
	 * ����Ȩ��ɾ������Ա�û�
	 * @param LoginID
	 * @param pwd
	 * @return ����1�ɹ�ɾ��������0ɾ��ʧ�ܣ�����2����
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
