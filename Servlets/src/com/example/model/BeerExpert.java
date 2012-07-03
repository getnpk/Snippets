package com.example.model;

import java.util.*;

public class BeerExpert {

	public ArrayList<String> getBrands(String color){
		ArrayList<String> brands = new ArrayList<String>();
		
		if (color.equals("green")){
			brands.add("green beer");
			brands.add("super slimy beer");
		}else if (color.equals("light")){
			brands.add("white beer");
			brands.add("slimy beer");
		}else{
			brands.add("regular draught beer");
		}
		return brands;
	}
}
