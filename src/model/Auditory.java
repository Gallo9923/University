package model;

public class Auditory
{
	
	// Constants
	
	public static String O = "OCUPADO";
	public static String D = "DISPONIBLE";
	public static char[] ABC = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
	'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	// Atributes
	
	private String name; 
	private String avaibility;
	
	// Associations
	
	private Seat[][] seats;
	
	// Constructor
	
	/**
	* Creates a new instance of Auditory
	* pos: A new Auditory is instanced
	* @param name Name of the Auditory
	* @param columnsInfo Information about the number of rows and columns of the Auditory
	*/
	public Auditory(String name, int[] columnsInfo)
	{
		this.name = name;
		
		int rows = columnsInfo.length; 
		
		int columns = 1; 
		
		for(int z=0; z<columnsInfo.length; z++)
		{
			columns = Math.max(columns, columnsInfo[z]);
		}
		
		seats = new Seat[rows][columns];
		
		for(int i=0; i<seats.length; i++)
		{
			for(int j=0; j<columnsInfo[i]; j++)
			{
				
				char rowName = Auditory.ABC[i];
				int columnNumber = j+1;
				seats[i][j] = new Seat(rowName, columnNumber);
			}
		}
		
		this.avaibility = Auditory.D; 
	
	}
	
	// Methods
	
	/**
	* Gets the number of faulty seats in the Auditory
	* @return int number of faulty seats in the Auditory
	*/
	public int faultySeats()
	{
		int faultySeats = 0; 
		Seat seat = null;
		
		for(int i=0; i<seats.length; i++) 
		{
			for(int j=0; j<seats[0].length; j++)
			{
				seat = seats[i][j];
				
				if(seat != null && seat.getState() == false)
				{
					faultySeats++;
				}
			}
		}
		
		return faultySeats;
	}
	
	
	/**
	* Gets the number of total seats in the Auditory
	* @return int number of total seats in the Auditory
	*/
	public int totalSeats()
	{
		int totalSeats = 0; 
		Seat seat = null;
		
		for(int i=0; i<seats.length; i++) 
		{
			for(int j=0; j<seats[0].length; j++)
			{
				seat = seats[i][j];
				
				if(seat != null)
				{
					totalSeats++;
				}
			}
		}
		
		return totalSeats;
	}
	
	
	/**
	* Allows to change the state of the seat to okay
	* pos: the state of the Seat is changed
	* @param row The row of the Seat
	* @param column The column of the Seat
	* @return String Information about the process
	*/
	public String fixedSeat(char row, int column)
	{
		String msg = "";
		
		boolean found = false;
		int i = 0;
		for(int z=0; z<Auditory.ABC.length && !found; z++)
		{
			if(row == Auditory.ABC[i])
			{
				found = true;
				i = z; 
			}
		}
		
		System.out.println("ENTRE");
		
		if(found == true)
		{
			if(column >= 0 &&  column<seats[0].length)
			{
				seats[i][column].setState(true); // seat fixed
				seats[i][column].setDescription("");
				msg += "Se ha actualizado la silla exitosamente";
				
			} else
			{
				msg += "\nError: El numero de la silla no existe";
			}
		} else
		{
			msg += "\nError: La letra de la silla no existe";
		}
		
		return msg; 	
	}
	
	
	/**
	* Allows to change the state of the seat to faulty
	* pos: the state and description of the Seat is changed
	* @param row The row of the Seat
	* @param column The column of the Seat
	* @param description Description of the damaged Seat
	* @return String Information about the process
	*/
	public String reportFaultySeat(char row, int column, String description)
	{
		String msg = "";
		
		boolean found = false;
		int i = 0;
		for(int z=0; z<Auditory.ABC.length && !found; z++)
		{
			if(row == Auditory.ABC[i])
			{
				found = true;
				i = z; 
			}
		}
		
		if(found == true)
		{
			if(column >= 0 &&  column<seats[0].length)
			{
				seats[i][column].setState(false); // seat broken
				seats[i][column].setDescription(description);
				msg += "Se ha actualizado la silla exitosamente";
				
			} else
			{
				msg += "\nError: El numero de la silla no existe";
			}
		} else
		{
			msg += "\nError: La letra de la silla no existe";
		}
		
		
		
		
		return msg; 
	}
	
	// Setters
	
	
	/**
	* Sets the avaibility of the Auditory
	* pos: The avaibility of the Auditory is updated
	* @param avaibility Avaivility of the auditory
	*/
	public void setAvaibility(String avaibility)
	{
		this.avaibility = avaibility;
	}
	
	//Getters
	
	
	/**
	* Get the name of the Auditory
	* @return String The name of the Auditory
	*/
	public String getName()
	{
		return this.name;	
	}
	
	
	/**
	* Get the avaibility of the Auditory
	* @return String The Avaibility of the Auditory
	*/
	public String getAvaibility()
	{
		return avaibility; 
	}
	
}