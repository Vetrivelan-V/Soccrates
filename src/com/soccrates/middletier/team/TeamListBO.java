package com.soccrates.middletier.team;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamListBO extends SoccratesObject {
	ArrayList<TeamBO> teamList= new  ArrayList<TeamBO>();

	public ArrayList<TeamBO> getTeamList() {
		return teamList;
	}

	public void setTeamList(ArrayList<TeamBO> teamList) {
		this.teamList = teamList;
	}
	

}
