package ui; 

import model.*;
import java.util.Scanner;

public class Main 
{
	// Constants
	
	public static final String[] AUD_NAMES = {"MANUELITA", "SIDOC", "ARGOS", "BANCO DE OCCIDENTE", "ERNESTO DELIMA", "VARELA", "TEATRINO", "TARIMA BIENESTAR"};
	public static final int[][] COLUMNS_INFO = {{1,1}, {1,2}, {2,1}, {2,2}, {1,1}, {1,1}, {1,1}, {1,1}};
	
	// Atributes
	
	private Scanner scNum; 
	private Scanner scStr;
	
	// Associations
	
	private University university;
	
	// Constructor
	
	public Main(String name)
	{	
		scNum = new Scanner(System.in);
		scStr = new Scanner(System.in);
		
		university = new University(name); 
		
	}
	
	// Main Method
	
	public static void main(String[] args)
	{
		
		Main obj = new Main("Universidad ICESI");
		
		obj.initializeAuditoriums();
		
		Auditory[] auditoriums1 = {obj.university.getAuditory(0)};
		
		obj.addEvent("EUDII", 2019, 11, 25, 7, 12, 
		"Efrain Pinto", "Ingenieria", auditoriums1);
	
		obj.menu();
		
		
	}
	
	// Methods
	
	public void displayAuditoriums()
	{
		System.out.println(university.displayAuditoriums());
	}
	
	public void menu()
	{
		System.out.println("--- Bienvenido al sistema de reservas ---");	
		
		boolean menu = true;
		
		while(menu)
		{
			System.out.println("1: Agregar evento \n2: Reportar silla danada" + 
			"\n3: Reportar silla reparada \n4: Agregar Auditorio \n5: % de sillas danadas" +
			"\n6: Eliminar evento \n7: Proximos eventos \n8: Disponibilidad de auditorio" +
			"\n9: Salir");
			int input = scNum.nextInt(); 
			
			switch(input)
			{
				case 1: 
					addEvent();// Agregar Evento
					break;
				case 2:
					reportFaultySeat();// Silla danada
					break;
				case 3: 
					fixedSeat();// Silla reparada
					break;
				case 4:
					addAuditory();// Agregar Auditorio
					break;
				case 5: 
					faultySeatsStats(); // Estadisticas Sillas
					break;
				case 6: 
					removeEvent(); // Quitar evento
					break;
				case 7:
					displayProxEvents();// Info Prox Eventos
					break;
				case 8:
					updateAuditoryState();
					auditoryAvaibility();//Estado de un auditorio
					break;
				case 9: 
					System.out.println("Ha salido");
					menu = false;
					break;
					
			}
		}
		
	}
	
	public void auditoryAvaibility()
	{
		displayAuditoriums();
		System.out.println("Digite el auditorio");
		int aux = scNum.nextInt();
			
		Auditory auditory = university.getAuditory(aux-1);
		
		String msg = university.auditoryAvaibility(auditory);
		System.out.println(msg);
	}
	
	public void updateAuditoryState()
	{
		university.updateAuditoryState();
	}
	
