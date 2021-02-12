package com.tyagi.DemoHibernate;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


//There are 3 Layer's :
//1. Entity
//2. Class
//3. Table

// 1. if we say,
// @Entity(name="table_name")
// public class Alien
// Entity_name = table_name, Class_name = Alien, Table_Name = table_name

// 2. if we say,
// @Entity
// public class Alien
// Entity_name = Alien, Class_name = Alien, Table_name = Alien

// 3. if we say,
// @Entity
// @Table(name="table_name")
// public class Alien
// Entity_name = Alien, Class_name = Alien, Table_name = table_name

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name="Alien")
public class Alien {
	
	
	@Id
	private int aid;
	
	private AlienName alienName;
	
	//If we don't want to persist this property
	@Transient
	private String aname_pet;
	
	//We can specify different column name by mentioning here
	@Column(name="techName")
	private String techName;
	
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", alienName=" + alienName + ", aname_pet=" + aname_pet + ", techName=" + techName
				+ "]";
	}
	
	public AlienName getAlienName() {
		return alienName;
	}
	public void setAlienName(AlienName alienName) {
		this.alienName = alienName;
	}
	
	public String getAname_pet() {
		return aname_pet;
	}
	public void setAname_pet(String aname_pet) {
		this.aname_pet = aname_pet;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}

	

}
