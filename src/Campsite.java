
public class Campsite {
	private String name;
	private Client[] clients;
	private Accomodation[] accomodations;
	private int naccomodations=0;	
	private int nclients=0;
	
	public Campsite(String n) {
		name=n;
		accomodations=new Accomodation[80];
		clients=new Client[80];
		
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public Client[] getClients() {
		return clients;
	}
	public void setClients(Client[] c) {
		clients = c;
	}
	public Accomodation[] getAccomodations() {
		return accomodations;
	}
	public void setAccomodations(Accomodation[] a) {
		accomodations = a;
	}
	
	public int existingClient(String ID) {
		int k=-1;
		boolean noEncontrado=true;
		for(int i=0; i<nclients && noEncontrado==true;i++) {
			if(ID.equals(clients[i].getID())) {
				k=i;
				noEncontrado=false; 
			}
		}
		return k;
	}
	
	public int existingAccomodation(String accID) {
		int k=-1;
		boolean notFound=true;
		for(int i=0; i<naccomodations && notFound==true;i++) { 
			if(accID.equals(accomodations[i].getID())) {
				k=i;
				notFound=false;
			}
		}
		return k;
	}
		
	public int consultDiscount(String ID) { 
		int m=0;
		int position=existingClient(ID);
			if(position!=-1) {
				m=clients[position].calculateDiscount();
			}
		return m;
	}
	
	public String showReserv(String ID) {
		String s="";
		int position=existingClient(ID);
			if(position!=-1) {
				if(clients[position].getNreservations()==0) {
					s="There are no reservations made.";
				}else {
					s+=clients[position].consultReserv();
				}
			}
		return s;
	}
	
	public double prepareCleaningCabins(Cleaning cl, int charge, int material) {
		double counter=0;
		for(int i=0; i<naccomodations;i++) {
			if(accomodations[i].getAv() == true & accomodations[i] instanceof Cabin){
				counter+=cl.getCharge()+cl.getMaterial();
			}
		}
		return counter;
	}
	
	public int priceofCabinsWeek(int price) {
		int n=0;
		for(int i=0; i<naccomodations;i++) {
			if(accomodations[i] instanceof Cabin & accomodations[i].getAv()==true) {  
				n+=accomodations[i].calculatePrice(price)*7;
			}
		}
		return n;
	}
	
	public double calculateWeb(Web w, int price) {
		return priceofCabinsWeek(price)*w.calculatePercentage(5);
	}

	public String showVIPReserv(String ID) {
		String s="";
		int position=0;
		position=existingClient(ID);
		if (position!=-1) {
			if(clients[position] instanceof VIP) { 
				s+=clients[position].consultReserv();
			}
			else {
				s="This ID is not from a VIP client.";
			}
		}
		return s;
	}
	
	public boolean availabilityParticularAccomodation(String accID) {
		boolean k=false;
		int position=existingAccomodation(accID);
		if(position!= -1){
			for(int i=0; i<naccomodations;i++) {
				if(accomodations[position].getAv()==true) {
					k=true;
				}
			}
		}
		return k;
	}
	
	public String infoAvailableAccomodation() {
		String s="";
			for(int i=0; i<naccomodations;i++) {
				if(accomodations[i].getAv()==true) {
				s+=accomodations[i].toString();
				s+="\n";
				}
			}
		return s;
	}
		
	public int totalPrice(String accID, int ndays, int price) {
		int n=0;
		int  position=existingAccomodation(accID);
			if (position!=-1) {
				if(accomodations[position].getAv()==true) {
					n=ndays*accomodations[position].calculatePrice(price);
				}
			}
		return n;
	}
	
	public boolean addClientReserv(String ID, int days, String ReservID) {
		boolean result=false;
		int position=existingClient(ID);
			if(position!=-1) {
				try {
					if(clients[position].getNreservations()>=5) throw new ReservationException(); 
					clients[position].addReserv(ReservID, days, accomodations[naccomodations]);
					result=true;
				}
				catch (ReservationException b) {
					System.out.println("Error. You have already made 5 reservations.");
					result=false;
				}
				
			}else {
				result=true;
			}
		return result;
	}
	
	
	public boolean addAcc(char type,String accID, int price, int people, int area, int rooms, boolean wifi, boolean campervan, boolean bbq, int counter) {
		boolean booket=false;
		int position=existingAccomodation(accID);
		if(position==-1) {
			
			if(type=='c') {
				Accomodation ac = new Cabin (type,accID,price,people,area,rooms,wifi);
				accomodations[naccomodations++ + counter]=ac;
			}
			if(type=='p'){
				Accomodation ac = new Pitches (type,accID,price,people,area,bbq,campervan);
				accomodations[naccomodations++ + counter]=ac;
			}
			
			booket=true;
		}
		return booket;
	}
	
	public boolean addClient( String ID, String name,long card, boolean VIP, int vipcard) {
		boolean booket=false;
		int position=existingClient(ID);
		if(position==-1) {
			Client cl=null; 
			if(VIP==true) {
				cl=new VIP(ID, name, card, VIP, vipcard);
			}else {
				cl= new Client (ID, name, card, VIP);
			}
			clients[nclients++]=cl;
			
			booket=true;
		}
		return booket;
	}
}