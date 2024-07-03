
abstract public class Accomodation {
	
	protected String ID;
	protected int price;
	protected int people;
	protected int area;
	protected boolean availability;
	protected char type;
	
	public Accomodation(char t,String i, int pr, int p, int a) {
		type=t;
		ID=i;
		price=pr;
		people=p;
		area=a;
		availability=true;
	}
	
	abstract public boolean hasBBQ();
	
	public String getID() {
		return ID;
	}
	
	public boolean getAv() {
		return availability;
	}
	
	public int calculatePrice(int p) {
		return price=p;
	}
	
	public String toString() {
		return "Accomodation ID: " + ID + ", price: " + price + "€, number of people: " + people;
	}
}
