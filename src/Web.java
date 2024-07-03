
public class Web {

	double percentage;
	String name;
 
	public Web(double p, String n){
		percentage=p;
		name=n;
	}
 
	public String getName() {
		return name;
	}
 
	public void setPercentage(double d) { 
		percentage=d*0.01;
	}
 
	public double calculatePercentage(double p) {
		return percentage=p*0.01;
	}
	
 	public String toString() {
 		return "The web platform name is " + name + ", and the percentage it obtains is " + percentage;
 	}
	
}
