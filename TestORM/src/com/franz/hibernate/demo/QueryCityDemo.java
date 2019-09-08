package com.franz.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.franz.hibernate.entity.City;

public class QueryCityDemo {

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
			
			//Query the Cities
			@SuppressWarnings("unchecked")
			List<City> cities = session.createQuery("from city").getResultList();
			
			displayCities(cities);
			
			//Query German Cities (WHERE-Clause)
			@SuppressWarnings("unchecked")
			List<City> germanCities = session.createQuery("from city c where c.countryCode = 'DEU' OR c.countryCode LIKE 'A%'").getResultList();
			
			displayCities(germanCities);
			

			//Commit Transaction
			session.getTransaction().commit();
			
		} finally{
			factory.close();
		}
		
		
		
	}
	
	//Method to display the Cities
	private static void displayCities(List<City> cities) {
		for(City city : cities){
			System.out.println(city);
		}
	}
}
