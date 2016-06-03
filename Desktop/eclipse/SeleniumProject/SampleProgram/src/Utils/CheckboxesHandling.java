package Utils;

import org.openqa.selenium.WebElement;

public class CheckboxesHandling {

	public static void selectTheCheckBox(WebElement element){
		try{
			if(element.isSelected()){
				TestReporter.log("Check-box: "+ element +"is already selected.");
				//System.out.println("Check-box: "+ element +"is already selected.");
			}else {
				element.click();
			}
		}catch(Exception e){
			 TestReporter.log("Unable to select the check-box: "+ element);
			 //System.out.println("Unable to select the check-box: "+ element);
			 e.printStackTrace();
		}
		
	}
			
}
