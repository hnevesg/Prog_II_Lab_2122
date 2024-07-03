
public class VIP extends Client{
	private int vipCard;
		
	public VIP(String i, String name, long card, boolean v, int c) {
		super(i, name, card, v);
		vipCard=c;
	}
	
	public int getVipcard() {
		return vipCard;
	}
		
	public int calculateDiscount(){
		int result = 10; 	// calculate if there is another extra discount
		if(nreservations>1){
			result += 5;
		}
		return result;
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
	
	public String toString(){
			return super.toString() + "Vip card number: " + vipCard;
	}

}
