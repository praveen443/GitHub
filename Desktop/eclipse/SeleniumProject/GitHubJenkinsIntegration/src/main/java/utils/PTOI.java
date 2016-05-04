package utils;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.dataFactory.database.Database;
import utils.dataFactory.database.Recordset;
import utils.dataFactory.database.databaseImpl.OracleDatabase;

public class PTOI {
	//Define a base query with values that indicate areas that need to be modified at runtime
	private String eaiQuery = "SELECT MESSAGE FROM EAI_SYSTEM_TEST.EVENT "
			+ "WHERE SERVICE_TYPE = 'Guest' "
			+ "and SERVICE_ACTION = 'Update' "
			+ "and TYPE = 'TOTAL_START' "
			+ "and CONVERSATION_ID In ({CONVOS}) "
			+ "ORDER BY CREATION_DATE DESC";
	private String dreamsQuery = "SELECT A.TXN_PTY_EXTNL_REF_VAL "
			+ "FROM GUEST.TXN_PTY_EXTNL_REF A, RES_MGMT.TPS B "
			+ "WHERE  A.TXN_PTY_ID = B.PRMY_PTY_ID "
			+ "AND A.PTY_EXTNL_SRC_NM = 'ODS' "
			+ "AND B.TPS_ID= {TPS_ID}";
	private String goMasterQuery = "SELECT A.Address_Market_Permission, A.Address_Market_Label, "
			+ "A.Address_Update_Timestamp, B.Email_Market_Permission, B.Email_Market_Label, "
			+ "B.Email_Update_Timestamp "
			+ "FROM(SELECT A.IDVL_ADDR_MKT_BRND_PRMSN_IN Address_Market_Permission, "
			+ "B.MKT_BRND_NM Address_Market_Label, A.UPDT_DT Address_Update_Timestamp "
			+ "FROM ODSP.TXN_IDVL_ADDR_MKT_BRND A, ODSP.MKT_BRND B "
			+ "WHERE A.MKT_BRND_ID = B.MKT_BRND_ID "
			+ "AND A.TXN_IDVL_ID = {ODS_GUEST} "
			+ "ORDER BY A.UPDT_DT DESC ) A, "
			+ "(SELECT A.IDVL_EML_MKT_BRND_PRMSN_IN Email_Market_Permission, "
			+ "B.MKT_BRND_NM Email_Market_Label, A.UPDT_DT Email_Update_Timestamp  "
			+ "FROM ODSP.TXN_IDVL_EML_MKT_BRND A, ODSP.MKT_BRND B "
			+ "WHERE A.MKT_BRND_ID = B.MKT_BRND_ID AND A.TXN_IDVL_ID = {ODS_GUEST} "
			+ "ORDER BY A.UPDT_DT DESC) B "
			+ "WHERE ROWNUM = 1";
	
	private String goMasterQueryNoEmail = "SELECT A.Address_Market_Permission, A.Address_Market_Label, "
			+ "A.Address_Update_Timestamp "
			+ "FROM(SELECT A.IDVL_ADDR_MKT_BRND_PRMSN_IN Address_Market_Permission, "
			+ "B.MKT_BRND_NM Address_Market_Label, A.UPDT_DT Address_Update_Timestamp "
			+ "FROM ODSP.TXN_IDVL_ADDR_MKT_BRND A, ODSP.MKT_BRND B "
			+ "WHERE A.MKT_BRND_ID = B.MKT_BRND_ID "
			+ "AND A.TXN_IDVL_ID = {ODS_GUEST} "
			+ "ORDER BY A.UPDT_DT DESC ) A "
			+ "WHERE ROWNUM = 1";
	
	private String convertAccoviaIdToTPSQuery = "SELECT TPS_ID FROM RES_MGMT.TPS_EXTNL_REF WHERE TPS_EXTNL_REF_VL = '{RES_ID}'";
	
