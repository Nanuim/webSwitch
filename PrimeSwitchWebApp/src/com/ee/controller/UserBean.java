package com.ee.controller;

import java.io.File;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ee.dao.TranHandler;
import com.ee.entity.Mfb;

@ManagedBean(name= "userBean")
@SessionScoped
public class UserBean {
	
	 	String id;
	    String name;
	    String wsdl_location;
	    String mfi_branch_id;
	    String charge_account;
	    String suspense_account;
	    File[] listOfFiles;
	    
	public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getWsdl_location() {
			return wsdl_location;
		}

		public void setWsdl_location(String wsdl_location) {
			this.wsdl_location = wsdl_location;
		}

		public String getMfi_branch_id() {
			return mfi_branch_id;
		}

		public void setMfi_branch_id(String mfi_branch_id) {
			this.mfi_branch_id = mfi_branch_id;
		}

		public String getCharge_account() {
			return charge_account;
		}

		public void setCharge_account(String charge_account) {
			this.charge_account = charge_account;
		}

		public String getSuspense_account() {
			return suspense_account;
		}

		public void setSuspense_account(String suspense_account) {
			this.suspense_account = suspense_account;
		}
		
		public File[] getListOfFiles() {
			TranHandler.read();
			return listOfFiles;
		}

		public void setListOfFiles(File[] listOfFiles) {
			this.listOfFiles = listOfFiles;
		}

	public String addUser()
	{
		try{
		Mfb mb = new Mfb (); 
			mb.setId(getId());
			mb.setName(getName());
			mb.setMfi_branch_id(getMfi_branch_id());
			mb.setSuspense_account(getSuspense_account());
			mb.setCharge_account(getCharge_account());
			mb.setWsdl_location(getWsdl_location());
			
			TranHandler.save(mb);
		      
			clearForm();
	 
			 return "SUCCESS";
			   } catch (Exception e) 
			   {
			   e.printStackTrace();
			   } 
			 
			  return "ERROR";
}	
	
        
        public String deleteUser()
	{
		try{
		Mfb mb = new Mfb (); 
			mb.setId(getId());		
			TranHandler.deletebyid(mb.getId());
		      
			clearForm();
	 
			 return "SUCCESS";
			   } catch (Exception e) 
			   {
			   e.printStackTrace();
			   } 
			 
			  return "ERROR";
}	
        
 public String updateUser()
	{
		try{
		Mfb mb = new Mfb (); 
			mb.setId(getId());
			mb.setName(getName());
			mb.setMfi_branch_id(getMfi_branch_id());
			mb.setSuspense_account(getSuspense_account());
			mb.setCharge_account(getCharge_account());
			mb.setWsdl_location(getWsdl_location());
			
			TranHandler.update(mb);
		      
			clearForm();
	 
			 return "SUCCESS";
			   } catch (Exception e) 
			   {
			   e.printStackTrace();
			   } 
			 
			  return "ERROR";
}	       
 
	
	private void clearForm(){
		this.setId("");
		this.setName("");
		this.setMfi_branch_id("");
		this.setSuspense_account("");
		this.setCharge_account("");
		this.setWsdl_location("");
		
		}
	
	

}
