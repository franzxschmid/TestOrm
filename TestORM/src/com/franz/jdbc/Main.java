package com.franz.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Main {

	static TreeMap<String, Integer> population  = new TreeMap<String, Integer>();
	
	
	
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String password = "root";
		
		try{
			Connection myCon = DriverManager.getConnection(url, user, password);
	        Statement stmt = myCon.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM country");
	        
	        while (rs.next()) {
	            population.put(rs.getString(2), rs.getInt(7));
	        }
		} catch(SQLException exc){
			exc.getMessage();
			exc.printStackTrace();
		} catch(Exception ex){
			ex.getMessage();
			ex.printStackTrace();
		}

		
		String eol = System.getProperty("line.separator");

		try (Writer writer = new FileWriter("somefile.csv")) {
		  for (Map.Entry<String, Integer> entry : population.entrySet()) {
		    writer.append(entry.getKey())
		          .append(';')
		          .append(entry.getValue().toString())
		          .append(eol);
		  }
		} catch (IOException ex) {
		  ex.printStackTrace(System.err);
		  ex.getMessage();
		}

	}
}


