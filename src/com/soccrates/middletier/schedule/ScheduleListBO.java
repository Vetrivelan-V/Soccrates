package com.soccrates.middletier.schedule;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;
 

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleListBO extends SoccratesObject {
	ArrayList<ScheduleBO> scheduleBOs = new ArrayList<>();

	public ArrayList<ScheduleBO> getScheduleBOs() {
		return scheduleBOs;
	}

	public void setScheduleBOs(ArrayList<ScheduleBO> scheduleBOs) {
		this.scheduleBOs = scheduleBOs;
	}
	

}
