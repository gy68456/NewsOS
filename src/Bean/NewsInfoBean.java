package Bean;

public class NewsInfoBean {

	public NewsInfoBean(){
		
	}
	private int NewsID;
	private String NewsTitle;
	private String NewsContent;
	private String Time;
	private int Release;
	private String ReleaseTime;
	private int Auditing;
	private String AuditingTime;
	private int Newstype;
	private String Depict;
	public String getReleaseTime() {
		return ReleaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		ReleaseTime = releaseTime;
	}
	public String getAuditingTime() {
		return AuditingTime;
	}
	public void setAuditingTime(String auditingTime) {
		AuditingTime = auditingTime;
	}
	public int getNewsID() {
		return NewsID;
	}
	public void setNewsID(int newsID) {
		NewsID = newsID;
	}
	public String getNewsTitle() {
		return NewsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		NewsTitle = newsTitle;
	}
	public String getNewsContent() {
		return NewsContent;
	}
	public void setNewsContent(String newsContent) {
		NewsContent = newsContent;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public int getRelease() {
		return Release;
	}
	public void setRelease(int release) {
		Release = release;
	}
	public int getAuditing() {
		return Auditing;
	}
	public void setAuditing(int auditing) {
		Auditing = auditing;
	}
	public int getNewstype() {
		return Newstype;
	}
	public void setNewstype(int newstype) {
		Newstype = newstype;
	}
	public String getDepict() {
		return Depict;
	}
	public void setDepict(String depict) {
		Depict = depict;
	}
	
}
