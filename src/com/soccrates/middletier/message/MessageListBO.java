package com.soccrates.middletier.message;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageListBO extends SoccratesObject {
	 ArrayList<MessageBO>messageBOs= new ArrayList<>();

	public ArrayList<MessageBO> getMessageBOs() {
		return messageBOs;
	}

	public void setMessageBOs(ArrayList<MessageBO> messageBOs) {
		this.messageBOs = messageBOs;
	}
	 
}
