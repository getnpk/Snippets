package logic;

import java.util.ArrayList;
import ds.MyIntStack;

public class PostfixEvaluation {

	MyIntStack stack = new MyIntStack();
	int value;
	
	ArrayList<Character> oplist = new ArrayList<Character>();
	
	public int eval (int x, int y, char o){
		
		int val = 0;
		
		int a = x;
		int b = y;
		
		System.out.println(a);
		System.out.println(b);
		
		if (o == '+')
			val = a + b;
		else if (o == '-')
			val = a - b;
		else if (o == '*')
			val = a * b;
		else if (o == '/')
			val =  a / b;
		
		return val;
	}
	
	
	public int getValue(String postfix){
		
		int a,b;
		System.out.println(postfix);
		int topush;
		
		oplist.add('+');
		oplist.add('-');
		oplist.add('*');
		oplist.add('/');
			
		for (char c : postfix.trim().toCharArray()){
			
			if (oplist.contains(c)){
				a = stack.pop();
				b = stack.pop();				
				topush = eval(b,a,c); // last one first
				System.out.println("Pushing " + topush);
				stack.push(topush);
			}else{
				System.out.println("Pushing-> " + c);
				stack.push(Integer.parseInt(Character.toString(c)));
			}
		}
		
		//System.out.println("Peek value " + Integer.parseInt(Character.toString(stack.peek())));
		
		return stack.peek();
	}
}
