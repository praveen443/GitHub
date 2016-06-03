package utils;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.w3c.dom.Document;

import api.soapServices.core.BaseSoapService;

public class GenerateCard extends BaseSoapService {
	private String responseXML = "";
	private Document responseDoc = null;
	private String cardTypeForUrl;
	
	public enum cardTypes  {
			GIFTCARD,
			DINERS_CLUB,
			DISNEYREWARDSCARD,
			JCB,
			AMEX,
			DISCOVER,
			MC,
			VISA
	}
	
	public GenerateCard(){}
	

	private Document retrieveCardInformation(String response, String delay, cardTypes cardType){
		boolean validCardType = true;
		
		switch (cardType) {
		case GIFTCARD:
			cardTypeForUrl = "GC";
			break;
		case DINERS_CLUB:
			cardTypeForUrl = "DINERS_CLUB";
			break;
		case DISNEYREWARDSCARD:
			cardTypeForUrl = "DRC";
			break;
		case JCB:
			cardTypeForUrl = "JCB";
			break;
		case AMEX:
			cardTypeForUrl = "AMEX";
			break;
		case DISCOVER:
			cardTypeForUrl = "DISC";
			break;
		case MC:
			cardTypeForUrl = "MC";
			break;
		case VISA:
			cardTypeForUrl = "VISA";
			break;
		default:
			validCardType = false;
			TestReporter.assertTrue(validCardType, "The card type ["+cardType+"] is not valid. Please enter one of the following: ["+cardTypes.values().toString()+"].");
			break;
		}

		
		String url = "http://fldcvpswa6204.wdw.disney.com/PCardRepository/rest/OneTestREST/Process/xml/rnd_SelectCardScenario/{response}/{delay}/{cardType}/1";
		url = url.replace("{cardType}", cardTypeForUrl).replace("{delay}", delay).replace("{response}", response);
		//Uncomment to output the url used by the test to the console
		TestReporter.log("PCard URL: " +  url);


		try {
			responseXML = sendGetRequest(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Uncomment to output the PCard response to the console
//		System.out.println(responseXML.toString());
		responseXML = removeTDMXMLAttributes(responseXML);
		responseDoc = XMLTools.makeXMLDocument(responseXML);

		return responseDoc;
	}

	public Map<String, String> getCardInfo(String response, int delay, cardTypes cardType) throws Exception {
		return getCardInfo(response, String.valueOf(delay), cardType);
	}

	public Map<String, String> getCardInfo(String response, String delay, cardTypes cardType){
		Document responseParts = null;
		Map<String, String> data = new HashMap<String, String>();
		boolean isGC = false;

		if (cardType == cardTypes.GIFTCARD) {
			isGC = true;
		}

		responseParts = retrieveCardInformation(response, delay, cardType);
		setResponseDocument(responseParts);
		data.put("CardID", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='CardID'][1]"));
		data.put("NameOnCard", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='NameOnCard'][1]"));
		data.put("AccountNumber", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='AccountNumber'][1]"));
		data.put("CardType", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='CardType'][1]"));
		data.put("CVV2", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='CVV2'][1]"));
		if (!isGC) {
			data.put("ExpMonth", getResponseNodeValueByXPath(
					"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='ExpMonth'][1]"));
			data.put("ExpYear", getResponseNodeValueByXPath(
					"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='ExpYear'][1]"));
		}
		data.put("BillingStreet", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='BillingStreet'][1]"));
		data.put("BillingStreet2", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='BillingStreet2'][1]"));
		data.put("BillingCity", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='BillingCity'][1]"));
		data.put("BillingState", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='BillingState'][1]"));
		data.put("BillingCountry", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='BillingCountry'][1]"));
		data.put("BillingZip", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='BillingZip'][1]"));
		data.put("CardTrackIndicator", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='CardTrackIndicator'][1]"));
		data.put("VendorOrPersonalFlag", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='VendorOrPersonalFlag'][1]"));
		data.put("VendorID", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='VendorID'][1]"));
		data.put("VendorType", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='VendorType'][1]"));
		data.put("AvailableBalance", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='AvailableBalance'][1]"));
		data.put("Delay", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='Delay'][1]"));
		data.put("StatusCode", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='StatusCode'][1]"));
		data.put("ExpectedResponse", getResponseNodeValueByXPath(
				"/*[local-name(.)='set'][1]/*[local-name(.)='item'][1]/*[local-name(.)='ExpectedResponse'][1]"));
		
		TestReporter.log("Card ID: " + data.get("CardID"));
		
		return data;
	}
	
	public cardTypes getCradTypeEnum(String method){
		cardTypes type = null;
		boolean validCardType = true;
		switch(method.toLowerCase().trim()){
		case "diners club":
			type = cardTypes.DINERS_CLUB;
			break;
		case "jcb":
			type = cardTypes.JCB;
			break;
		case "american express":
			type = cardTypes.AMEX;
			break;
		case "discover":
			type = cardTypes.DISCOVER;
			break;
		case "master card":
			type = cardTypes.MC;
			break;
		case "visa":
			type = cardTypes.VISA;
			break;
		case "gift card":
			type = cardTypes.GIFTCARD;
			break;
		case "disney rewards card":
			type = cardTypes.DISNEYREWARDSCARD;
			break;
		default:
			validCardType = false;
			TestReporter.assertTrue(validCardType, "The card type/method ["+method+"] is not a valid choice.");
			break;
		}
		return type;
	}

	@Test
	public void test() throws Exception {
		getCardInfo("APPROVED", 0, cardTypes.DISCOVER);
	}
}

