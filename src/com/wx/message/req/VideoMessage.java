package com.wx.message.req;

public class VideoMessage extends BaseMessage{
	
	private String MediaId;//��Ƶ��Ϣý��ID
	
	private String ThumbMediaId;//��Ƶ��Ϣ����ͼ��ý��ID

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	

}
