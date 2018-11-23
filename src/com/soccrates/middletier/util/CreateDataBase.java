package com.soccrates.middletier.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
  
import com.soccrates.middletier.address.AddressEntity;
import com.soccrates.middletier.company.CompanyEntity;
 
// TODO: Auto-generated Javadoc
/**
 * The Class CreateDataBase.
 */
public class CreateDataBase { 
	/**
 * The main method.
 *
 * @param args the arguments
 */
	public static void main(String[] args)  
	{
		// TODO Auto-generated method stub
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.addAnnotatedClass(AddressEntity.class);
		configuration.addAnnotatedClass(CompanyEntity.class);
		configuration.configure("hibernate.cfg.xml");
		new SchemaExport(configuration).create(true,true);		
		SessionFactory factory= configuration.buildSessionFactory();
		Session session= factory.getCurrentSession(); 
		session.beginTransaction(); 
		session.getTransaction().commit(); 
	} 
}


