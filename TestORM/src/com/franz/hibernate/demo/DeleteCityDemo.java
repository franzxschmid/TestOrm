package com.franz.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.franz.hibernate.entity.City;

public class DeleteCityDemo {

	public static void main(String[] args){
		
		//Create Session Factory
		
		SessionFactory factory = new Configuration()
		                         .configure("hibernate.cfg.xml")
		                         .addAnnotatedClass(City.class)
		                         .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			
			

			//Start a Transaction
			session.beginTransaction();
			
			//Delete on the fly
			session.createQuery("delete from city where population < 0").executeUpdate();
		
			//Commit Transaction (MAkes Update persistent)
			session.getTransaction().commit();
			
			
		} finally{
			factory.close();
		}
		
	}

}
