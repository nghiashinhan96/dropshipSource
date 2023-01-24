package com.SpringDb.HibernatePractic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.Spring.Entity.CustomerDAO;

public class SaveCustomer {
	// create Customer table and insert sample datas
	
	
	/*
	 * create table customer ( id int identity(1,1) primary key, last_name
	 * char(255), first_name char(255), email char(255)
	 * 
	 * );
	 * insert into customer(last_name,first_name, email) values('ngan','nguyen','nghia@gmail.com');
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(CustomerDAO.class)
				.buildSessionFactory();
try {
	
Session session = sessionFactory.getCurrentSession();
	//createCustomer(session);
	
	 
getCustomer(session);
	 // get customer with name
	 //String CUSTOMER_WITH_NAME ="SELECT * FROM CUSTOMER WHERE CUSTOMER_NAME LIKE %%";
}catch (Exception e) {
	// TODO: handle exception
	
}finally {
	sessionFactory.close();
}
}
	private static void createCustomer(Session session) {
		//create and save the customer
		CustomerDAO tempCustomer = new CustomerDAO("nghiaha", "nguyen", "nghia@gmail");
		session.beginTransaction();
		session.save(tempCustomer);
		session.getTransaction().commit();
	}
   private static void getCustomer(Session session) {
	 //get Customer with Id
		 session.beginTransaction();
		 CustomerDAO updateCustomer = session.get(CustomerDAO.class, 1);
		 System.out.println("Customer name is: "+updateCustomer.getLastName());
		 session.getTransaction().commit();
   }
}
