package ds;

public class MyIntStack {

	int[] myStack = new int[20];
	
	int top = -1;
	
	
	public void push( int c ){

		if (top < 10 ){
			myStack[++top] = c;
			//System.out.println("added " + c);
		}else{
			System.err.println("Stack overflow");
		}
	}
	
	public int pop(){
		if (top != -1){
			//System.out.println(myStack[top]);
			return myStack[top--];	
		}else 
			System.err.println("Stack underflow");
			System.exit(1);
			return 0;
	}
	
	public int peek(){
		if (isEmpty()){
			System.err.println("Stack Empty");
			System.exit(9);
			return 0;
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