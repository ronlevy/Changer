package com.RoNir.changer;

	public class MySingleton
	{
	  private static MySingleton instance;
	   
	  public boolean isAuth = false;
	  public String phoneNumber;
	   
	  public static void initInstance()
	  {
	    if (instance == null)
	    {
	      // Create the instance
	      instance = new MySingleton();
	    }
	  }
	 
	  public static MySingleton getInstance()
	  {
	    // Return the instance
	    return instance;
	  }
	   
	  private MySingleton()
	  {
	    // Constructor hidden because this is a singleton
	  }
	   
	  public void customSingletonMethod()
	  {
	    // Custom method
	  }
	}
