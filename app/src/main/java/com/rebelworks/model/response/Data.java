package com.rebelworks.model.response;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("prefix")
	private String prefix;

	@SerializedName("token")
	private String token;

	public void setPrefix(String prefix){
		this.prefix = prefix;
	}

	public String getPrefix(){
		return prefix;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"prefix = '" + prefix + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}