package logic;

import java.util.ArrayList;

public class ValidateInput {

	ArrayList<Character> oplist = new ArrayList<Character>();
	
	public Boolean isOkay(String input){
		
		
		Boolean status = true;
		
		// Supported operators
		oplist.add('+');
		oplist.add('-');
		oplist.add('*');
		oplist.add('/');
		
		String testString = input.trim();
		
		// check for digits
		for (int i=0; i< testString.length(); i++){
			if (Character.isDigit(testString.charAt(i))){
				i++;
			}else{
				status = false;
				break;
			}
		}
		
		// check for operators
		for (int i=1; i< testString.length(); i++){
			if (oplist.contains(testString.charAt(i))){
				i++;
			}else{
				status = false;
				break;
			}
		}
		
		return status;
	}
}
