package fr.uvsq.lb21605345;
import java.io.IOException;
import java.util.Stack;


import java.util.ArrayList;
import java.util.Scanner;

abstract class Operation {
	static String symbole = "+-*/";  
  
    Operation(String symbole){
	   Operation.symbole=symbole;
    }
  
    abstract double eval(ArrayList<String> str) throws IOException;
}

class MoteurRPN extends Operation{
	double ResultatValue = 0; 
	String PLUS="+"; 
	String MOINS="-"; 
	String MULT="*"; 
	String DIV="/"; 
	String tmp="";
	
	MoteurRPN() {
		super(symbole);
	}
	
	String transfer(ArrayList<String> str) {
		Stack<String> pl = new Stack<String>();  
		
        for (String t : str) {  
		   if (!symbole.contains(t)) {    
			    pl.push(t);
		   }else{  
				String a = String.valueOf(pl.pop());  
				String b = String.valueOf(pl.pop());  
				
				if(t.equals(PLUS)) { 
					 tmp="("+a+PLUS+b+")";
					 pl.push(tmp);	 
			    }
				else if(t.equals(MOINS)) { 
					tmp="("+b+MOINS+a+")";
					pl.push(tmp);
				}
				else if(t.equals(MULT)) { 
					tmp="("+a+MULT+b+")";
					pl.push(tmp); 
		    	}
				else if(t.equals(DIV)) { 
					tmp="("+b+DIV+a+")";
					pl.push(tmp);
		       }			
		  }  
		}
		return tmp;  	
	}
	
	double eval(ArrayList<String> str) {
		Stack<String> pile = new Stack<String>();  
		
	        for (String t : str) {  
			   if (!symbole.contains(t)) {  
				    pile.push(t);  
				    
			   }else{  
					double a = Double.valueOf(pile.pop());  
					double b = Double.valueOf(pile.pop());  
					
					if(t.equals(PLUS)) { 
						
				    	pile.push(String.valueOf(a + b));  
				    }
					else if(t.equals(MOINS)) { 
						
				    	pile.push(String.valueOf(b - a));  
					}
					else if(t.equals(MULT)) { 
						
				    	pile.push(String.valueOf(a * b));  
			    	}
					else if(t.equals(DIV)) { 
						
				    	pile.push(String.valueOf(b / a));  
			       }
					/* switch (t) {  
					     case "+":  
					       stack.push(String.valueOf(a + b));  
					       break;  
					     case "-":  
					       stack.push(String.valueOf(b - a));  
					       break;  
					     case "*":  
					       stack.push(String.valueOf(a * b));  
					       break;  
					     case "/":  
					       stack.push(String.valueOf(b / a));  
					       break;  
					    } */ 
			  }  
			}  
			ResultatValue = Double.valueOf(pile.pop());  
			  
			return ResultatValue; 
		}

}

class SaisieRPN{
	Scanner sc = new Scanner(System.in) ;
	public void saisie_rpn(ArrayList<String> str) {	
		int len_max=100;
		int i=1;
		String s;
		while(i<=len_max) {	
			s= sc.nextLine();
			if("exit".equals(s.trim())){
				break;		
			}else{
				str.add(s);
			}
			i++;
		}
	}
}

public class CalculatriceRPN{
	 public static void main(String[] args)  {  
		 
		 ArrayList<String> str = new ArrayList<String>(); 
		 
		 SaisieRPN saisie=new SaisieRPN();
		 MoteurRPN mtRPN= new MoteurRPN();
		 
		 System.out.println("Inserer le polynome:");
		 saisie.saisie_rpn(str);			 
		 
		 System.out.println("L'expression courante est "+mtRPN.transfer(str));
		
		 System.out.println("Le resultat du ploynome est "+(double)mtRPN.eval(str));
	 }
}