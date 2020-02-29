class Fraction {
	public String name;
	public String age;
	public Fraction(String name, String age ){
		this.name=name;
		this.age=age;
	}
	public String toString(){
		return this.name;
	}
	public String toString1(){
		return this.age;
	}
}

public class Main {
	 public static void main(String[] args) {
		 Fraction fc= new Fraction("bin","1 an");
		 System.out.println(fc.toString());
		 System.out.println(fc.toString1());
	 }
}
