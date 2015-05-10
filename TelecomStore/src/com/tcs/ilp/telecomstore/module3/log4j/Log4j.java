
/*
 @author T139 Group C 
 */

package com.tcs.ilp.telecomstore.module3.log4j;

import org.apache.log4j.Logger;


public class Log4j {
	private static Logger _appLogger=Logger.getLogger("appLogger");

	public static Logger get_appLogger() {
		return _appLogger;
	}

	public static void main(String s[])
	{
		_appLogger.trace("appLogger trace");
		_appLogger.debug("appLogger debug");
		_appLogger.info("appLogger info");
		_appLogger.warn("appLogger warn");
		_appLogger.error("appLogger error");
		_appLogger.fatal("appLogger fatal");
	}
	

}
