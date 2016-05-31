package BasicJavaPrograms.strings;

public class splitFuncExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("------------------------------------------------"); 
		String str = "This program splits a string based on space";
		 String[] tokens = str.split(" ");
		 for(String s: tokens){
			 System.out.println(s);
		 }
		
		 System.out.println("------------------------------------------------"); 
		
		 //Length of a string
		 String str1 = "Hello - world !";
		 int strLength = str1.length();
		 System.out.println("Length of a string : "+strLength);
		 
		 System.out.println("------------------------------------------------"); 
		 String str2 = "Hi Every";
		 String str3 = "One";
		 //String strConcat = str2+str3;
		 String strContactMethod = str2.concat(str3);
		 System.out.println("Concatinated String value is : "+strContactMethod);
		 System.out.println("------------------------------------------------"); 
	}

}
