package utils;

public class RemoveSpecialCharacters {

	
	/*
	 * Propose refactor this to be a String manipulation class
	 */
	
	public RemoveSpecialCharacters() {
		// TODO Auto-generated constructor stub
	}
	
	public String removeSpecialChars(String string){
		
		String finalString;
		
		finalString = string.replaceAll("'|,|&|-| ", "");
		/*finalString = string.replaceAll("'|,|&|-| ", "");*/
		
/*		finalString = string.replace(" ", "");
		finalString = finalString.replace("'", "");
		finalString = finalString.replace(",", "");
		finalString = finalString.replace("&", "");
		finalString = finalString.replace("-", "");*/
		
		return finalString;
	}

}

