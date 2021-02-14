package com.tyagi.DemoHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		statesOfObject(false);
		
		getAndLoadDifference(true);

	}

	private static void statesOfObject(boolean doOperation) {
		if(doOperation) {
		Configuration config = new Configuration().configure().addAnnotatedClass(LaptopHQL.class);
		SessionFactory sessFact = config.buildSessionFactory();
		Session session = sessFact.openSession();
		Transaction trans = session.beginTransaction();

			LaptopHQL lap = new LaptopHQL();
			lap.setBrand("Manish"+25);
			lap.setId(25);
			lap.setPrice((100+25));
			
			//Hibernate: insert into LaptopHQL (brand, price, id) values (?, ?, ?)
			session.save(lap);
			//Since the object will be in persistent state a new update query will be fired
			//Hibernate: update LaptopHQL set brand=?, price=? where id=?
			
			lap.setPrice(850);
			//session.detach(lap);
			
			//When we remove the object from the session a delete query is fired and the object will
			//be available for garbage collection
			//Hibernate: delete from LaptopHQL where id=?
			session.remove(lap);
			
			
		trans.commit();
		//After the commit the object will be in the detached state and hence, No brand setting
		//will be done again
		lap.setBrand("HP");
		}
	}
	
	private static void getAndLoadDifference(boolean doOperation) {
		if(doOperation) {
		Configuration config = new Configuration().configure().addAnnotatedClass(LaptopHQL.class);
		SessionFactory sessFact = config.buildSessionFactory();
		Session session = sessFact.openSession();
		Transaction trans = session.beginTransaction();

		//Get : Like a eqar call is firing a query immediately
		//Returns Null value if the object is not found in the database
		LaptopHQL lap = session.get(LaptopHQL.class, 22);
		
		//Load : Like a lazy call , it is like firing a query immediately
		//Throws ObjectNotFoundException if record is not found in the database
		LaptopHQL lapLazy = session.load(LaptopHQL.class, 22);
			
		trans.commit();
		}
	}

}
