package com.soccrates.middletier.constant;

/*
 * 
 */

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationConstants.
 */
public class ApplicationConstants {

	
	
	public static String TEAMDEFUALTIMAGE="http://34.218.114.30/team/teamdefault.png";
	
	public static String USERDEFAULTIMAGE="http://34.218.114.30/user/userdefault.png";
	
	
	/** The logger. */
	// Logger for All Classes and functions
	public static Logger logger = Logger.getLogger(ApplicationConstants.class.getCanonicalName());

	/** The Constant US_DATE_FORMAT. */
	public static final SimpleDateFormat US_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	/** The Constant DATE_FOR_REPORT. */
	public static final SimpleDateFormat DATE_FOR_REPORT = new SimpleDateFormat("YYMMdd");

	/** The Constant DATE_SEARCH. */
	public static final SimpleDateFormat DATE_SEARCH = new SimpleDateFormat("MM-dd-yyyy");

	/** The Constant UTF_8. */
	public static final String UTF_8 = "UTF-8";

	/** The Constant TRANSMISSIONDATE. */
	// Date Formats used in System
	public static final SimpleDateFormat TRANSMISSIONDATE = new SimpleDateFormat("yyyy-MM-dd");

	/** The Constant DATE_WITH_TIME. */
	public static final SimpleDateFormat DATE_WITH_TIME = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	/** The Constant DATE_IN_US_FORMAT. */
	public static final SimpleDateFormat DATE_IN_US_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

	/** The Constant DATE_FORMAT_IN_REPORT. */
	public static final SimpleDateFormat DATE_FORMAT_IN_REPORT = new SimpleDateFormat("yyyyMMdd");

	/** The Constant DATEPERIOD_FORMAT_IN_REPORT. */
	public static final SimpleDateFormat DATEPERIOD_FORMAT_IN_REPORT = new SimpleDateFormat("yyyyMM");

	/** The Constant DATEFORMATFORSER. */
	public static final SimpleDateFormat DATEFORMATFORSER = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/** The Constant ENCRYPTION_KEY. */
	public static final String ENCRYPTION_KEY = "AZygpewJsCpRrfOF";

	/** The Constant CREATE. */
	public static final String CREATE = "1";

	/** The Constant UPDATE. */
	public static final String UPDATE = "2";

	/** The Constant VIEW. */
	public static final String VIEW = "3";

	/** The Constant DELETE. */
	public static final String DELETE = "4";

	/** The Constant MOBILE_PAGE_SIZE. */
	public static final int MOBILE_PAGE_SIZE = 10;

	/** The Constant WEB_PAGE_SIZE. */
	public static final int WEB_PAGE_SIZE = 25;

	/** The Constant FILE_PATH. */
	public static final String FILE_PATH = "";

	/** The Constant CACHE_REQUIRED_FALSE. */
	public static final boolean CACHE_REQUIRED_FALSE = false;

	/** The Constant CACHE_REQUIRED_TRUE. */
	public static final boolean CACHE_REQUIRED_TRUE = true;

	/** The Constant DECIMALFORMAT. */
	public static final DecimalFormat DECIMALFORMAT = new DecimalFormat("0.00");

	public static final int USERTYPE_COACH = 1;

	public static final int USERTYPE_PLAYER = 2;
	public static final int USERTYPE_ADMIN = 0;

}
