package utils.dataFactory;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.soapServices.guestAccessControlService.operations.RetrieveDetails;
import utils.Sleeper;
import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.database.Recordset;
import utils.dataFactory.database.databaseImpl.OracleDatabase;
import utils.dataFactory.guestFactory.HouseHold;
import utils.dataFactory.staging.GenerateReservation;
import utils.dataFactory.staging.Reservation;

public class sandbox {
	String environment = "Sleepy";
	int reservations  = 1;
	
	//@Test
	public void resort(){
		String resort = "Disney's Boardwalk Villas Resort";
		System.out.println(ResortInfo.getLocationID(ResortColumns.MINI_CAMPUS, resort));
		System.out.println(ResortInfo.getFacilityID(ResortColumns.MINI_CAMPUS, resort));
		System.out.println(ResortInfo.getFacilityCD(ResortColumns.MINI_CAMPUS, resort));
		System.out.println(ResortInfo.getResortInfo(ResortColumns.MINI_CAMPUS, resort, ResortColumns.RESORT_CATEGORY));
		Assert.assertTrue(ResortInfo.isDVCResort(resort));
		
	}
//	@Test
	public void sandbox(){
		RetrieveDetails retrieve = new RetrieveDetails("Sleepy", "Main");
		retrieve.setPartyId("127328185");
		retrieve.setManufacturerId("19999841879007685");
		retrieve.sendRequest();
		 
		Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");
		
		System.out.println(retrieve.getAccessEncodingStatus());
		System.out.println(retrieve.getAccessMediaId());
		System.out.println(retrieve.getRfidMediaId());
	}
	//@Test
	public void randomDetailedReservation(){
		Reservation reservation = new GenerateReservation().bookResortReservation().CONTEMPORARY(environment);		
		reservation.quickBook();
		//reservation.bookRoomOnlyTwoAdults();
		reservation.assignRoomToReservation();
		reservation.checkInReservation();
		//reservation.makeFullPayment();
		reservation.encodePrimaryGuest();
		
		RetrieveDetails retrieve = new RetrieveDetails(environment, "Main");
		retrieve.setPartyId(reservation.getPartyId());
		retrieve.setManufacturerId(reservation.getManufacturerUID());
		retrieve.sendRequest();
		Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");
		
		
		System.out.println("Environment: " + reservation.getEnvironment());
		System.out.println("Travel Plan ID: " + reservation.getTravelPlanId());
		System.out.println("Reservation ID: " + reservation.getTravelPlanSegmentId());
		System.out.println("Facilty ID: " + reservation.getFacilityId());
		System.out.println("Location ID: " + reservation.getLocationId());
		System.out.println("Party ID: " + reservation.getPartyId());
		System.out.println("Guest ID: " + reservation.getGuestId());
		System.out.println("Assigned Room: " + reservation.getRoomNumber());
		//System.out.println("Folio ID: " + reservation.getFolioId());
		//System.out.println("Balance Due: " + reservation.getBalanceDue());
		System.out.println("Encoder ID: " + reservation.getEncoderId());
		System.out.println("KTTW ID: " + reservation.getKttwId());
		System.out.println("Manufacturer ID: " + reservation.getManufacturerUID());
		System.out.println("RFID Media ID: " + reservation.getRfidMediaId());
	}
	/*@Test
	public void addRoomTest(){
		Reservation reservation = new GenerateReservation().bookResortReservation().BAY_LAKE_TOWERS(environment);
		reservation.setNumberOfAdults(2);
		reservation.setPrimaryGuestLastName("AutomationTest");
		reservation.setRoomTypeCode("4O");
		reservation.quickBook();
		
		Reservation secondAccommodation =  new GenerateReservation().bookResortReservation().CONTEMPORARY(reservation);
//		secondAccommodation.addRoom();
		//reservation.assignRoomToReservation(reservation.getRoomTypeCode());
		reservation.assignRoomToReservation();
		reservation.checkInReservation();
		//reservation.makePayment();
		Sleeper.sleep(3000);		
		
		secondAccommodation.addRoom();
		secondAccommodation.assignRoomToReservation(secondAccommodation.getRoomTypeCode());
//		secondAccommodation.assignRoomToReservation();
		secondAccommodation.checkInReservation();
		//secondAccommodation.makePayment();
		
		System.out.println("Travel Plan ID: " + reservation.getTravelPlanId());
		System.out.println("Reservation ID: " + reservation.getTravelPlanSegmentId());
		System.out.println("Travel Component Grouping ID: " + reservation.getTravelComponentGroupingId());
		System.out.println("Travel Component ID: " + reservation.getTravelComponentId());
		System.out.println("Party ID: " + reservation.getPartyId());
		System.out.println("Guest ID: " + reservation.getGuestId());
		System.out.println("Assigned Room: " + reservation.getRoomNumber());
		System.out.println();
		System.out.println("Travel Plan ID: " + secondAccommodation.getTravelPlanId());
		System.out.println("Reservation ID: " + secondAccommodation.getTravelPlanSegmentId());
		System.out.println("Travel Component Grouping ID: " + secondAccommodation.getTravelComponentGroupingId());
		System.out.println("Travel Component ID: " + secondAccommodation.getTravelComponentId());
		System.out.println("Party ID: " + secondAccommodation.getPartyId());
		System.out.println("Guest ID: " + secondAccommodation.getGuestId());
		System.out.println("Assigned Room: " + secondAccommodation.getRoomNumber());
	}*/
	
