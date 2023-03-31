package common;

public class ActionForward {
			 // 스트링과 포워드 모두 받기 위한 타입..?
	private String path;		  // 응답 경로(Jsp 이름)
	private boolean isRedirect;
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}  // 이동 방식(true이면 redirect, false이면 forward)
	
}