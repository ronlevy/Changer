package com.RoNir.changer;

import android.app.Application;

public class AppSubClass extends Application  {
	public boolean isAuth = false;
	
	  @Override
	  public void onCreate()
	  {
	    super.onCreate();
	     
	    // Initialize the singletons so their instances
	    // are bound to the application process.
	    initSingletons();
	  }
	 
	  protected void initSingletons()
	  {
	    // Initialize the instance of MySingleton
	    MySingleton.initInstance();
	  }
	   
	  public void customAppMethod()
	  {
	    // Custom application method
	  }
	

}
