package com.franz.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.franz.hibernate.entity.City;

public class CreateCityDemo {

	public static void main(String[] args){
		
		//Create Session Factory
		
		SessionFactory factory = new Configuration()
		                         .configure("hibernate.cfg.xml")
		                         .addAnnotatedClass(City.class)
		                         .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			//Create a City-Object
			City tempCity = new City("Memmingen", "DEU", "Baijeri", 43000);
			
			//Start a Transaction
			session.beginTransaction();
			
			//Save the City-Object
			session.save(tempCity);
			
			//Commit Transaction
			session.getTransaction().commit();
			
		} finally{
			factory.close();
		}
		
		
		
	}
}
