package BasicJavaPrograms.strings;

/**
 * @summary: Program to compare two String objects in java.
 * @author : Praveen Namburi
 * @version: Created 24-05-2016
 */
public class StringComparision {

	public static void main(String[] args) {
		
		String x="JUNK DATA";
		String y="junk data";
		
		/**
         * We cannot use '==' operator to compare two strings.
         * We have to use equals() method.
         */
		if(x.equalsIgnoreCase(y)){
			System.out.println("Both the Strings are equal.");
		}else {
			System.out.println("Strings x and y are not equal.");
		}
	}

}
