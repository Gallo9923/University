package model;

public class Seat
{
	
	// Atributes
	
	private boolean state;
	private char row;
	private int column;
	private String description; 
	
	
	// Constructor
	
	/**
	* Create a Seat object
	* pos: A new Seat is created
	* @param row row of the Seat
	* @param column column of the Seat
	*/
	public Seat(char row, int column)
	{
		this.state = true; 
		this.row = row;
		this.column = column;
		this.description = ""; 
	}
	
	// Getters
	
	/**
	* Gets the state of the Seat
	* @return boolean True if the chain is okay
	*/
	public boolean getState()
	{
		return state;
	}
	
	// Setters
	
	/**
	* Sets the value of State of the Seat
	* pos The State of the Seat has changed
	* @param state True if the chair is okay
	*/
	public void setState(boolean state)
	{
		this.state = state;	// Seat broken
	}
	
	/**
	* Sets the Description of the Seat
	* pos The Description of the Seat has changed
	* @param description Description of the damaged Seat
	*/
	public void setDescription(String description)
	{
		this.description = description; 
	}
	
}