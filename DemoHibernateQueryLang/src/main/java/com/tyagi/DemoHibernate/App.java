package com.tyagi.DemoHibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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

		Configuration config = new Configuration().configure().addAnnotatedClass(StudentHQL.class);
		SessionFactory sessFact = config.buildSessionFactory();
		Session session = sessFact.openSession();
		Transaction trans = session.beginTransaction();

		// This is simply to create a Hibernate Query
		Query q = session.createQuery("from StudentHQL where rollno=7");

		List<StudentHQL> list = (List<StudentHQL>) q.list();

		for (StudentHQL stud : list) {
			System.out.println(stud);
		}

		StudentHQL s1 = (StudentHQL) q.uniqueResult();

		System.out.println(s1);

		Query q1 = session.createQuery("select rollno, name, marks from StudentHQL s where s.rollno=7");

		Object[] obj = (Object[]) q1.uniqueResult();

		System.out.println(obj[0] + " " + obj[1] + " " + obj[2]);

		Query q2 = session.createQuery("select rollno, name, marks from StudentHQL");

		List<Object[]> objs = (List<Object[]>) q2.list();

		for (Object[] ob : objs) {

			System.out.println(ob[0] + " ");
			System.out.print(ob[1] + " ");
			System.out.print(ob[2] + " ");

		}
		int b = 70;
		Query q3 = session.createQuery("select count(*) from StudentHQL s where s.marks>" + b);

		System.out.println(q3.getSingleResult());

		Query q4 = session.createQuery("select sum(s.marks) from StudentHQL s where s.marks> :b");
		q4.setParameter("b", b);

		System.out.println(q4.getSingleResult());

		SQLQuery q5 = session.createSQLQuery("select rollno, name, marks from StudentHQL");
		// Creates a map with key=column_name and value
		q5.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		// This will convert the objects into the specified class
		// q5.addEntity(StudentHQL.class);

		List objsql =  q5.list();

		for (Object ob : objsql) {

			Map m = (Map) ob;
			System.out.println(m.get("rollno") + " ");
			System.out.print(m.get("name") + " ");
			System.out.print(m.get("marks") + " ");

		}

		trans.commit();

	}

}
