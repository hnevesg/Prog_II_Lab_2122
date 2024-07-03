
public class Reservation {

	private String ID; // of reservation
	private int days;
	private Accomodation ac;
	private String idClient;
	private String name;
	private long card;
			
	public Reservation(String i, int d, Accomodation a, String I, String n, long c) {
		ID=i;
		days=d;
		ac=a;
		idClient=I;
		name=n;
		card=c;
	}
	
	public String getID() {
		return ID;
	}
	
	public int getDays() {
		return days;
	}
	public void setDays(int d) {
		days = d;
	}
	public Accomodation getAccomodation() {
		return ac;
	}
	public void setAccomodation(Accomodation a) {
		ac = a;
	}
	public String getIdClient() {
		return idClient;
	}

	public String getName() {
		return name;
	}

	public long getCard() {
		return card;
	}

	public boolean AccomodationhasBbq(){
		return ac.hasBBQ();
	}
	public String toString() {
		return ac.toString() + " Reservation ID: " + ID + ", number of days: " + days +".";
	}
}
