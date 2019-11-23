package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class University
{
	
	// Atributes
	
	private String name;
	
	// Associations
	
	private ArrayList<Auditory> uAuditoriums; 
	private ArrayList<Event> events;
	
	// Constructor
	
	
	/**
	* Creates an instance of a University
	* pos: A new University is created
	* @param name Name of the University
	*/
	public University(String name)
	{
		this.name = name; 
		uAuditoriums = new ArrayList();
		events = new ArrayList();
	}
	
	
	// methods
	
	
	/**
	* Gets the avaibility of the auditory 
	* @param auditory Auditory to check its avaibility
	* @return String avaibility of the Auditory
	*/
	public String auditoryAvaibility(Auditory auditory)
	{
		String msg = ""; 
		
		for(int i=0; i<uAuditoriums.size(); i++)
		{	
			if(uAuditoriums.get(i) != null && uAuditoriums.get(i) == auditory)
			{
				msg = auditory.getAvaibility();
			}
		}
		
		return msg; 
	}
	
	
	/**
	* Updates de Avaibility of the auditoriums
	* pos: Avaibility of the auditoriums is updated
	*/
	public void updateAuditoryState()
	{
		LocalDate dt1 = LocalDate.now();
		int year = dt1.getYear();
		int month = dt1.getMonthValue();
		int day = dt1.getDayOfMonth();
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		Event event = null;
		boolean busy = false; 
		
		for(int i=0; i<events.size(); i++)
		{
			busy = false; 
			
			event = events.get(i);
			
			int year2Check = event.getYear(); 
			int month2Check = event.getMonth(); 
			int day2Check = event.getDay();
			
			int startHour = event.getHourStart();
			int finishHour = event.getHourFinish();
			
			if(year == year2Check)
			{
				if(month == month2Check)
				{
					if(day == day2Check)
					{
						if(hour >= startHour && hour <= finishHour)
						{
							ArrayList<Auditory> auditoriums = event.getAuditoriums();
							
							for(int z=0; z<auditoriums.size(); z++)
							{
								auditoriums.get(z).setAvaibility(Auditory.O);
								busy = true; 
							}
							
							
						}
					}
				}
			}
			
			if(busy == false)
			{
				ArrayList<Auditory> auditoriums = event.getAuditoriums();
							
							for(int k=0; k<auditoriums.size(); k++)
							{
								auditoriums.get(k).setAvaibility(Auditory.D);
							}
			}
			
		}
	}
	
	
	/**
	* Display the events in the following 5 days
	* @return String List of events
	*/
	public String displayProxEvents()
	{
		
		String msg = ""; 
		LocalDate dt1 = LocalDate.now();
		
		int dayToday = dt1.getDayOfMonth();
		int monthToday = dt1.getMonthValue();
		int yearToday = dt1.getYear();
		
		dt1 = dt1.plus(5, ChronoUnit.DAYS); 
		
		int day = dt1.getDayOfMonth();
		int month = dt1.getMonthValue();
		int year = dt1.getYear();
		boolean found = false; 
		
		if(!events.isEmpty())
		{
		
			int size = events.size(); 
			
			
			for(int i=0; i<size; i++)
			{
				Event event = events.get(i);
				int day2Check = event.getDay();
				int month2Check = event.getMonth();
				int year2Check = event.getYear(); 
				
				if(year2Check >= yearToday && year2Check <= year)
				{
					if(month2Check >= monthToday && month2Check <= year)
					{
						if(day2Check >= dayToday && day2Check <= day)
						{
							found = true;
							msg += "\nNombre: " + event.getName(); 
						}
					}
				}
				
				
			}
		} else
		{
			msg = "No hay eventos";
		}
		
		if(found == false) {
			msg = "No hay eventos";
		}
		
		return msg; 
	}
	
	
	/**
	* Removes an Event
	* pos: an event is removed
	* @param eventToRemove Event to remove
	* @return String Information about the process
	*/
	public String removeEvent(Event eventToRemove)
	{
		String msg = "";
		boolean deleted = false;
		
		for(int i=0; i<events.size() && !deleted; i++)
		{
			if(events.get(i) == eventToRemove)
			{
				events.remove(i);
				deleted = true;
				msg = "Se ha eliminado el evento";
			}
		}
		
		return msg;
	}
	
	
	/**
	* Gets the array of events in a given date
	* @param year Year of the events
	* @param month Month of the events
	* @param day Day of the events
	* @return Event[] Array of events
	*/
	public Event[] getEventsByDate(int year, int month, int day)
	{
		ArrayList<Event> eventsByDate = new ArrayList();
		Event event = null;
		
		for(int i=0; i<events.size(); i++)
		{
			event = events.get(i);
			
			if(event.getYear() == year)
			{
				if(event.getMonth() == month)
				{
					if(event.getDay() == day)
					{
						eventsByDate.add(event);
					}
				}
			} 
			
		}
		
		Event[] arrayEventsByDate = new Event[eventsByDate.size()];
		
		for(int j=0; j<eventsByDate.size(); j++)
		{
			arrayEventsByDate[j] = eventsByDate.get(j);
		}
		
		return arrayEventsByDate;
	}
	
	
	/**
	* Calculates the statistics of falty seats
	* @param auditory Auditory to check
	* @return String Porcentage of faulty seats
	*/
	public String faultySeatsStats(Auditory auditory)
	{
		boolean found = false; 
		String msg = "";
		
		for(int i=0; i<uAuditoriums.size() && !found; i++)
		{
			if(uAuditoriums.get(i) != null && uAuditoriums.get(i) == auditory)
			{
				int faultySeatsNum = uAuditoriums.get(i).faultySeats();
				int totalSeatsNum = uAuditoriums.get(i).totalSeats();
				double per = ((double)faultySeatsNum / (double)totalSeatsNum) * 100;
				found = true;
				msg = "El porcentaje de sillas danadas es: " + per;
			}
		}
	
		return msg;
	}
	
	
	/**
	* Change a seat state to okay
	* pos: The state of the Seat is updated
	* @param auditory Auditory to check
	* @param row row of the Seat to check
	* @param column Coloumn of the Seat to check
	* @return String Information about the process 
	*/
	public String fixedSeat(Auditory auditory, char row, int column)
	{
		String msg = "";
		boolean found = true;
		
		for(int i=0; i<uAuditoriums.size() && found; i++)
		{
			if(uAuditoriums.get(i) == auditory) 
			{
				found = true; 
				msg = uAuditoriums.get(i).fixedSeat(row, column);
			}
		}
		
		return msg; 
	}
	
	
	/**
	* Change the seat state to faulty
	* pos: The state of the Seat is updated
	* @param auditory Auditory to check
	* @param row row of the Seat to check
	* @param column Coloumn of the Seat to check
	* @param description Description about the Seat
	* @return String Information about the process
	*/
	public String reportFaultySeat(Auditory auditory, char row, int column, String description)
	{
		String msg = "";
		boolean found = false;
		
		for(int i=0; i<uAuditoriums.size() && !found; i++)
		{
			if(uAuditoriums.get(i) == auditory) 
			{
				found = true; 
				msg = uAuditoriums.get(i).reportFaultySeat(row, column, description);
			}
		}
		
		return msg; 
	}
	
	
	/**
	* Gets the avaibility of an array of auditoriums
	* @param year Year to check
	* @param month Month to check
	* @param day Day to check
	* @param hourStart Start hour of the event to check
	* @param hourFinish Finish hour of the event to check
	* @param auditoriums array of auditoriums to check
	* @return boolean True if avaible 
	*/
	public boolean eventAvailability(int year, int month, int day, int hourStart, int hourFinish, Auditory[] auditoriums)
	{
		boolean avaibility = true; 
		
		for(int i=0; i<events.size() && avaibility; i++)
		{
			Event event = events.get(i);
			
			if(event.getYear() == year)
			{
				if(event.getMonth() == month)
				{
					if(event.getDay() == day)
					{
						if(hourStart >= event.getHourStart() && hourFinish <= event.getHourFinish())
						{
							
							ArrayList<Auditory> eventAuditoriums = event.getAuditoriums(); 
							
							for(int j=0; j<auditoriums.length && avaibility; j++)
							{
								if(eventAuditoriums.contains(auditoriums[j]))
								{
									avaibility = false; 
								}
							}
							
							
						}
					}
				}
			}
			
		}
		
		return avaibility;
	}
	
	
	/**
	* Creates a new event
	* pos: A new event instance is created
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
	public String addEvent(String name, int year, int month, int day, int hourStart, int hourFinish,
	String profName, String facName, Auditory[] auditoriums)
	{	
		String msg = ""; 
		boolean dataCorrect = true; 
		
		if(!(String.valueOf(year).length() == 4))
		{
			dataCorrect = false;
			msg += "\nError: El anio digitado no es valido";
		}
		
		if(!(month >= 1 && month <= 12))
		{
			dataCorrect = false;
			msg += "\nError: El mes digitado no es valido";
		}
		
		if(!(day >= 1 && month <= 31))
		{
			dataCorrect = false;
			msg += "\nError: El dia digitado no es valido";
		}
		
		if(!(hourStart>= Event.MIN_TIME && hourFinish <= Event.MAX_TIME))
		{
			dataCorrect = false;
			msg += "\nError: El evento debe ser entre las " + 
			Event.MIN_TIME + ":00 y las " + Event.MAX_TIME + ":00 Horas";
		}
		
		
		if( !((hourFinish - hourStart) >= Event.MIN_HOURS && (hourFinish - hourStart) <= Event.MAX_HOURS))
		{
			dataCorrect = false;
			msg += "\nError: El evento debe durar entre " + Event.MIN_HOURS + " y " + Event.MAX_HOURS + " horas"; 
		}
		
		boolean found = false; 
		Event event0 = null;
		for(int i=0; i<events.size() && !found; i++)
		{
			event0 = events.get(i);
			if(event0.getName().equalsIgnoreCase(name))
			{
				dataCorrect = false;
				found = true;
				msg += "\nError: Ya existe un evento con el mismo nombre";
			}
		}
	
		if( dataCorrect == true) 
		{
			if(eventAvailability(year, month, day, hourStart, hourFinish, auditoriums))
			{
				Event event = new Event(name, year, month, day, hourStart, hourFinish, profName, facName, auditoriums);
				events.add(event);
				
				msg += "\nEl evento ha sido creado exitosamente";
			} else
			{
				msg += "\nError: No hay disponibilidad para el evento";
			}
			
				
		}
	
		return msg;
	}
	
	
	/**
	* Displays the list of all auditoriums
	* @return String List of all auditoriums
	*/
	public String displayAuditoriums()
	{
		String msg = "";
		
		for(int i=0; i<uAuditoriums.size(); i++)
		{
			Auditory aud = uAuditoriums.get(i);
			
			if(aud != null)
			{
				msg += (i+1) + ": " + aud.getName() + " / ";
			}
			
		}
		
		return msg;
	}
	
	
	/**
	* Get an auditory 
	* @param i index of the auditory in the ArrayList
	* @return Auditory Auditory
	*/
	public Auditory getAuditory(int i)
	{
		Auditory aud = null;
		
		if(i< uAuditoriums.size())
		{
			aud = uAuditoriums.get(i);
		}
		
		return aud;
	}
	
	
	/**
	* Creates a new instance of Auditory
	* pos: A new Auditory is instanced
	* @param name Name of the Auditory
	* @param columnsInfo Information about the number of rows and columns of the Auditory
	*/
	public String addAuditory(String name, int[] columnsInfo)
	{
		Auditory auditory = new Auditory(name, columnsInfo);
		
		uAuditoriums.add(auditory);
		
		return "Se ha creado el auditorio";
	}
	
}