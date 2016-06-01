package Utility;

import org.openqa.selenium.WebElement;

public class CheckboxesHandling {

	public static void selectTheCheckBox(WebElement element){
		try{
			if(element.isSelected()){
				System.out.println("Check-box: "+ element +"is already selected.");
			}else {
				element.click();
			}
		}catch(Exception e){
			System.out.println("Unable to select the check-box: "+ element);
			e.printStackTrace();
		}
		
	}
			
}
