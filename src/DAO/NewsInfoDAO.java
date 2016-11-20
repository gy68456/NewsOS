package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.AdminUserBean;
import Bean.NewsInfoBean;
import DB.sqlDB;
import zjxTool.Security;

public class NewsInfoDAO {

	/**
	 * 对NewsInfo表进行插入
	 * 
	 * @param objNewsInfoBean
	 * @return 返回1表示插入成功,0表示不成功
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
	 * 获取最大记录数
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
	 * 对NewsInfo表进行更新操作
	 * 
	 * @param objNewsInfoBean
	 * @return 返回1代表成功，0表示失败
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
	 * 应用于查询某条新闻 根据编号
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
	 * 根据新闻类型进行不同的查询
	 * 
	 * @param pageindex
	 * @param pagesize
	 * @param where
	 *            0（头条） 1（要闻） 2（深谈） 3（新议）
	 * @return 返回结果集，利用json生成并前台解析
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
	 * 按新闻类型查询记录数
	 * 
	 * @param where
	 *            0（头条） 1（要闻） 2（深谈） 3（新议）
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
	 * 删除新闻，根据NewsID
	 * @param LoginID
	 * @param pwd
	 * @return 返回1成功删除，返回0删除失败，返回2错误
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
	 * 操作新闻审核和发布状态 op=0 审核 op=1 退回审核  op=2 暂不发布 op=3 发布
	 * @param op
	 * @param NewsID
	 * @return  返回1成功，0失败
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
	 * 使用存储过程查询NewsInfo表，并进行分页处理
	 * 
	 * @param pageindex
	 *            当前为第几页
	 * @param pagesize
	 *            每页的记录数
	 * @return 返回结果集，使用json解析
	 */
	public ResultSet selectuserinfo(String pageindex, String pagesize, String where, int op) {
		String _Sql = "execute up_Pagination @Tables ='NewsInfo', @PrimaryKey ='NewsID', @Sort='NewsID desc', @PageSize="
				+ pagesize + ", @Fields ='*', @Group='', @CurrentPage = " + pageindex + ",  @Filter='  ";
		if (op == 1) {// 已发布
			_Sql += "Release = 0 and Auditing =0";
			if (!where.isEmpty() || !"".equals(where) || where != null) {
				_Sql += " and NewsContent + NewsTitle like ''%" + where + "%'' ";
			}
		} else if (op == 2) {//未发布 
			_Sql += "Release = 1 and  Auditing =0";
			if (!where.isEmpty() || !"".equals(where) || where != null) {
				_Sql += " and NewsContent + NewsTitle like ''%" + where + "%'' ";
			}
		} else if (op == 3) {// 已审核
			_Sql += "Auditing = 0 ";
			if (!where.isEmpty() || !"".equals(where) || where != null) {
				_Sql += "and NewsContent + NewsTitle like ''%" + where + "%'' ";
			}
		} else if (op == 4) {// 未审核
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
	 * 获得表的总记录数
	 * 
	 * @param where
	 * @return 记录数
	 */
	public int GetTotal(String where, int op) {
		try {
			String _Sql = "select Count(*) AS TotalCount from NewsInfo where ";
			if (op == 1) {// 已审核
				_Sql += "Release = 0";
				if (!where.isEmpty() || !"".equals(where) || where != null) {
					_Sql += " and NewsContent + NewsTitle like '%" + where + "%' ";
				}
			} else if (op == 2) {// 未审核
				_Sql += "Release = 1";
				if (!where.isEmpty() || !"".equals(where) || where != null) {
					_Sql += " and NewsContent + NewsTitle like '%" + where + "%' ";
				}
			} else if (op == 3) {// 已发布
				_Sql += "Auditing = 0";
				if (!where.isEmpty() || !"".equals(where) || where != null) {
					_Sql += "and NewsContent + NewsTitle like '%" + where + "%' ";
				}
			} else if (op == 4) {// 未发布
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
