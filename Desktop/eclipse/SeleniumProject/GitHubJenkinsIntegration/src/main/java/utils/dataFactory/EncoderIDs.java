package utils.dataFactory;

import org.testng.Assert;

public class EncoderIDs {
	/*
	 * TODO This needs to be either a DB call to Dreams.RSRC_INV.WRK_LOC_ENDR or a service call CampusServicePort.retreiveLocationDetails
	 */
	public static String getEncoderId(String environment, String facilityId){
		String encoderId = "";
		boolean environmentFound = true;
		boolean facilityIdFound = true;

		switch(environment.toLowerCase()){
		case "sleepy":
			switch(facilityId){
			case "80010385":
				encoderId = "112400";
			break;
			case "1":
				encoderId = "112401";
			break;
			case "80010408":
				encoderId = "112405";
			break;
			case "80010405":
				encoderId = "112406";
			break;
			case "80010395":
				encoderId = "112403";
			break;
			case "273239":
				encoderId = "112404";
			break;
			case "80010384":
				encoderId = "112402";
			break;
			default:
				facilityIdFound = false;
			break;
			}
		break;
		case "snowwhite":
			switch(facilityId){
			case "80010386":
				encoderId = "111027";
			break;
			case "80010385":
				encoderId = "112526";
			break;
			case "80010384":
				encoderId = "112300";
			break;
			case "80010406":
				encoderId = "112302";
			break;
			case "80010388":
				encoderId = "111028";
			break;
			case "80010383":
				encoderId = "111029";
			break;
			case "80010405":
				encoderId = "112301";
			break;
			case "80010408":
				encoderId = "112101";
			break;
			case "80010391":
				encoderId = "111032";
			break;
			case "1":
				encoderId = "111030";
			break;
			case "80010404":
				encoderId = "111031";
			break;
			default:
				facilityIdFound = false;
			break;
			}
		break;
		case "grumpy":
			switch(facilityId){
			case "80010385":
				encoderId = "112201";
			break;
			case "80010384":
				encoderId = "112202";
			break;
			case "80010406":
				encoderId = "112203";
			break;
			case "80010387":
				encoderId = "112204";
			break;
			case "80010405":
				encoderId = "112208";
			break;
			case "80010408":
				encoderId = "112207";
			break;
			case "1":
				encoderId = "112205";
			break;
			case "144943":
				encoderId = "112206";
			break;
			default:
				facilityIdFound = false;
			break;
			}
		break;
		case "evilqueen":
			switch(facilityId){
			case "80010385":
				encoderId = "68525";
			break;
			default:
				facilityIdFound = false;
			break;
			}
		break;
		case "doc":
			switch(facilityId){
			case "80010385":
				encoderId = "1086";
			break;
			default:
				facilityIdFound = false;
			break;
			}
		break;
		case "bashful":
			switch(facilityId){
			case "80010389":
				encoderId = "112300";
			break;
			case "80010386":
				encoderId = "112301";
			break;
			case "80010399":
				encoderId = "112302";
			break;
			case "80010385":
				encoderId = "112303";
			break;
			case "80010396":
				encoderId = "112304";
			break;
			case "80010384":
				encoderId = "112305";
			break;
			case "80010387":
				encoderId = "112306";
			break;
			case "80010388":
				encoderId = "112307";
			break;
			case "80010403":
				encoderId = "112308";
			break;
			case "80010383":
				encoderId = "112309";
			break;
			case "80010405":
				encoderId = "112310";
			break;
			case "80010394":
				encoderId = "112311";
			break;
			case "80010407":
				encoderId = "112312";
			break;
			default:
				facilityIdFound = false;
			break;
			}
		break;
		default:
			environmentFound = false;
		break;
		}

		Assert.assertEquals(environmentFound, true, "The environment ["+environment+"] was not handled by the switch statement.\n");
		Assert.assertEquals(facilityIdFound, true, "The facility ID ["+facilityId+"] was not handled by the switch statement.\n");

		return encoderId;
	}
}

