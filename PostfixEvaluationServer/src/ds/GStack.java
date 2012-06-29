package ds;

public class GStack<T> {

	T[] myStack;
	
	int top;
	
	public GStack(){
		top = -1;
	}
	
	
	public void push( T c ){

		if (top < 10 ){
			myStack[++top] = c;
		}else{
			System.err.println("Stack overflow");
		}
	}
	
	public T pop(){
		if (top != -1){
			return myStack[top--];	
		}else 
			System.err.println("Stack underflow");
			System.exit(1);
			return myStack[0];
	}
	
	public T peek(){
		if (isEmpty()){
			System.err.println("Stack Empty");
			System.exit(9);
			return myStack[0];
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