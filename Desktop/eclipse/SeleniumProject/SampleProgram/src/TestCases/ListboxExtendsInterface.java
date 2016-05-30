package TestCases;

import Interfaces.ElementImpl;
import Interfaces.ListBoxImpl;

public class ListboxExtendsInterface implements ElementImpl,ListBoxImpl {

	public void selectByIndex(){
		System.out.println("Hi");
	}
	
	public void selectByVisibleText(){
		System.out.println("Everyone!!!!");
	}
	
	public void safeSetSecure() {
		System.out.println("This is....");
	}

	public void sendKeys() {
		System.out.println("Selenium World!!!!");
	}
	
	public static void main(String[] args){
		ListboxExtendsInterface listbox = new ListboxExtendsInterface();
		listbox.selectByIndex();
		listbox.selectByVisibleText();
		listbox.safeSetSecure();
		listbox.sendKeys();
	}


}
