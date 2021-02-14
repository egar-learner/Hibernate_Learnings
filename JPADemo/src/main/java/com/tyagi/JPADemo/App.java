package com.tyagi.JPADemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	AlienJPA alien = new AlienJPA();
    	alien.setAid(33);
    	alien.setAname("AlienName");
    	alien.setTech("DS");
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    	EntityManager em = emf.createEntityManager();
    	
    	AlienJPA a = em.find(AlienJPA.class, 7);
    	
    	em.getTransaction().begin();
    	em.persist(alien);
    	em.getTransaction().commit();
    	
    	System.out.println(alien);
    	
    	System.out.println(a);
        
    }
}
