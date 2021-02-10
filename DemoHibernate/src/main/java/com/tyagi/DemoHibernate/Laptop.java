package com.tyagi.DemoHibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Laptop {
	
	@Id
	int lid;
	String lname;
	
	@ManyToMany(mappedBy = "laptops")
	private List<Student> students = new ArrayList<Student>();
	
	/*
	 * @OneToMany
	private List<Student> students = new ArrayList<Student>();
	
	Which ever class specifies the OneToMany Mapping will try to create a mapping table.
	To avoid multiple mapping tables to be created, we need to specifiy
	
	mappedBy = variable_name
	 * */
	
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	

}