	private String convertAccoviaGuestIdToDreamsQuery = "SELECT DISTINCT(pty.TXN_PTY_EXTNL_REF_VAL) "
			+ "FROM RES_MGMT.TC_GRP tcg, "
			+ "RES_MGMT.TC tc, "
			+ "RES_MGMT.TC_GST tc_gst, "
			+ "GUEST.TXN_PTY_EXTNL_REF pty "
			+ "where tcg.tc_grp_nb = tc.tc_grp_nb "
			+ "and tc.tc_id = tc_gst.tc_id "
			+ "and tc_gst.txn_idvl_pty_id = pty.txn_pty_id "
			+ "and tcg.TPS_ID= {TPS_ID} ";
	private String goMasterDatabase = "GOMASTER";
	private String eaiDatabase = "EAI_LOG";
	private String dreamsDatabase = "DREAMS";
	
	private String environment = "";
	private String conversationIds = "";
	private String expectedMarketingBrand = "";
	private String expectedEmailPreference = "";
	private String expectedAddressPreference = "";
	private String actualEmailMarketingBrand = "";
	private String actualAddressMarketingBrand = "";
	private String actualEmailPreference = "";
	private String actualAddressPreference = "";
	private String originalEmailPreference = "";
	private String originalMailPreference = "";
	private String odsGuestid = "";
	private String tps_id = "";
	boolean verifyEaiDreams = true;
	
	public void validatePtoiDatabaseValues(String environment, String expectedEmailPreference, 
			String expectedAddressPreference, String expectedMarketBrand,
			Map<String, String> convos, String tps_id, boolean verifyEaiDreams){
		this.verifyEaiDreams = verifyEaiDreams;
		validatePtoiDatabaseValues(environment, expectedEmailPreference, 
				expectedAddressPreference, expectedMarketBrand,
				convos,  tps_id);
	}
	public void validatePtoiDatabaseValues(String environment, String expectedEmailPreference, 
			String expectedAddressPreference, String expectedMarketBrand,
			Map<String, String> convos, String tps_id){
		this.environment = environment;
		this.expectedMarketingBrand = expectedMarketBrand;
		this.expectedAddressPreference = expectedAddressPreference;
		this.expectedEmailPreference = expectedEmailPreference;
		originalEmailPreference = this.expectedEmailPreference;
		originalMailPreference = this.expectedAddressPreference;
		this.tps_id = tps_id;
//		this.tps_id = "460559560201";
		if(tps_id.length() < 12) convertAccoviaIdToTPS();
		//Generate the conversation id string for the eai query
		generateConversationIdStringFromMap(convos);
		
		if(!verifyEaiDreams)validateEAICommunication();
		validateGoMasterCommunication();
	}
	
