package com.example.demo.JPA.Model;

public class Response {

	private String responseMsg;
	private int responseCode;
	private String data;
	private Object jData;
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Object getjData() {
		return jData;
	}
	public void setjData(Object jData) {
		this.jData = jData;
	}

}
