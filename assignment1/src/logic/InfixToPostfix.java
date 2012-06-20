package logic;

import ds.MyStack;

public class InfixToPostfix {

	String infixString = "a*d-c+f/5";
	
	public static int pre(char c){
		if (c == '+' || c == '-')
			return 10;
		else 
			return 1000;
	}
	
	public String getValue(String input){
	
		infixString = input;
		
		char[] cpostfix = new char[20];
		
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
					
					while ((pre(c) <= pre(s.peek()) &&  !(s.isEmpty()))){
						
						if (!s.isEmpty()){
						char a = s.pop();
						cpostfix[index] = a;
						index++;
						
						}
					}
					s.push(c);
					
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