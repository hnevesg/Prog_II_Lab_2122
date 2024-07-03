import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mainClass implements I{
	static Scanner scan = new Scanner(System.in);
	static int nclients=fileClients;
	static int naccomodations=fileAccomodations;
	
	public static void main (String[]args) {
		Campsite c=new Campsite("El Mirador");
		Cleaning company=new Cleaning(15,2,"Limpieza y Mantenimiento S.L.");
		Web platform=new Web(webPercentage,"OnlineReservations.com");
		readAccomodation(c,"./Accomodation.txt");
		readClients(c,"./Clientes.txt");
		menu(c, platform, company, priceperNight);
	}
		
	public static void showInformation(Campsite c) { 
		System.out.println(c.infoAvailableAccomodation()); 
	}
	
	public static void calculatePriceofCleaning(Campsite c, Cleaning cl){
		System.out.println("The total price of cleaning all available cabins is " + c.prepareCleaningCabins(cl, cleaningCharge,cleaningMaterial) + "�.");
	}
	
	public static void bookAccomodation(Campsite c, int price) {
		System.out.println("Insert your ID:");
		String ID=scan.next().toUpperCase(); 
		System.out.println("And now the accommodation ID, to verify if it already exists:");
		String accID=scan.next().toUpperCase();
		
		int counter=0;
		int rooms=0;
		boolean wifi=false;
		boolean campervan=false;
		boolean bbq=false;
		
		
		if (c.existingClient(ID)!=-1){
			if(c.existingAccomodation(accID)!=-1){
				System.out.println("This accomodation already exists.");
			}else {
				
				int days=askDays();
				
				
					int p=askPeople();
					char t=askTypeAccomodation();
					int a=askArea();
					if(t=='c') {
						int r=askRooms();
						boolean w=askWifi();
						c.addAcc(t, accID, price, p, a,r,w,bbq,campervan,counter); 
						if(c.addClientReserv(ID, days, accID)==true) {
						System.out.println("The reservation has been successfully done.");	
						}else {
							counter=-1;
						}
					}
					if(t=='p') {
						boolean camp=askCampervan();
						boolean b=askBBQ();
						c.addAcc(t, accID, price, p, a,rooms,wifi,b,camp,counter); 
						if(c.addClientReserv(ID, days, accID)==true) {
						System.out.println("The reservation has been successfully done.");	
						}else {
							counter=-1;
						}
					}
			}
		}else {
			if(c.existingAccomodation(accID)!=-1){
				System.out.println("This accomodation already exists.");
			}else {
				String name=askClientName();
				long card=askClientCard();
				boolean VIP=askVIP();
				int vipCard=askVIPcard();
			c.addClient(ID, name, card, VIP, vipCard);
			
				int days=askDays();
				int p=askPeople();
				char t=askTypeAccomodation();
				int a=askArea();
				if(t=='c') {
					int r=askRooms();
					boolean w=askWifi();
					c.addAcc(t,accID, price, p, a,r,w,campervan,bbq,counter); 
					if(c.addClientReserv(ID, days, accID)==true) {
					System.out.println("The reservation has been successfully done.");	
					}else {
						counter=-1;
					}
				}
				if(t=='p') {
					boolean camp=askCampervan();
					boolean b=askBBQ();
					c.addAcc(t, accID, price, p, a,rooms,wifi,camp,b,counter);
					if(c.addClientReserv(ID, days, accID)==true) {
					System.out.println("The reservation has been successfully done.");	
					}else {
						counter=-1;
					}
				}
			}
		}
	}		

	public static void showDiscount (Campsite c, int price) { 
		System.out.println("Insert your ID:");
		String ID=scan.next().toUpperCase();
		System.out.println("And now the accommodation ID, to verify that it exists:");
		String accID=scan.next().toUpperCase();
		
		int days=askDays();
		if (c.existingClient(ID)!=-1){
			if(c.existingAccomodation(accID)!=-1){
				System.out.println("The price withouot discount is " + c.totalPrice(accID, days, price) + "�."); 
				System.out.print("The percentage applied is " + c.consultDiscount(ID) + "%.");
				if(c.consultDiscount(ID)>5) {
					System.out.println(" It has VIP discount.");
				}
				int result=c.totalPrice(accID, days, price)-(c.consultDiscount(ID)*c.totalPrice(accID, days, price)/100);
				System.out.println("The final price is " + result + "�.");
			}
			else{
				System.out.println("The accommodation does not exit. The price cannot be calculated.");	
			}
		}else {
			if(c.existingAccomodation(accID)!=-1){
				System.out.println("Since you are a new client, any discount is applied. The price is " + c.totalPrice(accID, days, price) + "�.");
			}else{
				System.out.println("The accommodation does not exit. The price cannot be calculated.");	
			}
		}
	}
		
	public static void reservationInfo(Campsite c) {
		System.out.println("Insert your ID:");
		String ID=scan.next().toUpperCase();
		if (c.existingClient(ID)!=-1){
			System.out.println(c.showReserv(ID));
		}
		else {
			System.out.println("Error. The client ID does not exist.");
		}
	}
	
	public static void profitWeb(Campsite c, Web w, int price) {
		System.out.println("The profit obtained by the Web platform is " + c.calculateWeb(w,price) + "�.");
	}
	
	public static void vipAccomodationswithBBQ(Campsite c) {
		System.out.println("Insert your ID:");
		String ID=scan.next().toUpperCase();
		if(c.existingClient(ID)!=-1) {
		System.out.println(c.showVIPReserv(ID));	
		}
		else {
			System.out.println("Error. The client ID does not exist.");
		}
	}
	
		public static void menu(Campsite c, Web w, Cleaning cl, int price) {
			int option=0;
			do {
			showOptions();
			boolean result=false;
			do {
			try {
					option=scan.nextInt();
					if(option<=0 || option>=9) throw new IndexOutOfBoundsException();
					result=false;
				}
				catch(InputMismatchException y){ 
					System.out.println("Error, letters and symbols are not allowed. Type a number:");
					scan.next();
					result=true;
				}
				catch(IndexOutOfBoundsException z) {
					System.out.println("Error, the number must be in the range [1,8]. Try again:");
					result=true;
				}
			}while(result);
			 			
			switch (option) {
			case 1: 
				showInformation(c);
				break;
			case 2:
				calculatePriceofCleaning(c,cl);
				break;
			case 3: 
				bookAccomodation(c,price);
				break;
			case 4:
				showDiscount(c,price);
				break;
			case 5: 
				reservationInfo(c);
				break;
			case 6:
				profitWeb(c,w,price);
				break;
			case 7:
				vipAccomodationswithBBQ(c);
				break;
			case 8:
				System.out.print("End of the program.");
				break;
			}
			System.out.println(" ");
		}while(option > 0 && option < 8);
	}


	public static void readAccomodation(Campsite c, String name){
		int counter=0;
		File f = new File (name); 
			Scanner namef=null;
			try {
				namef= new Scanner (f);
			} catch (FileNotFoundException e) {
				System.out.println("The accomodations file does not exist in that location.");
			}
			while (namef.hasNext()){
				char type=namef.next().charAt(0);
				String ID = namef.next();
				int price = namef.nextInt(), people = namef.nextInt(), area = namef.nextInt(), beds=0;
				boolean wifi=false,campervan=false,bbq=false;
				
				if (type=='c'){
					 beds = namef.nextInt();
					 wifi = namef.nextBoolean();
					
					c.addAcc(type,ID,price,people,area,beds,wifi,bbq,campervan,counter);
				}
				
				if (type=='p') {
					 bbq = namef.nextBoolean();
					 campervan = namef.nextBoolean();
					 c.addAcc(type,ID,price,people,area,beds,wifi,bbq,campervan,counter);
				}	
			}
			
			namef.close();
		}
	

	public static void readClients(Campsite c, String n) {
		
		File f = new File (n); 
		Scanner reader=null;
		try {
			reader = new Scanner (f);
		} catch (FileNotFoundException e) {
			System.out.println("The clients file does not exist in that location.");
		}
		while (reader.hasNext()){
			String name = reader.next();
			String ID = reader.next();
			long card = reader.nextLong();
			boolean VIP = reader.nextBoolean();
			int vipcard=0;
			if (VIP==true) {
				int vipCard=reader.nextInt();
				c.addClient(ID, name,card, VIP, vipCard);
				
			}else {
				c.addClient(ID, name,card, VIP, vipcard);
				
			}
			
		}
		reader.close();
	}
	
	public static int askArea() {
		System.out.println("In which zone do you want to make the reservation?");
		System.out.println("Enter 0 for 'area near the sinks and toilets'");
		System.out.println("Enter 1 for 'area near the swimming pool'");
		System.out.println("Enter 2 for 'quiet area'");
		boolean result=false;
		int area=0;
		do {
		try {
			area=scan.nextInt();
			if(area<0 || area>2) throw new IndexOutOfBoundsException();
			result=false;
		}
		catch(InputMismatchException a){
			System.out.println("Error, letters and symbols are not allowed. Type a number:");
			scan.next();
			result=true;
		}
		catch(IndexOutOfBoundsException z) {
			System.out.println("Error, the number must be in the range [0,2]. Try again:");
			result=true;
		}
		}while(result);
		return area;
	}
	
	public static char askTypeAccomodation() {
		System.out.println("In which type do you want to make the reservation?");
		System.out.println("Enter 'c' to reserve a cabin");
		System.out.println("Enter 'p' to reserve a pitch");
		char type='z';
		do {
			try {
				type=scan.next().toLowerCase().charAt(0);
				if(type!='c' & type!='p') throw new IndexOutOfBoundsException();
			}
			catch(IndexOutOfBoundsException z) {
				System.out.println("Error, numbers and symbols are not allowed, and the letter must be c or p. Try again:");
			}
		}while(type!='c' & type!='p');
		return type;
	}
	
	public static void showOptions() {
		System.out.println("Select one of the following options. If you want to exit, write 8.");
		System.out.println("1.See all information regarding accommodations");
		System.out.println("2.See how much it would cost to clean and prepare all cabins and replace the material");
		System.out.println("3.Reserve an accommodation");
		System.out.println("4.Consult the cost of reserving an accommodation");
		System.out.println("5.See all information regarding reservations particularly made");
		System.out.println("6.See Web company's profit");
		System.out.println("7.Reserved accommodations with barbeques by a particular VIP customer");	
		System.out.println("8.Exit");
	}
	
	public static int askDays() {
		System.out.println("How many days will be reserved?");
		boolean result=false;
		int ndays=0;
		do{
			try {
			ndays=scan.nextInt();
			if(ndays==0) throw new IndexOutOfBoundsException();
			result=false;
			}
			catch(InputMismatchException w){
				System.out.println("Error, letters and symbols are not allowed. Try again:");
				scan.next();
				result=true;
			}
			catch(IndexOutOfBoundsException z) {
				System.out.println("Error, enter a positive integer:");
				result=true;
			}
		}while(result);
		return ndays;
	}
	
	public static int askPeople() {
		System.out.println("And how many people will stay?");
		int people=0;
		boolean result=false;
		do {
			try {
				people=scan.nextInt();
				result=false;
			}
			catch(InputMismatchException z){
				System.out.println("Error, letters and symbols are not allowed. Try again:");
				scan.next();
				result=true;
			}
		}while(result);
	return people;
	}
	
	public static int askRooms() {
		System.out.println("You have selected a cabin. How many rooms do you want?");
		int rooms=0;
		boolean result=false;
		do {
			try {
				rooms=scan.nextInt();
				if(rooms==0) throw new IndexOutOfBoundsException();
				result=false;
			}
			catch(InputMismatchException z){
				System.out.println("Error, letters and symbols are not allowed. Try again:");
				scan.next();
				result=true;
			}
			catch(IndexOutOfBoundsException i) {
				System.out.println("Error, enter a positive integer. Please try again:");
				result = true;
			}
		}while(result);
		return rooms;
	}
	public static boolean askWifi() {
		boolean wifi=false;
		System.out.println("And you want it with wifi? If so enter 'yes'.");
		if("YES".equals(scan.next().toUpperCase())) {
		wifi=true;
		}
		return wifi;
	}
	public static boolean askCampervan() {
		boolean campervan=false;
		System.out.println("You have selected a pitch. Do you want it suitable for a campervan? If so enter 'yes'.");
		if("YES".equals(scan.next().toUpperCase())) {
			campervan=true;
			}
		return campervan;
	}
	public static boolean askBBQ() {
		boolean bbq=false;
		System.out.println("And do you want it with barbeque? If so enter 'yes'.");
		if("YES".equals(scan.next().toUpperCase())) {
			bbq=true;
			}
		return bbq;
	}
	public static String askClientName() {
		System.out.println("Since you are not registered, please enter the following information:");
		System.out.print("Name: ");
		String name=scan.next();
		return name;
	}
	public static long askClientCard(){
		System.out.print("Card number: ");
		long card=0;
		boolean result=false;
		do {
			try {
				card=scan.nextLong();
				result=false;
			}
			catch(InputMismatchException z){
				System.out.println("Error, letters and symbols are not allowed. Try again:");
				scan.next();
				result=true;
			}
		}while(result);
		return card;
	}
	public static boolean askVIP() {
		boolean VIP=false;
		System.out.println("Do you want to be a VIP customer? If so, enter 'yes'.");
		if("YES".equals(scan.next().toUpperCase())) {
			VIP=true;
		}
		return VIP;
	}
		
	public static int askVIPcard() {
			System.out.println("If you have chosen to be a VIP customer, write your VIP card number.");
			System.out.println("If you have not, write 0.");
			boolean resultado=false;
			int vipcard=0;
			do {
				try {
					vipcard=scan.nextInt();
					resultado=false;
				}
				catch(InputMismatchException z){
					System.out.println("Error, letters and symbols are not allowed. Try again:");
					scan.next();
					resultado=true;
				}
			}while(resultado);
		
		System.out.println("The information has been correctly saved. Thanks.");
		return vipcard;
	}

}