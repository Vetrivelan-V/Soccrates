package com.soccrates.middletier.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
 


// TODO: Auto-generated Javadoc
/**
 * The Class UtilityLogger.
 */
public class UtilityLogger {

/** The logger. */ 
	private static Logger logger  = null;
 
	
	/**
 * Gets the log.
 *
 * @param name the name
 * @return the log
 */
public static Logger getLog(String name)
	{
		//return  Logger.getLogger(name);
		
//		System.out.println("\n\n\nlogger");
		if(logger==null)
		{
			System.out.println(" Init logger");
			try{
				 Formatter simpleFormatter =new SimpleFormatter();


				logger= Logger.getLogger(name);
				String d=new SimpleDateFormat("_yyyy_MM_dd_HH_mm_").format(new Date());
				FileHandler fileHandler = new FileHandler("%h/TaxEngine"+d+"%g.log", 1024*1024,20,true);
				logger.addHandler(fileHandler);
				fileHandler.setLevel(Level.INFO);
				
				logger.setLevel(Level.ALL);
				fileHandler.setFormatter(simpleFormatter);  
				 
			}catch(Exception e){
				logger.log(Level.SEVERE, "Error occur in FileHandler.", e);

			}
			
		}
		
		return logger;
		
	}
	
}