	private void convertAccoviaIdToTPS(){
		convertAccoviaIdToTPSQuery = convertAccoviaIdToTPSQuery.replace("{RES_ID}", tps_id);
		Database db = new OracleDatabase(environment, dreamsDatabase);
		Recordset rs = new Recordset(db.getResultSet(convertAccoviaIdToTPSQuery));
		TestReporter.assertGreaterThanZero(rs.getRowCount());
		tps_id = rs.getValue("TPS_ID");
	}
	
	
	private void validateEAICommunication(){
		//Generate the conversation IDs for the query
		
		eaiQuery = eaiQuery.replace("{CONVOS}", conversationIds);
		TestReporter.log("EAI Query: " + eaiQuery);
		
		//Ensure the expected marketing values are valid
		TestReporter
				.assertTrue(
						(expectedEmailPreference.equalsIgnoreCase("TRUE") || expectedEmailPreference
								.equalsIgnoreCase("FALSE")),
				"The expected email marketing status [" + expectedEmailPreference
						+ "] is not valid. Only 'TRUE' or 'FALSE' are allowed, neither of which needs to be case-sensitive.");
		TestReporter
				.assertTrue(
						(expectedEmailPreference.equalsIgnoreCase("TRUE") || expectedEmailPreference
								.equalsIgnoreCase("FALSE")),
				"The expected address marketing status [" + expectedEmailPreference
						+ "] is not valid. Only 'TRUE' or 'FALSE' are allowed, neither of which needs to be case-sensitive.");
		
	    //If Opting In, the value that is sent to GoMaster is "false"
	    //If Opting Out, the value that is sent to GoMaster is "true"
	    //Determine the expected email indicator
	    if(expectedEmailPreference.equalsIgnoreCase("TRUE")){
	    	expectedEmailPreference = "FALSE";	
	    }
	    else if(expectedEmailPreference.equalsIgnoreCase("FALSE")){
	    	expectedEmailPreference = "TRUE";
	    }
	    //Determine the expected address indicator
	    if(expectedAddressPreference.equalsIgnoreCase("TRUE")){
	    	expectedAddressPreference = "FALSE";
	    }
	    else if(expectedAddressPreference.equalsIgnoreCase("FALSE")){
	    	expectedAddressPreference = "TRUE";
	    }
	    
	    OracleDatabase odb = new OracleDatabase(environment, eaiDatabase);
		Object[][] resultSet;
		resultSet = odb.getResultSet(eaiQuery);

		TestReporter.assertTrue(resultSet.length > 1, "The EAI query did not return any records.");

		TestReporter.log("Record Count: " + String.valueOf(resultSet.length - 1));
		printData(resultSet);
		
		//Format the string response into a valid xml format
		String latestMessage = resultSet[1][0].toString();
		latestMessage = latestMessage.replace("]]", "");
		latestMessage = latestMessage.substring(latestMessage.indexOf("[[")+2, latestMessage.length());
		TestReporter.log(latestMessage);		
		
		//Convert the xml String into and xml Document
		Document doc = XMLTools.makeXMLDocument(latestMessage.toString());
		doc.normalize();
		//Grab all of the child nodes in the GuestInfo node
		NodeList nList =doc.getChildNodes().item(0).getChildNodes().item(1).getChildNodes();
		Node emailNode = null;
		Node addressNode = null;
		//Loop through all child nodes until the email and address nodes are found
		for(int y = 0; y < nList.getLength(); y++){
			String nodeName = nList.item(y).getNodeName();
			System.out.println(nodeName);
			if(nodeName.equalsIgnoreCase("Address")){
				addressNode = nList.item(y);
			}else if(nodeName.equalsIgnoreCase("Email")){
				emailNode = nList.item(y);
			}
		}
		//Ensure that the email and address nodes are not empty
		TestReporter.assertNotNull(addressNode, "No address information was found");
		TestReporter.assertNotNull(emailNode, "No email information was found");
		
		//Capture the marketing brand and preferences from the database
		actualAddressMarketingBrand = addressNode.getAttributes().getNamedItem("OptInOptOutMarketingBrand").getNodeValue();
		actualAddressPreference = addressNode.getAttributes().getNamedItem("OptInOptOutMarketingBrandIndicator").getNodeValue();
		actualEmailMarketingBrand = emailNode.getAttributes().getNamedItem("OptInOptOutMarketingBrand").getNodeValue();
		actualEmailPreference = emailNode.getAttributes().getNamedItem("OptInOptOutMarketingBrandIndicator").getNodeValue();
		
		//Verify that the actual and expected marketing brand and preferences match
		TestReporter.assertEquals(expectedMarketingBrand.toLowerCase(), actualAddressMarketingBrand.toLowerCase(), "The actual address marketing brand ["+actualAddressMarketingBrand.toLowerCase()+"] did not match the expected address marketing brand ["+expectedMarketingBrand.toLowerCase()+"].");
		TestReporter.assertEquals(expectedAddressPreference.toLowerCase(), actualAddressPreference.toLowerCase(), "The actual address marketing preference ["+actualAddressPreference.toLowerCase()+"] did not match the expected address marketing preference ["+expectedAddressPreference.toLowerCase()+"].");
		TestReporter.assertEquals(expectedMarketingBrand.toLowerCase(), actualEmailMarketingBrand.toLowerCase(), "The actual email marketing brand ["+actualEmailMarketingBrand.toLowerCase()+"] did not match the expected email marketing brand ["+expectedMarketingBrand.toLowerCase()+"].");
		TestReporter.assertEquals(expectedAddressPreference.toLowerCase(), actualEmailPreference.toLowerCase(), "The actual email marketing preference ["+actualAddressPreference.toLowerCase()+"] did not match the expected email marketing preference ["+expectedEmailPreference.toLowerCase()+"].");
		
		//Reset the preferences to the original values
		expectedAddressPreference = originalMailPreference;
		expectedEmailPreference = originalEmailPreference;
	}
	
