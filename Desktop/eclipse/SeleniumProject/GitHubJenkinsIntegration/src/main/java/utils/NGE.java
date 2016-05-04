package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.lang3.time.StopWatch;
import org.testng.annotations.Test;

import core.WebDriverSetup;
import utils.dataFactory.database.Recordset;
import utils.dataFactory.database.databaseImpl.OracleDatabase;

public class NGE {
	//Define a base query with values that indicate areas that need to be modified at runtime
	private String query = "SELECT * FROM DRMSLOG.EVT_TRC where EVT_TRC_SBSCRBR_VAL = 'WDW_NGE' "
			+ "and EVT_TRC_CREATE_DTS < to_date('{startTime}','YYYY-MM-DD:HH24:MI:SS') "
			+ "and EVT_TRC_CREATE_DTS > to_date('{endTime}','YYYY-MM-DD:HH24:MI:SS') "
			+ "and EVT_TRC_CNVRSTN_ID_VAL = '{conversationID}'" + "and EVT_TRC_EVT_TYP_NM = '{eventType}' "
			+ "and EVT_TRC_BUS_ENTTY_VAL = '{reservationNumber}' "
			+ "and EVT_TRC_STEP_VAL != 'SET_TARGET_UP'";
	private String queryOrder = "order by  EVT_TRC_CREATE_DTS desc";
	private String database = "DREAMS_LOG";
	private long timeout = 0;

	/**
	 * 
	 * @param environment - Dreams Log environment to query
	 * @param endTime - The maximum date-time stamp with which to search for records (i.e. the events should have occurred prior to this time)
	 * @param startTime - The minimum date-time stamp with which to search for records (i.e. the events should have occurred before this time)
	 * @param conversationId - conversation ID as indicated by the application
	 * @param eventType - expected type of event for which to search
	 * @param reservationNumber - expected reservation number expected to be linked to NGE events
	 * @param expectedNumberOfEvents - number of events expected from this query
	 */
	public void verifyNgeEvents(String environment, String endTime, String startTime, String conversationId,
			String eventType, String reservationNumber, int expectedNumberOfEvents) {
		boolean recordsFound = false;
		StopWatch watch = new StopWatch();
		
		if(timeout == 0){
			try{
				timeout = WebDriverSetup.getDefaultTestTimeout();
			}catch(NumberFormatException nfe){
				timeout = 90;
			}
		}
		
		TestReporter.log("Begin query for NGE records.");
		TestReporter.log("ENVIRONMENT: " + environment);
		TestReporter.log("END TIME: " + endTime);
		TestReporter.log("START TIME: " + startTime);
		TestReporter.log("CONVERSATION ID: " + conversationId);
		TestReporter.log("EVENT TYPE: " + eventType);
		TestReporter.log("RESERVATION NUMBER: " + reservationNumber);
		TestReporter.log("EXPECTED NUMBER OF EVENTS: " + expectedNumberOfEvents);
		
		query = query.replace("{startTime}", startTime).replace("{endTime}", endTime)
				.replace("{conversationID}", conversationId).replace("{eventType}", eventType)
				.replace("{reservationNumber}", reservationNumber);
		
		if(conversationId.isEmpty()){
			query = query.replace("and EVT_TRC_CNVRSTN_ID_VAL = ''", " ");
		}

		if(environment.trim().equalsIgnoreCase("grumpy")){
			query = query.replace("DRMSLOG", "COMMON");
			database = "DREAMS";
		}
		
		if(conversationId.equalsIgnoreCase("remove")){
			query = query.replace("and EVT_TRC_CNVRSTN_ID_VAL = 'REMOVE'", " ");
		}
		
		TestReporter.logNoHtmlTrim("NGE Query: " + query);
		System.out.println(query);
		OracleDatabase odb = new OracleDatabase(environment, database);
		Recordset resultSet = null;
		//Loop until the expected number of records are returned or until the test timeout is reached
		watch.start();
		do{
			resultSet = new Recordset(odb.getResultSet(query));
			//resultSet = odb.getResultSet(query);
			
			Sleeper.sleep(1000);
		}while(resultSet.getRowCount() < expectedNumberOfEvents && watch.getTime()/1000 < timeout);

		resultSet.print();
		TestReporter.assertTrue(resultSet.getRowCount() > 1, "The NGE events query did not return any records.");
		TestReporter.assertTrue(resultSet.getRowCount() == expectedNumberOfEvents,
				"The number of NGE events [" + String.valueOf(resultSet.getRowCount())
						+ "] did not match the expected number of events [" + String.valueOf(expectedNumberOfEvents)
						+ "]");

		TestReporter.log("Record Count: " + String.valueOf(resultSet.getRowCount()));
		//printNgeEventsData(resultSet);
		resetQuery();
	}
	
