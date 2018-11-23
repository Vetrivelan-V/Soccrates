package com.soccrates.middletier.template;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccrates.middletier.util.SoccratesObject;


@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateListBO extends SoccratesObject {
	ArrayList<TemplateBO> templateBOs = new ArrayList<>();

	public ArrayList<TemplateBO> getTemplateBOs() {
		return templateBOs;
	}

	public void setTemplateBOs(ArrayList<TemplateBO> templateBOs) {
		this.templateBOs = templateBOs;
	}
	
}
