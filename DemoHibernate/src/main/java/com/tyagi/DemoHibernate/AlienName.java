package com.tyagi.DemoHibernate;

import javax.persistence.Embeddable;

//This specifies that don't create an extra table for it.
//This table is a embeddable table, so Embedd it in the table where it is used
@Embeddable
public class AlienName {

	String fname;
	
	String middleName;
	
	String lastName;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
