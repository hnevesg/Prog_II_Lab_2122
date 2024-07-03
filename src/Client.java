
public class Client {
	
	protected String ID;
	protected String name;
	protected long card;
	protected boolean vip;
	protected int vipcard;
	protected int nreservations=0; // counter
	protected Reservation[] reservations;
	
	public Client( String iD, String n,long c, boolean v) {  
		name=n;
		ID = iD;
		card = c;
		vip=v;	
		reservations=new Reservation[5];
	}
	
	public int hasVipcard() {
		return vipcard;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String i) {
		ID = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	public long getCard() {
		return card;
	}
	public void setCard(long c) {
		card = c;
	}

	public Reservation[] getReservation() {
		return reservations;
	}
	public void setReservation(Reservation[] r) {
		reservations = r;
	}
	public int getNreservations() {
		return nreservations;
	}
	public void setNreservations(int n) {
		nreservations = n;
	} 
	
	public int calculateDiscount(){ // If a person has at least one reservation
		int discount=0;
		if(nreservations>=1){ 
			discount=5;
		}
		return discount;
	}
	
	public boolean addReserv(String id, int day, Accomodation ac){
		boolean booket=true;
		if(nreservations < 5) {
			Reservation reser = new Reservation (id, day, ac, ID, name, card);
			reservations[nreservations++]=reser;
			
		}else {
			booket=false;
		}
		return booket;
	}
	
	public String consultReserv(){
		String cadena = "";
		if(nreservations==0){  
				cadena= "Any reservation made.";
		}else {
			for(int i=0; i<nreservations; i++) {
					if(reservations[i].AccomodationhasBbq()==true){
						cadena+= "This reservation has bbq. " + reservations[i].toString();
						cadena+="\n";
					}else{
						cadena+= "This reservation doesn�t have bbq. " + reservations[i].toString();
						cadena+="\n";
					}
			}
		}
		return cadena;
	}

	public String toString() {
		return "Client ID: " + ID + ", card number: " + card + ", reservations made: " + reservations.toString() + ", and the total number of reservations is " + nreservations + ".";
	}
		
}
