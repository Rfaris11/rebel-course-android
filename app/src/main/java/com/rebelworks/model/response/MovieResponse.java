package com.rebelworks.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataItem> data;

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

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
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
			"MovieResponse{" + 
			"msg = '" + msg + '\'' + 
			",code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",messages = '" + messages + '\'' + 
			"}";
		}
}