	public void validateGoMasterCommunication(){
		TestReporter.logStep("Query GOMASTER DB For MARKETING PREFERENCES");
		
		//Set the global value for the tps_id
		retrieveOdsGuestIdFromDreams();
		goMasterQuery = goMasterQuery.replace("{ODS_GUEST}", odsGuestid);
		TestReporter.log("GoMaster Query: " + goMasterQuery);
		
		//Ensure the expected marketing values are valid
		TestReporter
				.assertTrue(
						(expectedEmailPreference.equalsIgnoreCase("TRUE") || expectedEmailPreference
								.equalsIgnoreCase("FALSE") || expectedEmailPreference.equalsIgnoreCase("noEmail")),
				"The expected email marketing status [" + expectedEmailPreference
						+ "] is not valid. Only 'TRUE' or 'FALSE' are allowed, neither of which needs to be case-sensitive.");
		TestReporter
				.assertTrue(
						(expectedAddressPreference.equalsIgnoreCase("TRUE") || expectedAddressPreference
								.equalsIgnoreCase("FALSE")),
				"The expected address marketing status [" + expectedEmailPreference
						+ "] is not valid. Only 'TRUE' or 'FALSE' are allowed, neither of which needs to be case-sensitive.");
		
	    //If Opting In, the value that is sent to GoMaster is "false"
	    //If Opting Out, the value that is sent to GoMaster is "true"
	    //Determine the expected email indicator
	    if(expectedEmailPreference.equalsIgnoreCase("TRUE")){
	    	expectedEmailPreference = "Y";	
	    }
	    else if(expectedEmailPreference.equalsIgnoreCase("FALSE")){
	    	expectedEmailPreference = "N";
	    }
	    else if(expectedEmailPreference.equalsIgnoreCase("noEmail")){
	    	expectedEmailPreference = "";
	    	goMasterQuery = goMasterQueryNoEmail.replace("{ODS_GUEST}", odsGuestid);
	    }
	    //Determine the expected address indicator
	    if(expectedAddressPreference.equalsIgnoreCase("TRUE")){
	    	expectedAddressPreference = "Y";
	    }
	    else if(expectedAddressPreference.equalsIgnoreCase("FALSE")){
	    	expectedAddressPreference = "N";
	    }
	    
	    OracleDatabase odb = new OracleDatabase(environment, goMasterDatabase);
		Object[][] resultSet;
		resultSet = odb.getResultSet(goMasterQuery);

		TestReporter.assertTrue(resultSet.length > 1, "The GoMaster query did not return any records.");

		TestReporter.log("Record Count: " + String.valueOf(resultSet.length - 1));
		printData(resultSet);
		
		actualAddressMarketingBrand = resultSet[1][1].toString();
		actualAddressPreference = resultSet[1][0].toString();
		if(!expectedEmailPreference.isEmpty()
				){
			actualEmailPreference = resultSet[1][3].toString();
			actualEmailMarketingBrand = resultSet[1][1].toString();
		}
				
		//Ensure that the email and address nodes are not empty
		TestReporter.assertTrue(!actualAddressPreference.isEmpty(), "No address information was found");
		if(!expectedEmailPreference.isEmpty()){
			TestReporter.assertTrue(!actualEmailPreference.isEmpty(), "No email information was found");
		}
		
		//Verify that the actual and expected marketing brand and preferences match
		TestReporter.assertEquals(expectedMarketingBrand.toLowerCase(), actualAddressMarketingBrand.toLowerCase(), "The actual address marketing brand ["+actualAddressMarketingBrand.toLowerCase()+"] did not match the expected address marketing brand ["+expectedMarketingBrand.toLowerCase()+"].");
		TestReporter.assertEquals(expectedAddressPreference.toLowerCase(), actualAddressPreference.toLowerCase(), "The actual address marketing preference ["+actualAddressPreference.toLowerCase()+"] did not match the expected address marketing preference ["+expectedAddressPreference.toLowerCase()+"].");
		if(!expectedEmailPreference.isEmpty()){
			TestReporter.assertEquals(expectedMarketingBrand.toLowerCase(), actualEmailMarketingBrand.toLowerCase(), "The actual email marketing brand ["+actualEmailMarketingBrand.toLowerCase()+"] did not match the expected email marketing brand ["+expectedMarketingBrand.toLowerCase()+"].");
			TestReporter.assertEquals(expectedAddressPreference.toLowerCase(), actualEmailPreference.toLowerCase(), "The actual email marketing preference ["+actualAddressPreference.toLowerCase()+"] did not match the expected email marketing preference ["+expectedEmailPreference.toLowerCase()+"].");
		}
		System.out.println();
	}
	
