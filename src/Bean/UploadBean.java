package Bean;

public class UploadBean {

	
	private String FilePath; 	
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	
	public String getUpfiletime() {
		return upfiletime;
	}
	public void setUpfiletime(String upfiletime) {
		this.upfiletime = upfiletime;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getUpfileDepict() {
		return upfileDepict;
	}
	public void setUpfileDepict(String upfileDepict) {
		this.upfileDepict = upfileDepict;
	}
	public String getUpimgdepict() {
		return upimgdepict;
	}
	public void setUpimgdepict(String upimgdepict) {
		this.upimgdepict = upimgdepict;
	}
	public String getImgtime() {
		return imgtime;
	}
	public void setImgtime(String imgtime) {
		this.imgtime = imgtime;
	}
	private String FileType; 
	private String upfileDepict; 
	private String upfiletime;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String imgurl; 
	private String upimgdepict; 
	private String imgtime;
	
}
