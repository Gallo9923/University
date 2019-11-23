package model;

import java.util.ArrayList; 

public class Event
{
	// Constants
	public static int MIN_HOURS = 2; // hours
	public static int MAX_HOURS = 12; // hours
	public static int MIN_TIME = 7; //7:00 hours
	public static int MAX_TIME = 20; //20:00 hours
	
	// Atributes
	
	private String name;
	private int year;
	private int month;
	private int day; 
	private int hourStart;
	private int hourFinish;
	private String profName;
	private String facName;
	private int attendees;
	private boolean state;
	
	// Associations
	
	private ArrayList<Auditory> eAuditoriums;
	
	// Constructor
	
	/**
	* Create a new Event instance
	* pre: The parametters are valid
	* pos: A new event is instanced
	* @param name Name of the Event
	* @param year Year of the Event
	* @param month Month of the Event
	* @param day Day of the Event
	* @param hourStart Start hour of the event
	* @param hourFinish Finish hour of the event
	* @param profName Professor name in charge of the event
	* @param facName Faculty name in charge of the event
	* @param auditoriums Auditoriums reserved for the event
	*/
	public Event(String name, int year, int month, int day, int hourStart, int hourFinish, String profName,
	String facName, Auditory[] auditoriums) 
	{
		this.name = name;
		this.year = year;
		this.month = month; 
		this.day = day;
		this.hourStart = hourStart;
		this.hourFinish = hourFinish;
		this.profName = profName;
		this.facName = facName;
		
		this.attendees = 0;
		this.state = false; 	
		
		eAuditoriums = new ArrayList();
		
		for(int i=0; i<auditoriums.length; i++)
		{
			eAuditoriums.add(auditoriums[i]);
		}
		
	}
	
	
	//getters
	
	/**
	* Get the auditoriums of the event
	* pre: eAuditoriums is instanced
	* @return ArrayList Auditoriums of the event
	*/
	public ArrayList getAuditoriums()
	{
		return eAuditoriums;
	}
	
	
	/**
	* Get the year of the event
	* @return int Year of the event
	*/
	public int getYear()
	{
		return year;
	}
	
	
	/**
	* Get the month of the event
	* @return int month of the event
	*/
	public int getMonth()
	{
		return month; 
	}
	
	
	/**
	* Get the day of the event
	* @return int day of the event
	*/
	public int getDay()
	{
		return day;
	}
	
	
	/**
	* Get the start hour of the event
	* @return int start hour of the event
	*/
	public int getHourStart()
	{
		return hourStart;
	}
	
	
	/**
	* Get the finish hour of the event
	* @return int finish hour of the event
	*/
	public int getHourFinish()
	{
		return hourFinish;
	}
	
	
	/**
	* Get the name of the event
	* @return int name of the event
	*/
	public String getName()
	{
		return name;
	}
}