	public void displayProxEvents()
	{
		
		String msg = university.displayProxEvents();
		System.out.println(msg);
	}
	
	
	public void removeEvent()
	{
		System.out.println("Digite el anio del evento");
		int year = scNum.nextInt();
		
		System.out.println("Digite el mes del evento");
		int month = scNum.nextInt();
		
		System.out.println("Digite el dia del evento");
		int day = scNum.nextInt();
		
		Event[] events = university.getEventsByDate(year, month, day);
		
		for(int i=0; i<events.length; i++)
		{
			System.out.println("\n" + (i+1)+ ": " + events[i].getName()); 
		}
		
		System.out.println("Escoga el evento");
		int aux = scNum.nextInt();
		aux--;
		
		Event eventToRemove = events[aux];
		
		String msg = university.removeEvent(eventToRemove);
		System.out.println(msg);
	}
	
	
	public void faultySeatsStats()
	{
		displayAuditoriums();
		System.out.println("Digite el auditorio");
		int aux = scNum.nextInt();
			
		Auditory auditory = university.getAuditory(aux-1);
		
		String msg = university.faultySeatsStats(auditory);
		System.out.println(msg);
		
	}
	
	
	public void fixedSeat()
	{
		displayAuditoriums();
		System.out.println("Digite el auditorio");
		int aux = scNum.nextInt();
			
		Auditory auditory = university.getAuditory(aux-1);
		
		System.out.println("Digite la letra de la silla");
		char row = scStr.nextLine().charAt(0); 
		
		System.out.println("Digite el número de la silla");
		int column = scNum.nextInt();
		column--;	
		
		String msg = university.fixedSeat(auditory, row, column);
		System.out.println(msg);
	}
	
	
	public void reportFaultySeat()
	{
		displayAuditoriums();
		System.out.println("Digite el auditorio");
		int aux = scNum.nextInt();
			
		Auditory auditory = university.getAuditory(aux-1);
		
		System.out.println("Digite la letra de la silla");
		char row = scStr.nextLine().charAt(0); 
		
		System.out.println("Digite el número de la silla");
		int column = scNum.nextInt();
		column--;
		
		System.out.println("Digite la descripcion");
		String description = scStr.nextLine();
		
		String msg = university.reportFaultySeat(auditory, row, column, description);
		System.out.println(msg);
	}
	
	public void addEvent(String name, int year, int month, int day, int hourStart, int hourFinish,
	String profName, String facName, Auditory[] auditoriums)
	{
		university.addEvent(name, year, month, day, hourStart, hourFinish, profName, facName, auditoriums);
	}
	
	public void addEvent()
	{
		System.out.println("Digite el nombre del evento");
		String name = scStr.nextLine();
		
		System.out.println("Digite el anio del evento");
		int year = scNum.nextInt();
		
		System.out.println("Digite el mes del evento");
		int month = scNum.nextInt();
		
		System.out.println("Digite el dia del evento");
		int day = scNum.nextInt();
		
		System.out.println("Digite la hora de inicio del evento");
		int hourStart = scNum.nextInt(); 
		
		System.out.println("Digite la hora fin del evento");
		int hourFinish = scNum.nextInt();
		
		System.out.println("Digite el profesor encargado");
		String profName = scStr.nextLine();
		
		System.out.println("Digite el nombre de la facultad");
		String facName = scStr.nextLine();
		
		System.out.println("Digite el número de auditorios a utilizar");
		int numAud = scNum.nextInt(); 
		
		Auditory[] auditoriums = new Auditory[numAud];
		
		for(int i=0; i<numAud; i++)
		{
			displayAuditoriums();
			
			System.out.println("Digite el auditorio");
			int aux = scNum.nextInt();
			
			auditoriums[i] = university.getAuditory(aux-1);
			
		}
		
		String msg = university.addEvent(name, year, month, day, hourStart, hourFinish, profName, facName, auditoriums);
		System.out.println(msg);
		
	}
	
	public void addAuditory()
	{
		System.out.println("Digite el nombre del auditorio");
		String name = scStr.nextLine(); 
		
		System.out.println("Cuantas filas tiene el auditorio");
		int rows = scNum.nextInt();
		
		int[] columnsInfo = new int[rows];
		for(int i=0; i<rows; i++)
		{
			System.out.println("Cuantas sillas hay en la fila " + (i+1));
			columnsInfo[i] = scNum.nextInt();
		}
		
		String msg = university.addAuditory(name, columnsInfo);
		
		System.out.println(msg);
	}
	
	public void addAuditory(String name, int[] columnsInfo)
	{
		university.addAuditory(name, columnsInfo);
	}
	
	public void initializeAuditoriums()
	{
		for(int i=0; i<Main.AUD_NAMES.length; i++)
		{
			String audName = Main.AUD_NAMES[i];
			int[] columnsInfo = Main.COLUMNS_INFO[i];
			
			addAuditory(audName, columnsInfo);
		}
	}
	
	
}