	public void verifyNgeEvents(String environment, String endTime, String startTime, String conversationId,
			String eventType, String reservationNumber) {
		boolean recordsFound = false;
		StopWatch watch = new StopWatch();
		timeout = WebDriverSetup.getDefaultTestTimeout();
		
		TestReporter.log("Begin query for NGE records.");
		TestReporter.log("ENVIRONMENT: " + environment);
		TestReporter.log("END TIME: " + endTime);
		TestReporter.log("START TIME: " + startTime);
		TestReporter.log("CONVERSATION ID: " + conversationId);
		TestReporter.log("EVENT TYPE: " + eventType);
		TestReporter.log("RESERVATION NUMBER: " + reservationNumber);
		
		query = query.replace("{startTime}", startTime).replace("{endTime}", endTime)
				.replace("{conversationID}", conversationId).replace("{eventType}", eventType)
				.replace("{reservationNumber}", reservationNumber);

		if(environment.equalsIgnoreCase("grumpy")){
			query = query.replace("DRMSLOG", "COMMON");
			database = "DREAMS";
		}
		
		if(conversationId.equalsIgnoreCase("remove")){
			query = query.replace("and EVT_TRC_CNVRSTN_ID_VAL = 'REMOVE'", " ");
		}
		
		TestReporter.log("NGE Query: " + query);
		
		OracleDatabase odb = new OracleDatabase(environment, database);
		Object[][] resultSet;
		//Loop until the expected number of records are returned or until the test timeout is reached
		watch.start();
		do{
			resultSet = odb.getResultSet(query);
			Sleeper.sleep(1000);
//		}while(resultSet.length <= expectedNumberOfEvents && watch.getTime()/1000 < WebDriverSetup.getDefaultTestTimeout());
		}while(resultSet.length <= 2 && watch.getTime()/1000 < timeout);

		TestReporter.assertTrue(resultSet.length > 1, "The NGE events query did not return any records.");

		TestReporter.log("Record Count: " + String.valueOf(resultSet.length - 1));
		printNgeEventsData(resultSet);
		resetQuery();
	}
	
	/**
	 * 
	 * @param environment - Dreams Log environment to query
	 * @param endTime - The maximum date-time stamp with which to search for records (i.e. the events should have occurred prior to this time)
	 * @param startTime - The minimum date-time stamp with which to search for records (i.e. the events should have occurred before this time)
	 * @param conversationId - conversation ID as indicated by the application
	 * @param eventType - expected type of event for which to search
	 * @param reservationNumber - expected reservation number expected to be linked to NGE events
	 * @param expectedNumberOfEvents - number of events expected from this query
	 */
	public void verifyNgeEvents(String environment, String endTime, String startTime, Map<String, String> conversationIds,
			String eventType, String reservationNumber, int expectedNumberOfEvents) {
		
		String conversationId = "";
		for (Map.Entry<String, String> entry : conversationIds.entrySet())
		{
		    conversationId = conversationId + "'" + entry.getValue() + "',";
		}
		conversationId = conversationId.substring(0, conversationId.lastIndexOf(","));
		
		query = query.replace("EVT_TRC_CNVRSTN_ID_VAL = '{conversationID}'", "EVT_TRC_CNVRSTN_ID_VAL IN ("+ conversationId+ ")");
		String envCompare = environment.toLowerCase().replace(" ", "");
		if(!envCompare.equalsIgnoreCase("doc") && !envCompare.equalsIgnoreCase("evilqueen")){
			verifyNgeEvents(environment, endTime, startTime, conversationId,
					eventType, reservationNumber, expectedNumberOfEvents);
		}else{
			TestReporter.logStep("NGE VALIDATIONS SKIPPED FOR ["+envCompare.toUpperCase()+"] ENVIRONMENT.");
		}
	}
	
	public void verifyNgeEvents(String environment, String endTime, String startTime, Map<String, String> conversationIds,
			String eventType, String reservationNumber, int expectedNumberOfEvents, int timeout){
		this.timeout = timeout;
		verifyNgeEvents(environment, endTime, startTime, conversationIds,
				eventType, reservationNumber, expectedNumberOfEvents);
	}
	