	//@Test
	public void RetrieveTables(){
		String rootURL = "QAAUTO";
		String subTable = "";
		String baseTable = "";
		String mainTable = "";		
		String currentMainTable = "";
		String currentSubTable = "";
		String currentTable = "";
		
		int intTables = 0;
		
		Recordset rsRootTable = VirtualTable.compileXML(rootURL, new VirtualTable().getAllTestRows(rootURL));
		System.out.println(rootURL);
		rsRootTable.print();
		intTables++;
		//Get the dir path
/*		File directory = new File (".");
		
		File dirPath = null;
		try {
			dirPath = new File(directory.getCanonicalPath() + "\\datatable-backup\\");
		
		
		// if the directory does not exist, create it
		if (!dirPath.exists()) {
		  try{
			  dirPath.mkdir();
		   } catch(SecurityException se){
		      //handle it
		   }        
		}*/
			for (rsRootTable.moveFirst();rsRootTable.hasNext();rsRootTable.moveNext()){		
				intTables++;
				mainTable = rsRootTable.getValue("TABLELINK");
				System.out.println();
				System.out.println(mainTable);
				currentMainTable = mainTable.substring(mainTable.lastIndexOf("/")+1, mainTable.length());
				if (!currentMainTable.equalsIgnoreCase("QAAUTO_API_TEST_SCENARIO_DATA")){
					Recordset rsMainTables = VirtualTable.compileXML(currentMainTable, new VirtualTable().getAllTestRows(currentMainTable));
					rsMainTables.print();
					for(rsMainTables.moveFirst();rsMainTables.hasNext();rsMainTables.moveNext()){
						subTable = rsMainTables.getValue("TABLELINK");
						intTables++;
						System.out.println();
						System.out.println(subTable);
						currentSubTable = subTable.substring(subTable.lastIndexOf("/")+1, subTable.length());
						if(!currentSubTable.contains("METADATA")){
							Recordset rsSubTables = VirtualTable.compileXML(currentSubTable, new VirtualTable().getAllTestRows(currentSubTable));
							rsSubTables.print();
							for(rsSubTables.moveFirst() ; rsSubTables.hasNext() ; rsSubTables.moveNext()){
								baseTable = rsSubTables.getValue("TABLELINK");
								System.out.println();
								System.out.println(baseTable);
								currentTable = baseTable.substring(baseTable.lastIndexOf("/")+1, baseTable.length());
								Recordset rsTable = VirtualTable.compileXML(currentTable, new VirtualTable().getAllTestRows(currentTable));
								rsTable.print();	
								intTables++;
							}
						}
					}
				}
			}
		/*} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//");*/
		System.out.println();
		System.out.println("Number of tables found: " + intTables);
	}
	
	//@Test
	public void Guests(){
			
		HouseHold guests = new HouseHold(3);
		guests.primaryGuest().addAddress();
		System.out.println(guests.primaryGuest().getFullName());
		System.out.println(guests.primaryGuest().primaryAddress().getAddress1());
		System.out.println(guests.primaryGuest().getAllAddresses().get(1).getAddress1());
		System.out.println(guests.primaryGuest().primaryPhone().getNumber());
	}
	
	@Test
	public void test(){
System.out.println(ResortInfo.getFacilityID(ResortColumns.RESORT_NAME,"Disney's Contemporary Resort"));
		//new TdodClientUtil().stageGoMasterGuest("", "", null, false);
	}
	
}

