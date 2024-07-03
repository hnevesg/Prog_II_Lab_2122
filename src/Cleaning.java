
public class Cleaning {
	int charge, material;
 	String name;
 
 	public Cleaning(int c, int m, String n){
 		charge=c;
 		material=m;
 		name=n;
 	}
 
 	public String getName(){
 		return name;
 	}
   	public void setName(String n) {
 		name=n;
 	}
 
   	public int getMaterial(){
 		return material;
   	}
 	public void setMaterial(int m){
 		material=m;
 	}
   	public int getCharge(){
 		return charge;
 	}
   	public void setCharge(int c){
 		charge=c;
 	}
   	
 	public String toString() {
 		return "The cleaning company name is " + name + ", the cost of the material needed is " + material + ", and the amount to pay for the cleaning is " + charge;
 	}
 	
}
