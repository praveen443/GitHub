package utils;

import org.testng.Assert;

public class ArrayUtil {
	
	
	//Compares given two array data one to one and validates the elements are not same
    public void compareArrayOneToOne(String[] Value1, String[] Value2){
    	
    	for(int ArrayIterator = 0; ArrayIterator<=Value1.length-1; ArrayIterator++ ){
    		if(Value1[ArrayIterator] != Value2[ArrayIterator]){
    			TestReporter.logStep("Previous Value ["+Value1[ArrayIterator]+"] changed to Present Value ["+Value2[ArrayIterator]+"]");
    			Assert.assertNotEquals(Value1[ArrayIterator], Value2[ArrayIterator]);
    		}
    		
    	}
    	
    }
    
  //Compares given two array data one to one and validates the elements are same
    public void compareArrayOneToOneSame(String[] Value1, String[] Value2){
    	TestReporter.logStep("In compare arrary");
    	for(int ArrayIterator = 0; ArrayIterator<=Value1.length-1; ArrayIterator++ ){
    		if(Value1[ArrayIterator].trim().equals(Value2[ArrayIterator].trim())){
    			TestReporter.logStep("Previous Value ["+Value1[ArrayIterator]+"] is same as Present Value ["+Value2[ArrayIterator]+"]");
    			Assert.assertEquals(Value1[ArrayIterator], Value2[ArrayIterator]);
    		}
    		
    	}
    	
    }

}

