package com.franz.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.franz.hibernate.entity.City;

public class UpdateCityDemo {

	public static void main(String[] args){
		
		//Create Session Factory
		
		SessionFactory factory = new Configuration()
		                         .configure("hibernate.cfg.xml")
		                         .addAnnotatedClass(City.class)
		                         .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			
			int cityId = 1;

			//Start a Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			City city = session.get(City.class, cityId);

			//Update (in Memory)
			city.setCountryCode("AAA");
			
			//Commit Transaction (MAkes Update persistent)
			session.getTransaction().commit();
			
			
		} finally{
			factory.close();
		}
		
	}

}
