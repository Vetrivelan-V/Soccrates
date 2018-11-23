package com.soccrates.middletier.company;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.DocumentId;

import com.soccrates.middletier.address.AddressEntity;
import com.soccrates.middletier.util.SettingsEntity;
 
// TODO: Auto-generated Javadoc

/**
 * The Class MSCInventoryEntity.
 */
@Entity
@Table(name="company")
public class CompanyEntity {

	 
	private long companyId;
	
	private String name;
	
	private AddressEntity companyAddress;
	
	private SettingsEntity settingsId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="addressId")
	public AddressEntity getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(AddressEntity companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@DocumentId
	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "settingsId")
	public SettingsEntity getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(SettingsEntity settingsId) {
		this.settingsId = settingsId;
	}
	
	  	
	
}
