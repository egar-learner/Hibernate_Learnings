package com.tyagi.DemoHibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	//This method displays how to save data to the table from Java, using Entity class and
    	//Hibernate
    	saveDataToTable(false);
    	
    	getDataFromTable(false);
    	
    	//Check for Hibernate Mappings
    	hibernateMapping(false);
    	
    	hibernateFetchTypes(true);
    	
    	
    	
    }

	private static void saveDataToTable(boolean doOperation) {
		
		if(doOperation) {
		AlienName alienName = new AlienName();
		alienName.setFname("Manish");
		alienName.setLastName("Tyagi");
		alienName.setMiddleName("Kumar");
		Alien alien = new Alien();
    	alien.setAid(107);
    	alien.setAlienName(alienName);
    	alien.setTechName("Java7");
    	
    	
    	
    	//Get the configuration
    	Configuration config = new Configuration().configure().addAnnotatedClass(Alien.class);
    	
    	//Build a sessionFactory
    	SessionFactory ssFact = config.buildSessionFactory();
    	
    	//Open a Session
    	Session session = ssFact.openSession();
    	
    	Transaction trans = session.beginTransaction();
    	//Save an object
    	session.save(alien);
    	
    	//Ends the transaction
    	trans.commit();
		}
	}
	
	private static void getDataFromTable(boolean doOperation) {
		
		if(doOperation) {
		
		Configuration conf = new Configuration().configure().addAnnotatedClass(Alien.class);
		
		SessionFactory sessFact = conf.buildSessionFactory();
		
		Session session = sessFact.openSession();
		
		Transaction trans = session.beginTransaction();
		
		Alien alien = session.get(Alien.class, 106);
		System.out.println(alien);
		
		trans.commit();
		}
	}
	
	private static void hibernateMapping(boolean doOperation) {
		
		if(doOperation) {
			
			Laptop lap = new Laptop();
			lap.setLid(11);
			lap.setLname("HP");
			
			Student stud = new Student();
			stud.setRollno(15);
			stud.setName("Manish");
			stud.setMarks(99);
			stud.getLaptops().add(lap);
			
			//lap.getStudents().add(stud);
			
			
			Configuration config = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
			
			SessionFactory sessFact = config.buildSessionFactory();
			
			Session sess = sessFact.openSession();
			
			Transaction trans = sess.beginTransaction();
			
			sess.save(lap);
			sess.save(stud);
			
			trans.commit();
			
		}
	}
	
	private static void hibernateFetchTypes(boolean doOperation) {
		
		if(doOperation) {
			
			Configuration conf = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
			
			SessionFactory sessFact = conf.buildSessionFactory();
			
			Session sess = sessFact.openSession();
			
			Transaction trans = sess.beginTransaction();
			
			
//Hibernate: select student0_.rollno as rollno1_1_0_, student0_.marks as marks2_1_0_, student0_.name as name3_1_0_ from Student student0_ where student0_.rollno=?
//Manish
			
//IF WE SPECIFY THE FETCH TYPE AS EGAR THEN
//Hibernate: select student0_.rollno as rollno1_1_0_, student0_.marks as marks2_1_0_, student0_.name as name3_1_0_, laptops1_.students_rollno as students1_2_1_, laptop2_.lid as laptops_2_2_1_, laptop2_.lid as lid1_0_2_, laptop2_.lname as lname2_0_2_ from Student student0_ left outer join Student_Laptop laptops1_ on student0_.rollno=laptops1_.students_rollno left outer join Laptop laptop2_ on laptops1_.laptops_lid=laptop2_.lid where student0_.rollno=?
			Student student = sess.get(Student.class, 15);
			System.out.println(student.getName());
			
//Hibernate: select laptops0_.students_rollno as students1_2_0_, laptops0_.laptops_lid as laptops_2_2_0_, laptop1_.lid as lid1_0_1_, laptop1_.lname as lname2_0_1_ from Student_Laptop laptops0_ inner join Laptop laptop1_ on laptops0_.laptops_lid=laptop1_.lid where laptops0_.students_rollno=?
//HP
			List<Laptop> laptops = student.getLaptops();
			
			for(Laptop lap: laptops) {
				System.out.println(lap.getLname());
			}
			
			
			trans.commit();
		}
	}
}
