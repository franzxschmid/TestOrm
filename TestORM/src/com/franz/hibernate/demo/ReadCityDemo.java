package com.franz.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.franz.hibernate.entity.City;

public class ReadCityDemo {

	public static void main(String[] args){
		
		//Create Session Factory
		
		SessionFactory factory = new Configuration()
		                         .configure("hibernate.cfg.xml")
		                         .addAnnotatedClass(City.class)
		                         .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			//Create a City-Object
			City tempCity = new City("Kempten", "DEU", "Baijeri", 69000);
			
			//Start a Transaction
			session.beginTransaction();
			
			//Save the City-Object
			session.save(tempCity);
			
			//Commit Transaction
			session.getTransaction().commit();
			
			//Find out the Citys ID
			System.out.println(tempCity.getId());
			
			//New Session and start Transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve Student based on ID: Primary Key
			System.out.println(tempCity.getId());
			
			City myCity = session.get(City.class, tempCity.getId());
			
			//Commit on Transaction
			session.getTransaction().commit();
			
		} finally{
			factory.close();
		}
		
		
		
	}
}
