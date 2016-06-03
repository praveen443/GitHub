package utils.dataFactory.database.staging;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Reporter;

import utils.dataFactory.staging.bookResortReservation.BookResortReservation;

public class StageResortReservations_Json {
	    private JSONObject json_accommodation;
		private JSONObject json_partymixinfo;
		private String reservationNumber = "";
	    private String reosrt = "";
	    private String guestFirstName = "";
	    private String guestLastName = "";
	    private String roomTypeCode ="";
	    Reservation reservation = null;
	    Map<String, String> status = new HashMap<String, String>();
	
	    /**
		    * 
		    * @summary Method to create a reservation using Quick Book API's
		    * @version Created 08/21/2015
		    * @author  Brajesh Ahirwar
		    * @param   JSON file  
		    * @param   environment 
		    * @throws NA
		    * @return  guest first name,guest last name and Reservation number 
		    *
	     */
	public Map<String, String> bookReservation(JSONObject myjson, String environment) throws JSONException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		    json_partymixinfo  = myjson.getJSONObject("partymixinfo");
		    json_accommodation = myjson.getJSONObject("accommodationInfo");
		    reosrt             = json_accommodation.getString("resort");
		    roomTypeCode	   = json_accommodation.getString("roomTypeCode");
		    guestFirstName     = guestName(json_partymixinfo.getString("firstname"));
		    guestLastName      = guestName(json_partymixinfo.getString("lastname"));
		    status.put("firstName", guestFirstName);
		    status.put("lastName", guestLastName);
	        Class<?> clazz = Class.forName("com.disney.utils.dataFactory.staging.bookResortReservation.BookResortReservation");
			Method method = clazz.getDeclaredMethod(reosrt, String.class);
			reservation = (Reservation) method.invoke(new BookResortReservation(), environment);
	        reservation.setNumberOfAdults(json_partymixinfo.getString("number_adult"));
			reservation.setPrimaryGuestFirstName(guestFirstName);
			reservation.setPrimaryGuestLastName(guestLastName);
			reservation.setRoomTypeCode(roomTypeCode);
			reservation.quickBook();
		    reservationNumber =reservation.getTravelPlanSegmentId();
		    Reporter.log(" Reservation Number : " + reservation.getTravelPlanSegmentId() + "</br>");
			status.put("Reservation", reservationNumber);
				
			return status;
			}
		
	 /**
	    * 
	    * @summary Method to create a groupreservation using Quick Book API's
	    * @version Created 09/07/2015
	    * @author  Brajesh Ahirwar
	    * @param   JSON file  
	    * @param   environment 
	    * @throws NA
	    * @return  guest first name,guest last name and Reservation number 
	    *
  */
public Map<String, String> bookReservationGroup(JSONObject myjson, String environment) throws JSONException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	
	    json_partymixinfo  = myjson.getJSONObject("partymixinfo");
	    json_accommodation = myjson.getJSONObject("accommodationInfo");
	    reosrt             = json_accommodation.getString("resort");
	    roomTypeCode	   = json_accommodation.getString("roomTypeCode");
	    guestFirstName     = guestName(json_partymixinfo.getString("firstname"));
	    guestLastName      = guestName(json_partymixinfo.getString("lastname"));
	    status.put("firstName", guestFirstName);
	    status.put("lastName", guestLastName);
	    Class<?> clazz = Class.forName("com.disney.utils.dataFactory.staging.bookResortReservation.BookResortReservation");
		Method method = clazz.getDeclaredMethod(reosrt, String.class);
		reservation = (Reservation) method.invoke(new BookResortReservation(), environment);
		reservation.setNumberOfAdults(json_partymixinfo.getString("number_adult"));
		reservation.setPrimaryGuestFirstName(guestFirstName);
		reservation.setPrimaryGuestLastName(guestLastName);
		reservation.setRoomTypeCode(roomTypeCode);
		// By default its book a reservation for group code 01905 
		reservation.bookGroupBooking();
        reservationNumber =reservation.getTravelPlanSegmentId();
	    Reporter.log(" Reservation Number : " + reservation.getTravelPlanSegmentId() + "</br>");
		status.put("Reservation", reservationNumber);
		status.put("Group", "01905");
	    return status;
		}



/**
    * 
    * @summary Method to create a reservation using Quick Book API's
    * @version Created 10/06/2015
    * @author  Brajesh Ahirwar
    * @param   JSON file  
    * @param   environment 
    * @throws NA
    * @return  guest first name,guest last name and Reservation number 
    *
 */
public Map<String, String> bookResForInventory(JSONObject myjson, String environment,String rtcode ) throws JSONException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

    json_partymixinfo  = myjson.getJSONObject("partymixinfo");
    json_accommodation = myjson.getJSONObject("accommodationInfo");
    reosrt             = json_accommodation.getString("resort");
    guestFirstName     = guestName(json_partymixinfo.getString("firstname"));
    guestLastName      = guestName(json_partymixinfo.getString("lastname"));
    status.put("firstName", guestFirstName);
    status.put("lastName", guestLastName);
    Class<?> clazz = Class.forName("com.disney.utils.dataFactory.staging.bookResortReservation.BookResortReservation");
	Method method = clazz.getDeclaredMethod(reosrt, String.class);
	reservation = (Reservation) method.invoke(new BookResortReservation(), environment);
    reservation.setNumberOfAdults(json_partymixinfo.getString("number_adult"));
	reservation.setPrimaryGuestFirstName(guestFirstName);
	reservation.setPrimaryGuestLastName(guestLastName);
	reservation.setRoomTypeCode(rtcode);
	reservation.quickBook();
    reservationNumber =reservation.getTravelPlanSegmentId();
    Reporter.log(" Reservation Number : " + reservation.getTravelPlanSegmentId() + "</br>");
	status.put("Reservation", reservationNumber);
		
	return status;
	}


//Method to split a String  and return a Guest  name 
   public String guestName(String guestNames)
   {
	   
	    Random rand = new Random();
		String guestName[] = guestNames.split(";");
	    String name =guestName[rand.nextInt(10)];
        return name;   
   }
}

