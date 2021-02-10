package com.tyagi.DemoHibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Student {
	
	@Id
	int rollno;
	String name;
	int marks;
	
	//BY DEFAULT THE FETCH TYPE IS LAZY
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Laptop> laptops = new ArrayList<Laptop>();
	
	/*
	 * 
	@OneToOne
	private Laptop laptop;
	
	Each Student is having a laptop of his own, and no new table is created.
	Instead, A foreign key for Laptop is created in Studen table along with the other column
	 * */
	
	/*
	 * @OneToMany
	private List<Laptop> laptops = new ArrayList<Laptop>();
	
	This signifies that One Student can have multiple Laptops 
	And, to have one, student Id mapped to Multiple Laptop Ids
	We need a mapping table , which This Studen table creates automatically and takes care.
	 * */
	
	
	/*
	 * @OneToMany(mappedBy = "students")
	private List<Laptop> laptops = new ArrayList<Laptop>();
	
	mappedBy MUST NOT BE SPECIFIED ON BOTH SIDES, BECAUSE THEN WE CANNOT HANDLE
	 * */
	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public List<Laptop> getLaptops() {
		return laptops;
	}
	public void setLaptops(List<Laptop> laptops) {
		this.laptops = laptops;
	}
	

}