	public void retrieveOdsGuestIdFromDreams(){
		TestReporter.logStep("Query DREAMS DB For ODS Guest ID");
		dreamsQuery = dreamsQuery.replace("{TPS_ID}", tps_id);
		convertAccoviaGuestIdToDreamsQuery = convertAccoviaGuestIdToDreamsQuery.replace("{TPS_ID}", tps_id);
		TestReporter.log("Dreams Query: " + dreamsQuery);
	    
	    OracleDatabase odb = new OracleDatabase(environment, dreamsDatabase);
		Object[][] resultSet;
		resultSet = odb.getResultSet(dreamsQuery);
		try{
			odsGuestid = resultSet[1][0].toString();
		}catch(Exception e){
			resultSet = odb.getResultSet(convertAccoviaGuestIdToDreamsQuery);
			odsGuestid = resultSet[1][0].toString();
		}
		TestReporter.log("ODS Guest ID: " + odsGuestid);
	}
	
	private void generateConversationIdStringFromMap(Map<String, String> convos){
		for (Map.Entry<String, String> entry : convos.entrySet())
		{
			conversationIds = conversationIds + "'" + entry.getValue() + "',";
		}
		conversationIds = conversationIds.substring(0, conversationIds.lastIndexOf(","));
	}	
	
	private void printData(Object[][] events) {
		String[][] eventsArray = new String[events.length][events[0].length];
		for (int row = 0; row < events.length; row++) {
			for (int column = 0; column < events[0].length; column++) {
				eventsArray[row][column] = events[row][column].toString();
			}
		}
		new Recordset(eventsArray).print();
	}
	
	@Test
	public void test(){
		String environment = "Grumpy";
		String expectedEmailPreference = "FALSE"; 
		String expectedAddressPreference = "FALSE";
		String expectedMarketBrand = "QUALNOPAID";
		Map<String, String> convos = new HashMap<String, String>();
		convos.put("book", "3cmosKyQYjdB1_lPMZZDEej");
		String tps_id = "460551677523";
		
		validatePtoiDatabaseValues( environment,  expectedEmailPreference, 
				 expectedAddressPreference,  expectedMarketBrand,
				convos, tps_id, false);
	}
}

