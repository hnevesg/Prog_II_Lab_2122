
public class Pitches extends Accomodation{
	private boolean bbq;
	private boolean campervan;
	
	public Pitches (char t, String i, int pr, int p, int a, boolean b, boolean c) {
		super(t, i, pr, p, a);
		bbq=b;
		campervan=c;
	}

	public boolean hasCampervan() {
		return campervan;
	}	
	
	public boolean hasBBQ() {
		return bbq;
	}
	
	public String toString() {
	return super.toString() + ", about the bbq " + bbq + ", and about the campervan " + campervan + ".";	
	}
	
}
