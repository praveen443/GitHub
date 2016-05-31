package BasicJavaPrograms.arrayList;

import java.util.ArrayList;
import java.util.Iterator;

import Utility.TestReporter;

/**
 * @summary: Program to read all elements in ArrayList by using iterator.
 * @author : Praveen Namburi
 * @version: 24-05-2016
 */

public class ArrayListIterator {
	public static void main(String[] args){
		
		ArrayList<String> arrayList =  new ArrayList<String>();
		//Adding elements to the end
		arrayList.add("First");
		arrayList.add("Second");
		arrayList.add("Third");
		arrayList.add("Four");
		
		TestReporter.logStep("---------------------------------------------------------------");
		TestReporter.logStep("Program to read all elements in Arraylist by using Iterator");
		TestReporter.logStep("---------------------------------------------------------------");
		
		Iterator<String> Iterator = arrayList.iterator();
		while(Iterator.hasNext()){
			TestReporter.logStep(Iterator.next());
	    }
	
		ArrayList<Integer> arrayList1 =  new ArrayList<Integer>();
		arrayList1.add(1);
		arrayList1.add(10);
		arrayList1.add(100);
		arrayList1.add(1000);
		
		TestReporter.logStep("---------------------------------------------------------------");
		Iterator<Integer> arrayListIterator = arrayList1.iterator();
		while(arrayListIterator.hasNext()) {
			
			TestReporter.logStep(arrayListIterator.next());
		}
		TestReporter.logStep("---------------------------------------------------------------");
		
	}
}
