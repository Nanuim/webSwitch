package com.ee.entity;

/**
 *
 * @author Nancy Imeh
 */
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class Mfb {
    String id;
    String name;
    String wsdl_location;
    String mfi_branch_id;
    String charge_account;
    String suspense_account;

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }  
    
    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getWsdl_location() {
        return wsdl_location;
    }

    @XmlElement
    public void setWsdl_location(String wsdl_location) {
        this.wsdl_location = wsdl_location;
    }

    public String getCharge_account() {
        return charge_account;
    }

    @XmlElement
    public void setCharge_account(String charge_account) {
        this.charge_account = charge_account;
    }

    public String getMfi_branch_id() {
        return mfi_branch_id;
    }

    @XmlElement
    public void setMfi_branch_id(String mfi_branch_id) {
        this.mfi_branch_id = mfi_branch_id;
    }

	public String getSuspense_account() {
		return suspense_account;
	}

	public void setSuspense_account(String suspense_account) {
		this.suspense_account = suspense_account;
	}

	@Override
	public String toString() {
		return "Mfb [id=" + id + ", name=" + name + ", wsdl_location=" + wsdl_location + ", mfi_branch_id="
				+ mfi_branch_id + ", charge_account=" + charge_account + ", suspense_account=" + suspense_account + "]";
	}

    
}