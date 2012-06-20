package ds;

public class MyStack {

	char[] myStack = new char[20];
	
	int top = -1;
	
	
	public void push( char c ){

		if (top < 10 ){
			myStack[++top] = c;
			//System.out.println("added " + c);
		}else{
			System.err.println("Stack overflow");
		}
	}
	
	public char pop(){
		if (top != -1){
			//System.out.println(myStack[top]);
			return myStack[top--];	
		}else 
			System.err.println("Stack underflow");
			System.exit(1);
			return 'z';
	}
	
	public char peek(){
		if (isEmpty()){
			System.err.println("Stack Empty");
			System.exit(9);
			return 'z';
		}else
			return myStack[top];
		
	}
	
	public boolean isEmpty(){
		if (top == -1)
			return true;
		else
			return false;
	}
	
}