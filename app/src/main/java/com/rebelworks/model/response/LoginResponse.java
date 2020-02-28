package com.rebelworks.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Data data;

	@SerializedName("messages")
	private List<Object> messages;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setMessages(List<Object> messages){
		this.messages = messages;
	}

	public List<Object> getMessages(){
		return messages;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",messages = '" + messages + '\'' + 
			"}";
		}
}