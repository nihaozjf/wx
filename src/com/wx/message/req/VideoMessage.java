package com.wx.message.req;

public class VideoMessage extends BaseMessage{
	
	private String MediaId;//视频消息媒体ID
	
	private String ThumbMediaId;//视频消息缩略图的媒体ID

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
