package com.soccrates.middletier.user;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserListBO extends SoccratesObject{

	ArrayList<UserBO>userBOs = new ArrayList<>();

	public ArrayList<UserBO> getUserBOs() {
		return userBOs;
	}

	public void setUserBOs(ArrayList<UserBO> userBOs) {
		this.userBOs = userBOs;
	}
	
}
