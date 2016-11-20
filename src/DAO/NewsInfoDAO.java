package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.AdminUserBean;
import Bean.NewsInfoBean;
import DB.sqlDB;
import zjxTool.Security;

public class NewsInfoDAO {

	/**
	 * ��NewsInfo����в���
	 * 
	 * @param objNewsInfoBean
	 * @return ����1��ʾ����ɹ�,0��ʾ���ɹ�
	 */
	public int AddNewsInfo(NewsInfoBean objNewsInfoBean) {

		sqlDB objsqlDB = new sqlDB();
		// Security objSecurity = new Security();
		String _sql = "insert into NewsInfo(NewsID, NewsTitle, NewsContent, Release, ReleaseTime, Auditing, AuditingTime, Newstype, Depict) Values (?,?,?,?,?,?,?,?,?)";
		String NewsID = String.valueOf(objNewsInfoBean.getNewsID());
		String NewsTitle = objNewsInfoBean.getNewsTitle();
		String NewsContent = objNewsInfoBean.getNewsContent();
		String Release = String.valueOf(objNewsInfoBean.getRelease());
		String ReleaseTime = objNewsInfoBean.getReleaseTime();
		String Auditing = String.valueOf(objNewsInfoBean.getAuditing());
		String AuditingTime = objNewsInfoBean.getAuditingTime();
		String Newstype = String.valueOf(objNewsInfoBean.getNewstype());
		String Depict = objNewsInfoBean.getDepict();

		String[] a = { NewsID, NewsTitle, NewsContent, Release, ReleaseTime, Auditing, AuditingTime, Newstype, Depict };

		if (objsqlDB.insert(_sql, a) == true) {
			System.out.println("baocun");
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * ��ȡ����¼��
	 * 
	 * @return
	 */
	public int selcetnewsIDMax() {
		sqlDB objsqlDB = new sqlDB();
		String _sql = "SELECT Max(NewsID) As max1 From NewsInfo ";
		ResultSet rs = objsqlDB.select_2(_sql);
		try {
			if (rs.next()) {
				return rs.getInt("max1");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}

	}

	/**
	 * ��NewsInfo����и��²���
	 * 
	 * @param objNewsInfoBean
	 * @return ����1����ɹ���0��ʾʧ��
	 */
	public int updateNewsInfo(NewsInfoBean objNewsInfoBean) {

		sqlDB objsqlDB = new sqlDB();
		// Security objSecurity = new Security();
		String _sql = "update NewsInfo set NewsTitle= ?, NewsContent = ?,Release=?,ReleaseTime=?,Auditing=?,AuditingTime=?,Newstype=?,Depict=? Where NewsID = ?";
		String NewsID = String.valueOf(objNewsInfoBean.getNewsID());
		String NewsTitle = objNewsInfoBean.getNewsTitle();
		String NewsContent = objNewsInfoBean.getNewsContent();
		String Release = String.valueOf(objNewsInfoBean.getRelease());
		String ReleaseTime = objNewsInfoBean.getReleaseTime();
		String Auditing = String.valueOf(objNewsInfoBean.getAuditing());
		String AuditingTime = objNewsInfoBean.getAuditingTime();
		String Newstype = String.valueOf(objNewsInfoBean.getNewstype());
		String Depict = objNewsInfoBean.getDepict();

		String[] a = { NewsTitle, NewsContent, Release, ReleaseTime, Auditing, AuditingTime, Newstype, Depict, NewsID };
		if (objsqlDB.update_2(_sql, a) == 1) {

			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Ӧ���ڲ�ѯĳ������ ���ݱ��
	 * 
	 * @param NewsID
	 * @param NewsTitle
	 * @param NewsContent
	 * @return
	 */
	public ResultSet selectNewsInfo(String NewsID) {

		sqlDB objsqlDB = new sqlDB();
		String _sql = "select * from NewsInfo where NewsID = '" + NewsID + "'";

		// System.out.println(_Sql);
		return objsqlDB.select_2(_sql);
	}

	/**
	 * �����������ͽ��в�ͬ�Ĳ�ѯ
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param where
	 *            0��ͷ���� 1��Ҫ�ţ� 2����̸�� 3�����飩
	 * @return ���ؽ����������json���ɲ�ǰ̨����
	 */
	public ResultSet selectNewstype(String pageindex, String pagesize, int where) {
		String _Sql = "execute up_Pagination @Tables ='NewsInfo', @PrimaryKey ='NewsID', @Sort='NewsID desc', @PageSize="
				+ pagesize + ", @Fields ='*', @Group='', @CurrentPage = " + pageindex + ",  @Filter=' Newstype = "
				+ where + " and Release = 0 ";

		_Sql += "'";
		sqlDB objsqlDB = new sqlDB();
		 //System.out.println(_Sql);
		return objsqlDB.select_2(_Sql);

	}

	/**
	 * ���������Ͳ�ѯ��¼��
	 * 
	 * @param where
	 *            0��ͷ���� 1��Ҫ�ţ� 2����̸�� 3�����飩
	 * @return
	 */
	public int GetTotal(int where) {
		try {
			String _Sql = "select Count(*) AS TotalCount from NewsInfo where Newstype = " + where + " ";
			// System.out.println(_Sql);
			sqlDB objsqlDB = new sqlDB();
			ResultSet objRS = objsqlDB.select_2(_Sql);

			if (objRS.next()) {
				return objRS.getInt("TotalCount");
			} else {
				return 0;
			}
		} catch (Exception ex) {
			return 0;
		}
	}

	

	/**
	 * ɾ�����ţ�����NewsID
	 * @param LoginID
	 * @param pwd
	 * @return ����1�ɹ�ɾ��������0ɾ��ʧ�ܣ�����2����
	 */
	public int delNews(int  NewsID) {
		
		try {
			
			sqlDB objsqlDB = new sqlDB();
			String _sql = "delete  from NewsInfo where NewsID= '" + NewsID + "' ";						
			
			//System.out.println(_sql);
			if(objsqlDB.deldata(_sql) == 1){
				return 1;
			}else{
			return 0;
			}
			

		} catch (Exception ex) {
			return 2;
		}
	}
	
	/**
	 * ����������˺ͷ���״̬ op=0 ��� op=1 �˻����  op=2 �ݲ����� op=3 ����
	 * @param op
	 * @param NewsID
	 * @return  ����1�ɹ���0ʧ��
	 */
	public int optionNewsAud_Rel(int op,int NewsID,NewsInfoBean objNewsInfoBean)
	{
		
		
			String _Sql = "update NewsInfo set Auditing= ?, AuditingTime = ?,Release=?, ReleaseTime=? Where NewsID = "+NewsID+"";
			System.out.println(_Sql);
			sqlDB objsqlDB = new sqlDB();
			int Release=objNewsInfoBean.getRelease();
			String ReleaseTime=objNewsInfoBean.getReleaseTime();
			int Auditing=objNewsInfoBean.getAuditing();
			String AuditingTime=objNewsInfoBean.getAuditingTime();
			
			//System.out.println( Auditing+AuditingTime+Release+ ReleaseTime );
			Object[] a = {  Auditing, AuditingTime,Release, ReleaseTime,};

			if (objsqlDB.update_2(_Sql, a) == 1) {
				
				return 1;
			}
			else{
			return 0;
			}
	}

	/**
	 * ʹ�ô洢���̲�ѯNewsInfo�������з�ҳ����
	 * 
	 * @param pageindex
	 *            ��ǰΪ�ڼ�ҳ
	 * @param pagesize
	 *            ÿҳ�ļ�¼��
	 * @return ���ؽ������ʹ��json����
	 */
	public ResultSet selectuserinfo(String pageindex, String pagesize, String where, int op) {
		String _Sql = "execute up_Pagination @Tables ='NewsInfo', @PrimaryKey ='NewsID', @Sort='NewsID desc', @PageSize="
				+ pagesize + ", @Fields ='*', @Group='', @CurrentPage = " + pageindex + ",  @Filter='  ";
		if (op == 1) {// �ѷ���
			_Sql += "Release = 0 and Auditing =0";
			if (!where.isEmpty() || !"".equals(where) || where != null) {
				_Sql += " and NewsContent + NewsTitle like ''%" + where + "%'' ";
			}
		} else if (op == 2) {//δ���� 
			_Sql += "Release = 1 and  Auditing =0";
			if (!where.isEmpty() || !"".equals(where) || where != null) {
				_Sql += " and NewsContent + NewsTitle like ''%" + where + "%'' ";
			}
		} else if (op == 3) {// �����
			_Sql += "Auditing = 0 ";
			if (!where.isEmpty() || !"".equals(where) || where != null) {
				_Sql += "and NewsContent + NewsTitle like ''%" + where + "%'' ";
			}
		} else if (op == 4) {// δ���
			_Sql += "Auditing = 1 ";
			if (!where.isEmpty() || !"".equals(where) || where != null) {
				_Sql += " and NewsContent + NewsTitle like ''%" + where + "%'' ";
			}
		}

		_Sql += "'";
		sqlDB objsqlDB = new sqlDB();
		//System.out.println(_Sql);
		return objsqlDB.select_2(_Sql);

	}

	/**
	 * ��ñ���ܼ�¼��
	 * 
	 * @param where
	 * @return ��¼��
	 */
	public int GetTotal(String where, int op) {
		try {
			String _Sql = "select Count(*) AS TotalCount from NewsInfo where ";
			if (op == 1) {// �����
				_Sql += "Release = 0";
				if (!where.isEmpty() || !"".equals(where) || where != null) {
					_Sql += " and NewsContent + NewsTitle like '%" + where + "%' ";
				}
			} else if (op == 2) {// δ���
				_Sql += "Release = 1";
				if (!where.isEmpty() || !"".equals(where) || where != null) {
					_Sql += " and NewsContent + NewsTitle like '%" + where + "%' ";
				}
			} else if (op == 3) {// �ѷ���
				_Sql += "Auditing = 0";
				if (!where.isEmpty() || !"".equals(where) || where != null) {
					_Sql += "and NewsContent + NewsTitle like '%" + where + "%' ";
				}
			} else if (op == 4) {// δ����
				_Sql += "Auditing = 1";
				if (!where.isEmpty() || !"".equals(where) || where != null) {
					_Sql += " and NewsContent + NewsTitle like '%" + where + "%' ";
				}
			}

			// System.out.println(_Sql);
			sqlDB objsqlDB = new sqlDB();
			ResultSet objRS = objsqlDB.select_2(_Sql);

			if (objRS.next()) {
				return objRS.getInt("TotalCount");
			} else {
				return 0;
			}
		} catch (Exception ex) {
			return 0;
		}
	}

}
