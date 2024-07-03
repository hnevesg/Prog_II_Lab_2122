
public class Cabin extends Accomodation {
	private int rooms;
	private boolean wifi;

	public Cabin (char t, String i, int pr, int p, int a, int r, boolean w) {
		super(t,i, pr, p, a);
		rooms=r;
		wifi=w;
	}
	
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int r) {
		rooms=r;
	}
	public boolean getWifi() {
		return wifi;
	}
	public void setWifi(boolean w) {
		wifi=w;
	}
	
	public boolean hasBBQ() {
		return true;
	}
	
	public String toString() {
		return super.toString() + ", the number of rooms is " + rooms + ", and about wifi " + wifi + ".";	
	}
	
}