	/**
	 * 
	 * @param environment - Dreams Log environment to query
	 * @param endTime - The maximum date-time stamp with which to search for records (i.e. the events should have occurred prior to this time)
	 * @param startTime - The minimum date-time stamp with which to search for records (i.e. the events should have occurred before this time)
	 * @param conversationId - conversation ID as indicated by the application
	 * @param eventType - expected type of event for which to search
	 * @param reservationNumber - expected reservation number expected to be linked to NGE events
	 * @param expectedNumberOfEvents - number of events expected from this query
	 */
	public void verifyNgeEvents(String environment, String endTime, String startTime, Map<String, String> conversationIds,
			String eventType, Map<String, String> reservationNumbers, int expectedNumberOfEvents) {
		
		String reservationNumber = "";
		for (Map.Entry<String, String> entry : conversationIds.entrySet())
		{
			reservationNumber = reservationNumber + "'" + entry.getValue() + "',";
		}
		reservationNumber = reservationNumber.substring(0, reservationNumber.lastIndexOf(","));
		
		query = query.replace("EVT_TRC_BUS_ENTTY_VAL = '{reservationNumber}", "EVT_TRC_BUS_ENTTY_VAL IN ("+ reservationNumber+ ")");
		
		verifyNgeEvents(environment, endTime, startTime, conversationIds,
				eventType, reservationNumber, expectedNumberOfEvents);
	}
	
	/**
	 * 
	 * @param environment - Dreams Log environment to query
	 * @param endTime - The maximum date-time stamp with which to search for records (i.e. the events should have occurred prior to this time)
	 * @param startTime - The minimum date-time stamp with which to search for records (i.e. the events should have occurred before this time)
	 * @param conversationId - conversation ID as indicated by the application
	 * @param eventType - expected type of event for which to search
	 * @param reservationNumber - expected reservation number expected to be linked to NGE events
	 * @param expectedNumberOfEvents - number of events expected from this query
	 */
	public void verifyNgeEvents(String environment, String endTime, String startTime, Map<String, String> conversationIds,
			String eventType, String[] reservationNumbers, int expectedNumberOfEvents) {
		
		String conversationId = "";
		for (Map.Entry<String, String> entry : conversationIds.entrySet())
		{
		    conversationId = conversationId + "'" + entry.getValue() + "',";
		}
		conversationId = conversationId.substring(0, conversationId.lastIndexOf(","));
		query = query.replace("EVT_TRC_CNVRSTN_ID_VAL = '{conversationID}'", "EVT_TRC_CNVRSTN_ID_VAL IN ("+ conversationId+ ")");
		
		
		String reservationNumber = "";
		for (String resNum : reservationNumbers)
		{
			reservationNumber = reservationNumber + "'" + resNum + "',";
		}
		reservationNumber = reservationNumber.substring(0, reservationNumber.lastIndexOf(","));	
		query = query.replace("EVT_TRC_BUS_ENTTY_VAL = '{reservationNumber}'", "EVT_TRC_BUS_ENTTY_VAL IN ("+reservationNumber+")");
		
		verifyNgeEvents(environment, endTime, startTime, conversationId,
				eventType, reservationNumber, expectedNumberOfEvents);
	}
	
	

	private void printNgeEventsData(Object[][] events) {
		String[][] eventsArray = new String[events.length][events[0].length];
		for (int row = 0; row < events.length; row++) {
			for (int column = 0; column < events[0].length; column++) {
				eventsArray[row][column] = events[row][column].toString();
			}
		}
		new Recordset(eventsArray).print();
	}
	
	private void resetQuery(){
		query = "SELECT * FROM DRMSLOG.EVT_TRC where EVT_TRC_SBSCRBR_VAL = 'WDW_NGE' "
				+ "and EVT_TRC_CREATE_DTS < to_date('{startTime}','YYYY-MM-DD:HH24:MI:SS') "
				+ "and EVT_TRC_CREATE_DTS > to_date('{endTime}','YYYY-MM-DD:HH24:MI:SS') "
				+ "and EVT_TRC_CNVRSTN_ID_VAL = '{conversationID}'" + "and EVT_TRC_EVT_TYP_NM = '{eventType}' "
				+ "and EVT_TRC_BUS_ENTTY_VAL = '{reservationNumber}' ";
	}
	
	public static String generateCurrentDatetime() {
		String adDate = "1963-01-01";
		DateFormat dfms = new SimpleDateFormat("yyyy-MM-dd"); // XML date time
		DateFormat dfmst = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String Date = dfms.format(cal.getTime());
		String time = dfmst.format(cal.getTime());
		adDate = Date + " " + time;

		return adDate;
	}

	@Test
	public void test() {
		System.setProperty("selenium.default_timeout", "60");
		verifyNgeEvents("sleepy", "2015-12-28 14:28:06", 
				"2015-12-28 14:31:34", "REMOVE",
				"ChargeAccountBusinessEvent", "400", 6);
		resetQuery();
	}
}


