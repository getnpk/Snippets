package logic;

import ds.MyStack;
import ds.GStack;

public class InfixToPostfix {

	String infixString = "a*d-c+f/5";
	
	// use Generic stack, not done.
	GStack<String> thestack = new GStack<String>();

	public static int pre(char c){
		if (c == '+' || c == '-')
			return 10;
		else 
			return 1000;
	}
	
	public String getValue(String input){
		
		infixString = input;
		
		char[] cpostfix = new char[10];
		
		int index = 0;
		
		System.out.println("Infix exp : " + infixString);
		
		MyStack s = new MyStack();
		
		for (char c : infixString.toCharArray()){
			
			if (Character.isLetterOrDigit(c)){
				cpostfix[index] = c;
				index++;
			}else{
				if (s.isEmpty())
					s.push(c);
					
				else{
					
					if ( pre(c) <= pre(s.peek()) ){
						while ( !(s.isEmpty()) && (pre(c) <= pre(s.peek()) )){
							
							if (!s.isEmpty()){
							char a = s.pop();
							cpostfix[index] = a;
							index++;
							//System.out.println(cpostfix);
							
							}
						}
						s.push(c);
					}else{
						s.push(c);
					}
				}
			}
		}
		
		while (!s.isEmpty()){
			char a = s.pop();
			cpostfix[index] = a;
			index++;
		}
		
		System.out.print("Postfix exp: ");
		System.out.println(cpostfix);
		
		
		return new String(cpostfix);
	} 
}