package utils.dataFactory.database.staging;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.MediaSize.NA;

import org.testng.Assert;
import org.testng.Reporter;

import api.soapServices.ServiceConstants;
import api.soapServices.accommodationAssignmentServicePort.operations.AssignRoomForReservation;
import api.soapServices.accommodationAssignmentServicePort.operations.FindRoomForReservation;
import api.soapServices.accommodationFulfillmentServicePort.operations.CheckIn;
import api.soapServices.accommodationFulfillmentServicePort.operations.CompleteCheckIn;
import api.soapServices.accommodationSalesServicePort.operations.Add;
import api.soapServices.accommodationSalesServicePort.operations.Book;
import api.soapServices.accommodationSalesServicePort.operations.Cancel;
import api.soapServices.accommodationSalesServicePort.operations.CreateComments;
import api.soapServices.accommodationSalesServicePort.operations.Quickbook;
import api.soapServices.accommodationSalesServicePort.operations.Reconcile;
import api.soapServices.accommodationSalesServicePort.operations.RemoveRateOverride;
import api.soapServices.accommodationSalesServicePort.operations.Retrieve;
//import api.soapServices.accommodationSalesServicePort.operations.RetrieveComments;
//import api.soapServices.accommodationSalesServicePort.operations.RetrieveTravelPlanMediaCustomization;
import api.soapServices.accommodationSalesServicePort.operations.SearchResortReservationsByGuest;
import api.soapServices.accommodationSalesServicePort.operations.Share;
import api.soapServices.accommodationSalesServicePort.operations.UpdateComments;
//import api.soapServices.accommodationSalesServicePort.operations.UpdateGuaranteeStatus;
import api.soapServices.accommodationStatusComponentService.operations.UpdateSingleRoomStatus;
import api.soapServices.admissionSalesServicePort.operations.BookKeyToTheWorldTickets;
import api.soapServices.admissionSalesServicePort.operations.CancelKeyToTheWorldTickets;
import api.soapServices.admissionSalesServicePort.operations.RetrieveKeyToTheWorldTickets;
import api.soapServices.chargeAccountServiceV2.operations.Create;
import api.soapServices.chargeAccountServiceV2.operations.UpdatePin;
import api.soapServices.chargeAccountServiceV2.operations.ValidatePin;
import api.soapServices.experienceMediaServiceV1.operations.GrantNewAccess;
import api.soapServices.experienceMediaServiceV1.operations.RetrieveDetails;
import api.soapServices.folioConfigurationService.operations.CreateOrRetrievePaymentDevice;
import api.soapServices.folioServicePort.operations.CreateSettlementMethod;
import api.soapServices.folioServicePort.operations.RetrieveFolioBalanceDue;
import api.soapServices.folioServicePort.operations.RetrieveResponsiblePartyDetails;
import api.soapServices.inventoryService.operations.FreezeInventory;
import api.soapServices.membershipService.operations.SearchMemberships;
import api.soapServices.paymentService.operations.PostCardPayment;
import api.soapServices.paymentService.operations.PostCheckPayment;
import api.soapServices.travelPlanSegmentServicePort.operations.UpdatePreArrivalInformation;
import utils.Datatable;
import utils.GenerateCard;
import utils.Randomness;
import utils.RetrieveChargeAccountID;
import utils.Sleeper;
import utils.TestReporter;
import utils.dataFactory.EncoderIDs;
import utils.dataFactory.ResortInfo;
import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.database.Recordset;
import utils.dataFactory.database.databaseImpl.OracleDatabase;
import utils.dataFactory.database.sqlStorage.ReservationInfo;
import utils.date.DateTimeConversion;


public class ReservationDecorator implements Reservation {
	/*
	 * General variables
	 */
	private String environment;
	private Map<String, String> conversationIDs = new HashMap<String, String>();
	private Datatable datatable = new Datatable();
	
	/*
	 * Quickbook & book request variables
	 */
	private String primaryGuestFirstName;
	private String primaryGuestLastName;
	private String numberOfAdults;
	private String numberOfRooms;
	private String arrivalDaysOut;
	private String arrivalDate;
	private String departureDaysOut;
	private String departureDate;
	private String packageCode;	
	private String resortCode;
	private String roomTypeCode;
	private String locationId;
	private String facilityId;
	private String phoneNumber;
	private String email;
	private String responseCode;
	private String sourceAccountingCenterId;
	private String primaryGuestOdsId;
	
	private String inventoryTrackingID;
	private String primaryGuestPartyId;
	
	private String primaryGuestAddress1 = "";
	private String primaryGuestCity = "";
	private String primaryGuestState = "";
	private String primaryGuestPostalCode = "";

	/*
	 * Retrieve response variables
	 */
	private String travelPlanId;
	private String travelPlanSegmentId;
	private String travelComponentGroupingId;
	private String travelComponentId;
	private String primaryGuestId;
	private String partyId;
	private String[] guestIds;
	private String[] partyIds;
	
	/*
	 * Assign room variables
	 */
	
	private String roomNumber;
	private String resourceId;
	private String assignmentOwnerNumber;
	
	/*
	 * Make payment variables
	 */
	
	private String folioId;
	private String depositDue;
	private String balanceDue;
	private String originalTransactionId;
	private String latestTransactionId;
	private String paymentId;
	private String[] paymentIds = new String[2];
	private String rrnNumber;
	private String[] rrnNumbers = new String[2];
	private String rrnKey;
	private String terminalID;
	private String cardNumber = "";
	private String cardExpirationDate = "";
	private String cardExpirationMonth = "";
	private String cardExpirationYear = "";
	private String cardCcvCode = "";
	private String cardHolderName = "";
	private String cardPaymentMethod;
	private String cardPaymentType;
	private String cardAddressLine1;
	private String cardAddressLine2;
	private String cardCity;
	private String cardState;
	private String cardPostalCode;
	private String[] cardNumbers = new String[2];
	private String[] cardExpirationDates = new String[2];
	private String[] cardExpirationMonths = new String[2];
	private String[] cardExpirationYears = new String[2];
	private String[] cardCcvCodes = new String[2];
	private String[] cardHolderNames = new String[2];
	private String[] cardPaymentMethods = new String[2];
	private String[] cardPaymentTypes = new String[2];
	
	/*
	 * DVC variables
	 */
	private String DVCMemberID;		//same as the DVC member ID
	private String membershipRefID;
	private String vacationType;
	private String resortLocation;
	private String DVCresortCode;
	
	/*
	 * Guest Media info
	 */
	private String kttwId;
	private String[] kttwIds;
	private String encoderId;
	private String manufacturerUID;
	private String rfidMediaId;
	private String accessEncodingStatus;
	private String guestRoomIndicator;
	private String mediaAccessId;
	
	/*
	 * Tickets variables
	 */
	private String[] admissionComponentId;
	private String[] baseAdmissionProductId;
	
	
	/*
	 * Global variables for Guest Access Services
	 */
	public static String gaKttwId;
	public static String gaPartyId;
	public static String gaTravelPlanId;
	
	/*
	 * Charge Account fields
	 */
	private String[] chargeAcctIds;
	private String pinNumber;
	private String[] pinNumbers;
	
	/*
	 * Event Booking fields
	 */
	private String eventTravelPlanId;
	
	public ReservationDecorator() {
		
	}
	
	public ReservationDecorator(String environment){
		this.environment = environment;
		this.primaryGuestFirstName = "fName";
		this.primaryGuestLastName = "lName";
		this.numberOfAdults = "1";
		this.numberOfRooms = "1";
		this.arrivalDaysOut = "0";
		this.departureDaysOut = "1";
		this.packageCode = "B325A";
		}
	
	public ReservationDecorator(ReservationDecorator oldRes) {
		this.environment = oldRes.getEnvironment();
		this.primaryGuestFirstName = oldRes.getPrimaryGuestFirstName();
		this.primaryGuestLastName = oldRes.getPrimaryGuestLastName();
		this.numberOfAdults = oldRes.getNumberOfAdults();
		this.numberOfRooms = oldRes.getNumberOfRooms();
		this.arrivalDaysOut = oldRes.getArrivalDaysOut();
		this.departureDaysOut = oldRes.getDepartureDaysOut();
		this.packageCode = oldRes.getPackageCode();	
		this.resortCode = oldRes.getResortCode();
		this.roomTypeCode = oldRes.getRoomTypeCode();
		this.locationId = oldRes.getLocationId();
		this.facilityId = oldRes.getFacilityId();
		this.inventoryTrackingID = oldRes.getInventoryTrackingID();
		this.travelPlanId = oldRes.getTravelPlanId();
		this.travelPlanSegmentId = oldRes.getTravelPlanSegmentId();
		this.travelComponentGroupingId = oldRes.getTravelComponentGroupingId();
		this.travelComponentId = oldRes.getTravelComponentId();
		this.primaryGuestId = oldRes.getGuestId();
		this.partyId = oldRes.getPartyId();
		this.folioId = oldRes.getFolioId();
		this.depositDue = oldRes.getDepositDue();
		this.balanceDue = oldRes.getBalanceDue();
		this.originalTransactionId = oldRes.getOriginalTransactionId();
		this.latestTransactionId = oldRes.getLatestTransactionId();
		this.paymentId = oldRes.getPaymentId();
		this.rrnNumber = oldRes.getRRNNumber();
		this.rrnKey = oldRes.getRRNKey();
		this.DVCMemberID = oldRes.getDVCMemberID();		//same as the DVC member ID
		this.membershipRefID = oldRes.getMembershipRefID();
		this.vacationType = oldRes.getVacationType();
		this.resortLocation = oldRes.getResortLocation();
		this.DVCresortCode = oldRes.getDVCresortCode();
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getPrimaryGuestFirstName() {
		return primaryGuestFirstName;
	}

	public void setPrimaryGuestFirstName(String primaryGuestFirstName) {
		this.primaryGuestFirstName = primaryGuestFirstName;
	}

	public String getPrimaryGuestLastName() {
		return primaryGuestLastName;
	}

	public void setPrimaryGuestLastName(String primaryGuestLastName) {
		this.primaryGuestLastName = primaryGuestLastName;
	}

	public String getNumberOfAdults() {
		return numberOfAdults;
	}

	public void setNumberOfAdults(int numberOfAdults) {
		this.numberOfAdults = String.valueOf(numberOfAdults);
	}
	
	public void setNumberOfAdults(String numberOfAdults) {
		this.numberOfAdults = numberOfAdults;
	}

	public String getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(String numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = String.valueOf(numberOfRooms);
	}
	
	public String getArrivalDaysOut() {
		return arrivalDaysOut;
	}

	public void setArrivalDaysOut(String arrivalDaysOut) {
		this.arrivalDaysOut = arrivalDaysOut;
	}

	public void setArrivalDaysOut(int arrivalDaysOut) {
		this.arrivalDaysOut = String.valueOf(arrivalDaysOut);
	}

	public String getDepartureDaysOut() {
		return departureDaysOut;
	}

	public void setDepartureDaysOut(String departureDaysOut) {
		this.departureDaysOut = departureDaysOut;
	}
	
	public void setDepartureDaysOut(int departureDaysOut) {
		this.departureDaysOut = String.valueOf(departureDaysOut);
	}
	
	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}

	public String getResortCode() {
		return resortCode;
	}

	protected void setResortCode(String resortCode) {
		this.resortCode = resortCode;
	}
	
	protected void setResortCode(ResortColumns using, String info) {
		this.resortCode = ResortInfo.getFacilityCD(using, info);
	}
	
	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	protected void setLocationId(ResortColumns using, String info) {
		this.locationId = ResortInfo.getLocationID(using, info);
	}

	protected void setLocationId(int locationId) {
		this.locationId = String.valueOf(locationId);
	}
	
	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	
	protected void setFacilityId(ResortColumns using, String info) {
		this.facilityId = ResortInfo.getFacilityID(using, info);
	}
	
	protected void setSouceAccountingCenterId(ResortColumns using, String info){
		this.sourceAccountingCenterId = ResortInfo.getSourceAccountingCenterId(using, info);
	}
	
	public String getSourceAcccountingCenterId(){
		return sourceAccountingCenterId;
	}

	protected void setFacilityId(int facilityId) {
		this.facilityId = String.valueOf(facilityId);
	}
		
	public String getInventoryTrackingID() {
		return inventoryTrackingID;
	}

	public void setInventoryTrackingID(String inventoryTrackingID) {
		this.inventoryTrackingID = inventoryTrackingID;
	}
	/* 
	 * Retreive Response Gets and Sets
	 */
	public String getTravelPlanId(){
		return travelPlanId;
	}
	
	protected void setTravelPlanId(String id){
		this.travelPlanId = id;
	}
	
	public String getTravelPlanSegmentId() {
		return travelPlanSegmentId;
	}

	protected void setTravelPlanSegmentId(String travelPlanSegmentId) {
		this.travelPlanSegmentId = travelPlanSegmentId;
	}

	public String getTravelComponentGroupingId() {
		return travelComponentGroupingId;
	}

	protected void setTravelComponentGroupingId(String travelComponentGroupingId) {
		this.travelComponentGroupingId = travelComponentGroupingId;
	}

	public String getTravelComponentId() {
		return travelComponentId;
	}

	protected void setTravelComponentId(String travelComponentId) {
		this.travelComponentId = travelComponentId;
	}

	public String getPartyId() {
		return partyId;
	}

	protected void setPartyId(String primaryPartyId) {
		this.partyId = primaryPartyId;
	}
	
	public String getGuestId() {
		return primaryGuestId;
	}
	
	public String[] getAllGuestIds() {
		return guestIds;
	}

	public String[] getAllPartyIds() {
		return partyIds;
	}
	protected void setGuestId(String primaryGuestId) {
		this.primaryGuestId = primaryGuestId;
	}
	
	/*
	 * Assign Room gets and sets
	 */
	
	protected void setRoomNumber(String roomNumber){
		this.roomNumber = roomNumber;
	}
	
	public String getRoomNumber(){
		return roomNumber;
	}
	
	protected void setResourceId(String resourceId){
		this.resourceId = resourceId;
	}
	
	public String getResourceId(){
		return resourceId;
	}
	
	protected void setAssignmentOwnerNumber(String assignmentOwnerNumber){
		this.assignmentOwnerNumber = assignmentOwnerNumber;
	}
	
	public String getAssignmentOwnerNumber(){
		return assignmentOwnerNumber;
	}
	
	/*
	 * Make Payment gets and sets
	 */
	
	protected void setFolioId(String folioId){
		this.folioId = folioId;
	}
	
	public String getFolioId(){
		return folioId;
	}
	
	protected void setDepositDue(String depositDue){
		this.depositDue = depositDue;
	}
	
	public String getDepositDue(){
		return depositDue;
	}
	
	public void setBalanceDue(String balanceDue){
		this.balanceDue = balanceDue;
	}
	
	public String getBalanceDue(){
		return balanceDue;
	}
	
	public String getOriginalTransactionId() {
		return originalTransactionId;
	}

	protected void setOriginalTransactionId(String originalTransactionId) {
		this.originalTransactionId = originalTransactionId;
	}

	public String getLatestTransactionId() {
		return latestTransactionId;
	}

	protected void setLatestTransactionId(String latestTransactionId) {
		this.latestTransactionId = latestTransactionId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	protected void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getRRNNumber() {
		return rrnNumber;
	}

	protected void setRRNNumber(String rrnNumber) {
		this.rrnNumber = rrnNumber;
	}

	public String getRRNKey() {
		return rrnKey;
	}

	protected void setRRNKey(String rrnKey) {
		this.rrnKey = rrnKey;
	}

	
	/*
	 * DVC setters & getters
	 */
	
	public String getMembershipRefID() {
		return membershipRefID;
	}

	protected void setMembershipRefID(String membershipRefID) {
		this.membershipRefID = membershipRefID;
	}

	public String getDVCMemberID() {
		return DVCMemberID;
	}

	public void setDVCMemberID(String DVCMemberID) {
		this.DVCMemberID = DVCMemberID;
	}

	public String getVacationType() {
		return vacationType;
	}

	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}

	public String getResortLocation() {
		return resortLocation;
	}

	public void setResortLocation(String resortLocation) {
		this.resortLocation = resortLocation;
	}
	
	public String getDVCresortCode() {
		return DVCresortCode;
	}

	public void setDVCresortCode(String dVCresortCode) {
		DVCresortCode = dVCresortCode;
	}
	
	/*
	 * Guest Media Gets and Sets
	 */
	public String getKttwId(){
		return kttwId;
	}
	
	protected void setKttwId(String kttwId){
		this.kttwId = kttwId;
	}
	
	public String getEncoderId(){
		return encoderId;
	}
	
	protected void setEncoderId(String encoderId){
		this.encoderId = encoderId;
	}
	
	public String getManufacturerUID(){
		return manufacturerUID;
	}
	
	protected void setManufacturerUID(String manufacturerUID){
		this.manufacturerUID = manufacturerUID;
	}
	
	public String getRfidMediaId(){
		return rfidMediaId;
	}
	
	protected void setRfidMediaId(String rfidMediaId){
		this.rfidMediaId = rfidMediaId;
	}
	
	public String getAccessEncodingStatus(){
		return accessEncodingStatus;
	}
	
	protected void setAccessEncodingStatus(String accessEncodingStatus){
		this.accessEncodingStatus = accessEncodingStatus;
	}
	
	public String getGuestRoomIndicator(){
		return guestRoomIndicator;
	}
	
	protected void setGuestRoomIndicator(String guestRoomIndicator){
		this.guestRoomIndicator = guestRoomIndicator;
	}
	
	public String getMediaAccessId(){
		return mediaAccessId;
	}
	
	protected void setMediaAccessId(String mediaAccessId){
		this.mediaAccessId = mediaAccessId;
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a room only booking using the accommadtion sales book service
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookRoomOnly(){
		//instantiate
		Book book = new Book(getEnvironment(), "bookWithoutTickets");
		
		//set dynamic data
		book.setArrivalDate(getArrivalDaysOut());
		book.setDeptDate(getDepartureDaysOut());
		book.setPackageCode(getPackageCode());
		book.setResortCode(getResortCode());
		book.setRoomTypeCode(getRoomTypeCode());
		book.setLocationID(getLocationId());
		book.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
		book.setPrimaryGuestLastName(getPrimaryGuestLastName());
		if(getPhoneNumber() != null) book.setPhoneNumber(getPhoneNumber());
		if(getEmail() != null)book.setEmail(getEmail());
		phoneNumber = book.getPrimaryPhoneNumber();
		conversationIDs.put("book", book.getConversationID());
		
		book.sendRequest();
		Assert.assertEquals(book.getResponseStatusCode(), "200","Response was not 200 " + book.getResponse());
	
		//get the returned travel plan info
		setTravelPlanSegmentId(book.getTravelPlanSegmentId());
        setTravelComponentGroupingId(book.getTravelComponentGroupingId());
        setTravelComponentId(book.getTravelComponentId());
        setTravelPlanId(book.getTravelPlanId());
        setPrimaryGuestFirstName(book.getPrimaryGuestFirstName());
        setPrimaryGuestLastName(book.getPrimaryGuestLastName());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setRequestNodeValueByXPath("//request/travelPlanId", getTravelPlanId());        
        retrieve.setRequestNodeValueByXPath("//request/locationId", getLocationId());
        retrieve.sendRequest();
        Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");	
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a room only booking using the accommadtion sales book service
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookRoomOnlyTwoAdults(){
		//instantiate
		Book book = new Book(getEnvironment(), "book2AdultsWithoutTickets");
		
		//set dynamic data
		book.setArrivalDate(getArrivalDaysOut());
		book.setDeptDate(getDepartureDaysOut());
		book.setPackageCode(getPackageCode());
		book.setResortCode(getResortCode());
		book.setRoomTypeCode(getRoomTypeCode());
		book.setLocationID(getLocationId());
		book.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
		book.setPrimaryGuestLastName(getPrimaryGuestLastName());
		
		//send the request
		System.out.println(book.sendRequest());

		Assert.assertEquals(book.getResponseStatusCode(), "200","Response was not 200 " + book.getResponse());
	
		//get the returned travel plan info
		setTravelPlanSegmentId(book.getTravelPlanSegmentId());
        setTravelComponentGroupingId(book.getTravelComponentGroupingId());
        setTravelComponentId(book.getTravelComponentId());
        setTravelPlanId(book.getTravelPlanId());
        setPrimaryGuestFirstName(book.getPrimaryGuestFirstName());
        setPrimaryGuestLastName(book.getPrimaryGuestLastName());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setRequestNodeValueByXPath("//request/travelPlanId", getTravelPlanId());        
        retrieve.setRequestNodeValueByXPath("//request/locationId", getLocationId());
       retrieve.sendRequest();
        
        Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");	
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());guestIds = new String[2];
        guestIds[0] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[1]/guest/guestId");
        guestIds[1] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[2]/guest/guestId");
        partyIds[0] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[1]/guest/partyId");
        partyIds[1] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[2]/guest/partyId");
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a room only booking using the accommadtion sales book service
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookRoomOnlyTwoAdultsTwoChildren(){

		//instantiate
		Book book = new Book(getEnvironment(), "bookRoomOnly2Adults2ChildrenWithoutTickets");
		
		//set dynamic data
		book.setArrivalDate(getArrivalDaysOut());
		book.setDeptDate(getDepartureDaysOut());
		book.setPackageCode(getPackageCode());
		book.setResortCode(getResortCode());
		book.setRoomTypeCode(getRoomTypeCode());
		book.setLocationID(getLocationId());
		book.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
		book.setPrimaryGuestLastName(getPrimaryGuestLastName());
		
		//send the request
		book.sendRequest();
		Assert.assertEquals(book.getResponseStatusCode(), "200","Response was not 200");
	
		//get the returned travel plan info
		setTravelPlanSegmentId(book.getTravelPlanSegmentId());
        setTravelComponentGroupingId(book.getTravelComponentGroupingId());
        setTravelComponentId(book.getTravelComponentId());
        setTravelPlanId(book.getTravelPlanId());
        setPrimaryGuestFirstName(book.getPrimaryGuestFirstName());
        setPrimaryGuestLastName(book.getPrimaryGuestLastName());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setRequestNodeValueByXPath("//request/travelPlanId", getTravelPlanId());        
        retrieve.setRequestNodeValueByXPath("//request/locationId", getLocationId());
        retrieve.sendRequest();
        Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");	
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());guestIds = new String[4];
        guestIds[0] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[1]/guest/guestId");
        guestIds[1] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[2]/guest/guestId");
        guestIds[2] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[3]/guest/guestId");
        guestIds[3] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[4]/guest/guestId");
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a room only booking using the accommadtion sales book service
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookWDTCTwoAdultsTwoChildren(){

		//instantiate
		Book book = new Book(getEnvironment(), "bookWDTC2Adults2ChildrenWithoutTickets");
		
		//set dynamic data
		book.setArrivalDate(getArrivalDaysOut());
		book.setDeptDate(getDepartureDaysOut());
		book.setPackageCode(getPackageCode());
		book.setResortCode(getResortCode());
		book.setRoomTypeCode(getRoomTypeCode());
		book.setLocationID(getLocationId());
		book.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
		book.setPrimaryGuestLastName(getPrimaryGuestLastName());
		
		//send the request
		book.sendRequest();
		System.out.println(book.getResponse());
		Assert.assertEquals(book.getResponseStatusCode(), "200","Response was not 200");
	
		//get the returned travel plan info
		setTravelPlanSegmentId(book.getTravelPlanSegmentId());
        setTravelComponentGroupingId(book.getTravelComponentGroupingId());
        setTravelComponentId(book.getTravelComponentId());
        setTravelPlanId(book.getTravelPlanId());
        setPrimaryGuestFirstName(book.getPrimaryGuestFirstName());
        setPrimaryGuestLastName(book.getPrimaryGuestLastName());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setRequestNodeValueByXPath("//request/travelPlanId", getTravelPlanId());        
        retrieve.setRequestNodeValueByXPath("//request/locationId", getLocationId());
        retrieve.sendRequest();
        Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");	
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());guestIds = new String[4];
        guestIds[0] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[1]/guest/guestId");
        guestIds[1] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[2]/guest/guestId");
        guestIds[2] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[3]/guest/guestId");
        guestIds[3] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[4]/guest/guestId");
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a room only booking using the accommadtion sales book service
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookWholesaleTwoAdultsNoTickets(){

		//instantiate
		Book book = new Book(getEnvironment(), "bookWholesale2AdultsWithoutTickets");
		
		//set dynamic data
		book.setArrivalDate(getArrivalDaysOut());
		book.setDeptDate(getDepartureDaysOut());
		book.setPackageCode(getPackageCode());
		book.setResortCode(getResortCode());
		book.setRoomTypeCode(getRoomTypeCode());
		book.setLocationID(getLocationId());
		book.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
		book.setPrimaryGuestLastName(getPrimaryGuestLastName());
		
		//send the request
		book.sendRequest();
		conversationIDs.put("roomOnlyBook", book.getConversationID());
		Assert.assertEquals(book.getResponseStatusCode(), "200","Response was not 200");
	
		//get the returned travel plan info
		setTravelPlanSegmentId(book.getTravelPlanSegmentId());
        setTravelComponentGroupingId(book.getTravelComponentGroupingId());
        setTravelComponentId(book.getTravelComponentId());
        setTravelPlanId(book.getTravelPlanId());
        setPrimaryGuestFirstName(book.getPrimaryGuestFirstName());
        setPrimaryGuestLastName(book.getPrimaryGuestLastName());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setRequestNodeValueByXPath("//request/travelPlanId", getTravelPlanId());        
        retrieve.setRequestNodeValueByXPath("//request/locationId", getLocationId());
        retrieve.sendRequest();
        Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");	
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());
        guestIds = new String[2];
        guestIds[0] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[1]/guest/guestId");
        guestIds[1] = retrieve.getResponseNodeValueByXPath("/Envelope/Body/retrieveResponse/travelPlanInfo/travelPlanGuests[2]/guest/guestId");
	}
	
	public void quickBook(){
	        Quickbook quickbook = new Quickbook(getEnvironment(), "UI Booking");
	        
	        quickbook.setArrivalDaysOut(getArrivalDaysOut());
	        arrivalDate = quickbook.getArrivalDate();
	        quickbook.setDepartureDaysOut(getDepartureDaysOut());
	        departureDate = quickbook.getDepartureDate();
	        quickbook.setNumberOfAdults(getNumberOfAdults());
	        quickbook.setNumberOfRooms(getNumberOfRooms());
	        quickbook.setPackageCode(getPackageCode());
	        quickbook.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
	        quickbook.setPrimaryGuestLastName(getPrimaryGuestLastName());
	        quickbook.setResortCode(getResortCode());
	        quickbook.setRoomTypeCode(getRoomTypeCode());
	        quickbook.setLocationId(getLocationId());
	        
	        quickbook.sendRequest();
	        Assert.assertEquals(quickbook.getResponseStatusCode(), "200","Response was not 200. " + quickbook.getResponse());	
	        conversationIDs.put("api"+Randomness.randomNumber(4), quickbook.getConversationID());
	        
	        setTravelPlanId(quickbook.getTravelPlanId());
	        
	        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
	        retrieve.setTravelPlanId(getTravelPlanId());        
	        retrieve.setLocationId(getLocationId());
	        retrieve.sendRequest();
	        TestReporter.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");		
			
	        setTravelPlanSegmentId(retrieve.getTravelPlanSegmentId());
	        setTravelComponentGroupingId(retrieve.getTravelComponentGroupingId());
	        setTravelComponentId(retrieve.getTravelComponentId());
	        setPartyId(retrieve.getPartyId());
	        int numGuests = Integer.parseInt(getNumberOfAdults());
	        guestIds = new String[numGuests];
	        for(int guest = 0; guest < numGuests; guest++){
	        	guestIds[guest] = retrieve.getResponseNodeValueByXPath("/*[local-name(.)='Envelope'][1]/*[local-name(.)='Body'][1]/*[local-name(.)='retrieveResponse'][1]/*[local-name(.)='travelPlanInfo'][1]/*[local-name(.)='travelPlanGuests']["+(guest + 1)+"]/*[local-name(.)='guest'][1]/*[local-name(.)='guestId'][1]");
	        }
	        setGuestId(retrieve.getGuestId());
	}
	
	public void quickBookWithAddress(){
        Quickbook quickbook = new Quickbook(getEnvironment(), "Booking with Address");
        
        quickbook.setArrivalDaysOut(getArrivalDaysOut());
        arrivalDate = quickbook.getArrivalDate();
        quickbook.setDepartureDaysOut(getDepartureDaysOut());
        departureDate = quickbook.getDepartureDate();
        quickbook.setNumberOfAdults(getNumberOfAdults());
        quickbook.setNumberOfRooms(getNumberOfRooms());
        quickbook.setPackageCode(getPackageCode());
        quickbook.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
        quickbook.setPrimaryGuestLastName(getPrimaryGuestLastName());
        quickbook.setResortCode(getResortCode());
        quickbook.setRoomTypeCode(getRoomTypeCode());
        quickbook.setLocationId(getLocationId());

    	if(!primaryGuestAddress1.isEmpty()) quickbook.setPrimaryGuestAddress1(primaryGuestAddress1);
    	if(!primaryGuestCity.isEmpty()) quickbook.setPrimaryGuestCity(primaryGuestCity);
    	if(!primaryGuestState.isEmpty()) quickbook.setPrimaryGuestPostalCode(primaryGuestPostalCode);
    	if(!primaryGuestPostalCode.isEmpty()) quickbook.setPrimaryGuestState(primaryGuestState);        	
        	
        quickbook.sendRequest();
        Assert.assertEquals(quickbook.getResponseStatusCode(), "200","Response was not 200");	
        
        setTravelPlanId(quickbook.getTravelPlanId());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setTravelPlanId(getTravelPlanId());        
        retrieve.setLocationId(getLocationId());
        retrieve.sendRequest();
        TestReporter.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");		
		
        setTravelPlanSegmentId(retrieve.getTravelPlanSegmentId());
        setTravelComponentGroupingId(retrieve.getTravelComponentGroupingId());
        setTravelComponentId(retrieve.getTravelComponentId());
        setPartyId(retrieve.getPartyId());
        int numGuests = Integer.parseInt(getNumberOfAdults());
        guestIds = new String[numGuests];
        for(int guest = 0; guest < numGuests; guest++){
        	guestIds[guest] = retrieve.getResponseNodeValueByXPath("/*[local-name(.)='Envelope'][1]/*[local-name(.)='Body'][1]/*[local-name(.)='retrieveResponse'][1]/*[local-name(.)='travelPlanInfo'][1]/*[local-name(.)='travelPlanGuests']["+(guest + 1)+"]/*[local-name(.)='guest'][1]/*[local-name(.)='guestId'][1]");
        }
        setGuestId(retrieve.getGuestId());
}

	public void quickBookMain(){
        Quickbook quickbook = new Quickbook(getEnvironment(), "Main");
        
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/arrivalDate", "fx:GetDate; Daysout:" + getArrivalDaysOut());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/departureDate", "fx:GetDate; Daysout:" + getDepartureDaysOut());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/numberOfAdults", getNumberOfAdults());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/numberOfRooms", getNumberOfRooms());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/packageCode", getPackageCode());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/primaryGuestDetail/firstName", getPrimaryGuestFirstName());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/primaryGuestDetail/lastName", getPrimaryGuestLastName());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/resortCode", getResortCode());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/roomTypeCode", getRoomTypeCode());
        quickbook.setRequestNodeValueByXPath("//quickBookRequest/locationId", getLocationId());
        
        quickbook.sendRequest();
        setResponseStatusCode(quickbook.getResponseStatusCode());
        Assert.assertEquals(getResponseStatusCode(), "200","Response was not 200");	
        
        setTravelPlanId(quickbook.getTravelPlanId());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setRequestNodeValueByXPath("//request/travelPlanId", getTravelPlanId());        
        retrieve.setRequestNodeValueByXPath("//request/locationId", getLocationId());
        retrieve.sendRequest();
        Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200 " + quickbook.getResponse());	
		
        setTravelPlanSegmentId(retrieve.getTravelPlanSegmentId());
        setTravelComponentGroupingId(retrieve.getTravelComponentGroupingId());
        setTravelComponentId(retrieve.getTravelComponentId());
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());
}
	
	public void setResponseStatusCode(String code){
		responseCode = code;
	}
	
	public String getResponseStatusCode(){
		return responseCode;
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a group booking using the accommodation sales book
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookGroupBooking(){
		//instantiate
		Book book = new Book(getEnvironment(), "bookGroupPackageWithoutTickets");
		
		//set dynamic data
		book.setArrivalDate(getArrivalDaysOut());
		book.setDeptDate(getDepartureDaysOut());
		book.setPackageCode(getPackageCode());
		book.setResortCode(getResortCode());
		book.setRoomTypeCode(getRoomTypeCode());
		book.setLocationID(getLocationId());
		book.setPrimaryGuestFirstName(getPrimaryGuestFirstName());
		book.setPrimaryGuestLastName(getPrimaryGuestLastName());
		book.setBlockCode("01905");
		phoneNumber = book.getPrimaryPhoneNumber();
		
		//send the request
		book.sendRequest();
		Assert.assertEquals(book.getResponseStatusCode(), "200","Response was not 200");
	
		//get the returned travel plan info
		setTravelPlanSegmentId(book.getTravelPlanSegmentId());
        setTravelComponentGroupingId(book.getTravelComponentGroupingId());
        setTravelComponentId(book.getTravelComponentId());
        setTravelPlanId(book.getTravelPlanId());
        setPrimaryGuestFirstName(book.getPrimaryGuestFirstName());
        setPrimaryGuestLastName(book.getPrimaryGuestLastName());
		
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setRequestNodeValueByXPath("//request/travelPlanId", getTravelPlanId());        
        retrieve.setRequestNodeValueByXPath("//request/locationId", getLocationId());
        retrieve.sendRequest();
        Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");	
        
        setTravelPlanSegmentId(retrieve.getTravelPlanSegmentId());
        setTravelComponentGroupingId(retrieve.getTravelComponentGroupingId());
        setTravelComponentId(retrieve.getTravelComponentId());
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a DVC cash reservation
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookDVCCash(){
		//instantiate
		Book book = new Book(getEnvironment(), "bookDVCCash");
		SearchMemberships searchMemberships = null;
		try {
			searchMemberships = new SearchMemberships(getEnvironment(), "Main");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//set the dynamic data for search memberships.
		//Search memberships is used to get the membershipRefID which is based off the DVC member id
		searchMemberships.setMembershipID(getDVCMemberID());
		searchMemberships.setMemberID(getDVCMemberID());
		
		searchMemberships.sendRequest();
		Assert.assertEquals(searchMemberships.getResponseStatusCode(), "200","Response was not 200");
		
		//From the response, grab the membershipRefID and set itin this class
		setMembershipRefID(searchMemberships.getMembershipRefID());
		
		//set the dynamic data for freeze inventory
		FreezeInventory freezeInventory = new FreezeInventory(getEnvironment(), "FreezeDVCCash");
		freezeInventory.setMemberRefId(getDVCMemberID());
		freezeInventory.setAgeCount(getMembershipRefID());
		freezeInventory.setArrivalDate("fx:GetDate; Daysout:" + getArrivalDaysOut());
		freezeInventory.setDeptDate("fx:GetDate; Daysout:" + getDepartureDaysOut());
		freezeInventory.setFacilityID(getDVCresortCode());
		freezeInventory.setDreamsLocationCode(getLocationId());
		freezeInventory.setRoomTypeCode(getRoomTypeCode());
		
		freezeInventory.sendRequest();
		Assert.assertEquals(freezeInventory.getResponseStatusCode(), "200","Response was not 200");
		
		//From the repsonse, set the inventory tracking ID
		setInventoryTrackingID(freezeInventory.getInventoryTrackingID());
				
		//set dynamic data
		book.setArrivalDate(getArrivalDaysOut());
		book.setDeptDate(getDepartureDaysOut());
		book.setPackageCode(getPackageCode());
		book.setResortCode(getResortCode());
		book.setRoomTypeCode(getRoomTypeCode());
		book.setLocationID(getLocationId());
		book.setInventoryTrackingID(getInventoryTrackingID());
		
		//send the request
		book.sendRequest();
		Assert.assertEquals(book.getResponseStatusCode(), "200","Response was not 200");
	
		//get the returned travel plan info
		setTravelPlanSegmentId(book.getTravelPlanSegmentId());
        setTravelComponentGroupingId(book.getTravelComponentGroupingId());
        setTravelComponentId(book.getTravelComponentId());
        setTravelPlanId(book.getTravelPlanId());
        
        makeFullPayment();
		
	}
	
	/**
	 * @throws 		NA	
	 * @Summary		Makes a DVC cash reservation
	 * @param	
	 * @Author		Jessica Marshall
	 * @Version		Created: 11/7/2014
	 * @Return		NA
	 */
	public void bookDVCMemberPoints(){
		
	}
	public void assignRoomToReservation(){
	 	/*
		 * Occassionally rooms are assigned to other rooms. Loop 5 times,
		 * sleeping a second after each iteration, in am attempt to find a
		 * vacant room. Example fault string: Room:1725 is already Assigned or
		 * on Hold to some other Reservation or is in OOI/CLF status, Please
		 * select another Room
		 */
		int counter = 0;
		int maxTries = 5;
		boolean success = false;
		FindRoomForReservation findRoom = null;
		AssignRoomForReservation assignRoom = null;
		do{
			findRoom = new FindRoomForReservation(getEnvironment(), "UI Booking");
	    	findRoom.setTravelPlanId(getTravelPlanId());
	    	findRoom.sendRequest();
	    	Assert.assertEquals(findRoom.getResponseStatusCode(), "200","Response was not 200");	
	    	
	    	setRoomNumber(findRoom.getFirstRoomNumber());
	    	setResourceId(findRoom.getRoomResourceNumber());
	    	setAssignmentOwnerNumber(findRoom.getAssignmentOwnerNumber());
	    	
	    	assignRoom = new AssignRoomForReservation(getEnvironment(), "UI Booking");
	    	assignRoom.setArrivalAndDepartureDaysOut(getArrivalDaysOut(), getDepartureDaysOut());
	    	assignRoom.setAssignmentOwnerNumber(getAssignmentOwnerNumber());
	    	assignRoom.setFacilityId(getFacilityId());
	    	assignRoom.setRoomNumber(getRoomNumber());
	    	assignRoom.setRoomResourceNumber(getResourceId());
	    	assignRoom.sendRequest();
	    	if(!assignRoom.getResponseStatusCode().equalsIgnoreCase("200")){
	    		TestReporter.log(assignRoom.getResponse());
	    	}
	    	if(assignRoom.getResponseStatusCode().equalsIgnoreCase("true")){
	    		success = true;
	    	}else{
	    		Sleeper.sleep(1000);
	    	}
	    	counter++;
		}while(!success && counter < maxTries);
		Assert.assertTrue(counter < maxTries, "No room was found to be available for room type code ["+getRoomTypeCode()+"].");
		Assert.assertEquals(assignRoom.getResponseStatusCode(), "200","Response was not 200");	
	}
	
	public void assignNotReadyRoomToReservation(){

	 	FindRoomForReservation findRoom = new FindRoomForReservation(getEnvironment(), "UI Booking");
    	findRoom.setTravelPlanId(getTravelPlanId());
    	findRoom.sendRequest();
    	Assert.assertEquals(findRoom.getResponseStatusCode(), "200","Response was not 200");	
    	
    	setRoomNumber(findRoom.getFirstRoomNumber());
    	setResourceId(findRoom.getRoomResourceNumber());
    	setAssignmentOwnerNumber(findRoom.getAssignmentOwnerNumber());
    	
    	UpdateSingleRoomStatus updateStatus = new UpdateSingleRoomStatus(getEnvironment(), "updateToDirtyAndVacant");
    	updateStatus.setResourceId(resourceId);
    	updateStatus.setRoomNumber(roomNumber);
    	updateStatus.sendRequest();
    	Assert.assertEquals(updateStatus.getResponseStatusCode(), "200","Response was not 200");
    	
    	AssignRoomForReservation assignRoom = new AssignRoomForReservation(getEnvironment(), "UI Booking");
    	assignRoom.setArrivalAndDepartureDaysOut(getArrivalDaysOut(), getDepartureDaysOut());
    	assignRoom.setAssignmentOwnerNumber(getAssignmentOwnerNumber());
    	assignRoom.setFacilityId(getFacilityId());
    	assignRoom.setRoomNumber(getRoomNumber());
    	assignRoom.setRoomResourceNumber(getResourceId());
    	assignRoom.sendRequest();
    	Assert.assertEquals(assignRoom.getResponseStatusCode(), "200","Response was not 200");
	}
	
	public void assignRoomToReservation(String roomTypeCode, String environment){
	 	FindRoomForReservation findRoom = new FindRoomForReservation(getEnvironment(), "UI Booking");
    	findRoom.setTravelPlanId(getTravelPlanId());
    	findRoom.sendRequest();
    	Assert.assertEquals(findRoom.getResponseStatusCode(), "200","Response was not 200");	
    	

		OracleDatabase db = new OracleDatabase(environment, "Dreams");	
		Recordset rs = new Recordset(db.getResultSet(ReservationInfo.findVacantRoomByRoomTypeCode(getRoomTypeCode(), getLocationId())));

/*			System.out.println("Example 1");
		for (rs.moveFirst() ; rs.hasNext() ; rs.moveNext()){
			System.out.println(rs.getValue("RSRC_ID") +" " +rs.getValue("RM_NM"));			
		}*/
		
		System.out.println();
		System.out.println();
		rs.print();	
	
    	setRoomNumber(rs.getValue("RM_NM"));
    	setResourceId(rs.getValue("RSRC_ID"));
    	//setAssignmentOwnerNumber(getTravelComponentId());
    	setAssignmentOwnerNumber(findRoom.getAssignmentOwnerNumber());
    	
    	AssignRoomForReservation assignRoom = new AssignRoomForReservation(getEnvironment(), "UI Booking");
    	assignRoom.setArrivalAndDepartureDaysOut(getArrivalDaysOut(), getDepartureDaysOut());
    	assignRoom.setAssignmentOwnerNumber(getAssignmentOwnerNumber());
    	assignRoom.setFacilityId(getFacilityId());
    	assignRoom.setRoomNumber(getRoomNumber());
    	assignRoom.setRoomResourceNumber(getResourceId());
    	assignRoom.sendRequest();
    	Assert.assertEquals(assignRoom.getResponseStatusCode(), "200","Response was not 200");	
	}
	
	public void checkInReservation(){
		CheckIn checkIn = new CheckIn(getEnvironment(),"UI Booking");
		checkIn.setTravelComponentGroupingId(getTravelComponentGroupingId());
		checkIn.setLocationId(getLocationId());
		checkIn.setGuestId(getGuestId());
		checkIn.sendRequest();
		Assert.assertEquals(checkIn.getResponseStatusCode(), "200","Response was not 200");			

		CompleteCheckIn completeCheckIn = new CompleteCheckIn(getEnvironment(),"UI Booking");
		completeCheckIn.setLocationId(getLocationId());
		completeCheckIn.setRoomNumber(getRoomNumber());
		completeCheckIn.setTravelComponentGroupingId(getTravelComponentGroupingId());
		completeCheckIn.setTravelComponentId(getTravelComponentId());
		completeCheckIn.sendRequest();
		Assert.assertEquals(completeCheckIn.getResponseStatusCode(), "200","Response was not 200");	
	}
	
	
	public void makeFullPayment(){
		RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
		retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
		retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
		retrieveBalance.setLocationId(getLocationId());
		retrieveBalance.sendRequest();
		
		Assert.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(retrieveBalance.getPaymentRequired());
		setDepositDue(retrieveBalance.getDepositRequired());
		setFolioId(retrieveBalance.getFolioId());
		
		PostCardPayment postPayment = new PostCardPayment(getEnvironment(),"Visa-CreditCard");
		postPayment.setAmount(getBalanceDue());
		postPayment.setFolioId(getFolioId());
		
		postPayment.setBookingReference(ServiceConstants.BookingSource.DREAMS_TC, getTravelPlanId());
		postPayment.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TC, getTravelComponentId());
		postPayment.setLocationId(getLocationId());
		postPayment.setPartyId(getPartyId());
		postPayment.setPrimaryLastname(getPrimaryGuestLastName());
		postPayment.setTravelPlanId(getTravelPlanId());
		postPayment.setTravelPlanSegmentId(getTravelPlanSegmentId());
		postPayment.setRetreivalReferenceNumber();
		postPayment.sendRequest();
		conversationIDs.put("payment", postPayment.getConversationID());
		Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(postPayment.getFolioBalance());
		setOriginalTransactionId(postPayment.getOriginalTransactionId());
		setLatestTransactionId(postPayment.getTransactionId());
		setRRNKey(postPayment.getRRNKey());
		setRRNNumber(postPayment.getRRNNumber());
		setPaymentId(postPayment.getPaymentId());
		
	}
	
	public void makeCardPaymentNegative(String scenario){		
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		
		cardPaymentType = datatable.getDataParameter("PaymentType");
		cardPaymentMethod = datatable.getDataParameter("PaymentMethod");
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		String ccv = datatable.getDataParameter("EnterCCV");
		String isNegative = datatable.getDataParameter("Negative");
		String errorType = datatable.getDataParameter("ErrorType");
		String errorMessage = datatable.getDataParameter("ErrorMessage");
		
		TestReporter.log("Payment Type: " + cardPaymentType);
		TestReporter.log("Payment Method: " + cardPaymentMethod);
		TestReporter.log("Status: " + status);
		TestReporter.log("Delay: " + delay);
		TestReporter.log("Enter CCV: " + ccv);
		boolean incidentals = true;
		if (datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")) {
			incidentals = false;
		}
		String amount = datatable.getDataParameter("Amount");
		
		/*
		 * Generate A Card
		 */
		GenerateCard card = new GenerateCard();
		Map<String, String> cardInfo = null;
		try {
			cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(cardPaymentMethod));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardNumber = cardInfo.get("AccountNumber").replace("-", "");
		cardExpirationMonth = cardInfo.get("ExpMonth");
		cardExpirationYear = cardInfo.get("ExpYear");
		cardExpirationDate = cardExpirationMonth + "/" + cardExpirationYear;
		cardCcvCode= cardInfo.get("CVV2");
		cardHolderName = cardInfo.get("NameOnCard");
		
		/*
		 * Retrieve Folio Balance Due
		 */
		RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
		retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
		retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
		retrieveBalance.setLocationId(getLocationId());
		retrieveBalance.sendRequest();
		
		TestReporter.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(retrieveBalance.getPaymentRequired());
		setDepositDue(retrieveBalance.getDepositRequired());
		setFolioId(retrieveBalance.getFolioId());
		
		/*
		 * Post Card Payment
		 */
		PostCardPayment postPayment = new PostCardPayment(getEnvironment(),"Visa-CreditCard");
		if(amount.equalsIgnoreCase("total")){
			postPayment.setAmount(getBalanceDue());
		}else{
			postPayment.setAmount(amount.replace("$", "").replace(",", ""));
		}
		postPayment.setFolioId(getFolioId());
		
		postPayment.setBookingReference(ServiceConstants.BookingSource.DREAMS_TC, getTravelPlanId());
		postPayment.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TC, getTravelComponentId());
		postPayment.setLocationId(getLocationId());
		postPayment.setPartyId(getPartyId());
		postPayment.setPrimaryLastname(getPrimaryGuestLastName());
		postPayment.setTravelPlanId(getTravelPlanId());
		postPayment.setTravelPlanSegmentId(getTravelPlanSegmentId());		
		postPayment.setCardNumber(cardNumber);

		postPayment.setRetreivalReferenceNumber();
		if(cardPaymentType.equalsIgnoreCase("PrePaidCard")){
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/expirationDate", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationMonth", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationYear", "fx:RemoveNode");
		}else{
			if(errorType.equalsIgnoreCase("Invalid Expiration")){
				postPayment.setExpirationDate("14/11");
				postPayment.setExpriationMonth("14");
				postPayment.setExpirationYear("11");
			}else{
				postPayment.setExpirationDate(cardExpirationDate);
				postPayment.setExpriationMonth(cardExpirationMonth);
				postPayment.setExpirationYear(cardExpirationYear);
			}
		}
		postPayment.setCardHolderName(cardHolderName);
		if(ccv.equalsIgnoreCase("true")){
			postPayment.setAuthorizationCode(cardCcvCode);
		}else{
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/authorizationCode", "fx:RemoveNode");
		}
		postPayment.setPaymentMethod(cardPaymentMethod);
		postPayment.setPaymentType(cardPaymentType.replace(" ", ""));
//		postPayment.setDeviceTerminalID(getTerminalID());
		postPayment.sendRequest();
		conversationIDs.put("payment", postPayment.getConversationID());
		
		if(isNegative.equalsIgnoreCase("true")){
			System.out.println(postPayment.getFaultString());
			TestReporter.assertTrue(postPayment.getFaultString().contains(errorMessage), "The expected error message ["+errorMessage+"] was not found in the fault string ["+postPayment.getFaultString()+"]");
		}else{
			Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");
			
			setBalanceDue(postPayment.getFolioBalance());
			setOriginalTransactionId(postPayment.getOriginalTransactionId());
			setLatestTransactionId(postPayment.getTransactionId());
			setRRNKey(postPayment.getRRNKey());
			setRRNNumber(postPayment.getRRNNumber());
			setPaymentId(postPayment.getPaymentId());	
		}		
	}
	
	public void makeCardPayment(String scenario){		
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		
		cardPaymentType = datatable.getDataParameter("PaymentType");
		cardPaymentMethod = datatable.getDataParameter("PaymentMethod");
		String status = datatable.getDataParameter("Status");
		String delay = datatable.getDataParameter("Delay");
		String ccv = datatable.getDataParameter("EnterCCV");
		TestReporter.log("Payment Type: " + cardPaymentType);
		TestReporter.log("Payment Method: " + cardPaymentMethod);
		TestReporter.log("Status: " + status);
		TestReporter.log("Delay: " + delay);
		TestReporter.log("Enter CCV: " + ccv);
		boolean incidentals = true;
		if (datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")) {
			incidentals = false;
		}
		String amount = datatable.getDataParameter("Amount");
		
		/*
		 * Generate A Card
		 */
		GenerateCard card = new GenerateCard();
		Map<String, String> cardInfo = null;
		try {
			cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(cardPaymentMethod));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardNumber = cardInfo.get("AccountNumber").replace("-", "");
		cardExpirationMonth = cardInfo.get("ExpMonth");
		cardExpirationYear = cardInfo.get("ExpYear");
		cardExpirationDate = cardExpirationMonth + "/" + cardExpirationYear;
		cardCcvCode= cardInfo.get("CVV2");
		cardHolderName = cardInfo.get("NameOnCard");
		
		/*
		 * Retrieve Terminal ID
		 */
//		CreateOrRetrievePaymentDevice device = new CreateOrRetrievePaymentDevice(getEnvironment(), "CCTID");
//		device.setCampusId(ResortInfo.getResortInfo(ResortInfo.ResortColumns.LOCATION_ID, getLocationId(), ResortInfo.ResortColumns.CAMPUS_ID));
//		device.sendRequest();
//		TestReporter.assertEquals(device.getResponseStatusCode(), "200","Response was not 200");
//		setTerminalID(device.getDeviceTerminalId());
		
		/*
		 * Retrieve Folio Balance Due
		 */
		RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
		retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
		retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
		retrieveBalance.setLocationId(getLocationId());
		retrieveBalance.sendRequest();
		
		TestReporter.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(retrieveBalance.getPaymentRequired());
		setDepositDue(retrieveBalance.getDepositRequired());
		setFolioId(retrieveBalance.getFolioId());
		
		/*
		 * Post Card Payment
		 */
		PostCardPayment postPayment = new PostCardPayment(getEnvironment(),"Visa-CreditCard");
		if(amount.equalsIgnoreCase("total")){
			postPayment.setAmount(getBalanceDue());
		}else{
			postPayment.setAmount(amount.replace("$", "").replace(",", ""));
		}
		postPayment.setFolioId(getFolioId());
		
		postPayment.setBookingReference(ServiceConstants.BookingSource.DREAMS_TC, getTravelPlanId());
		postPayment.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TC, getTravelComponentId());
		postPayment.setLocationId(getLocationId());
		postPayment.setPartyId(getPartyId());
		postPayment.setPrimaryLastname(getPrimaryGuestLastName());
		postPayment.setTravelPlanId(getTravelPlanId());
		postPayment.setTravelPlanSegmentId(getTravelPlanSegmentId());		
		postPayment.setCardNumber(cardNumber);
		postPayment.setRetreivalReferenceNumber();
		if(cardPaymentType.equalsIgnoreCase("PrePaidCard")){
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/expirationDate", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationMonth", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationYear", "fx:RemoveNode");
		}else{
			postPayment.setExpirationDate(cardExpirationDate);
			postPayment.setExpriationMonth(cardExpirationMonth);
			postPayment.setExpirationYear(cardExpirationYear);
		}
		postPayment.setCardHolderName(cardHolderName);
		if(ccv.equalsIgnoreCase("true")){
			postPayment.setAuthorizationCode(cardCcvCode);
		}else{
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/authorizationCode", "fx:RemoveNode");
		}
		postPayment.setPaymentMethod(cardPaymentMethod);
		postPayment.setPaymentType(cardPaymentType.replace(" ", ""));
//		postPayment.setDeviceTerminalID(getTerminalID());
		postPayment.sendRequest();
		
		Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");
		
		setBalanceDue(postPayment.getFolioBalance());
		setOriginalTransactionId(postPayment.getOriginalTransactionId());
		setLatestTransactionId(postPayment.getTransactionId());
		setRRNKey(postPayment.getRRNKey());
		setRRNNumber(postPayment.getRRNNumber());
		setPaymentId(postPayment.getPaymentId());
	}
	
	public void createDayGuest(){
		api.soapServices.travelPlanSalesOrderServiceV1.operations.Create create = 
				new api.soapServices.travelPlanSalesOrderServiceV1.operations.Create(getEnvironment(), "DayGuest"); 
		create.setFacilityId(getFacilityId());
		create.setLocationId(getLocationId());
		create.sendRequest();
        Assert.assertEquals(create.getResponseStatusCode(), "200","Response was not 200. " + create.getResponse());	
        conversationIDs.put("api"+Randomness.randomNumber(4), create.getConversationID());
        
        setTravelPlanId(create.getTravelPlanId());
        
        Retrieve retrieve = new Retrieve(getEnvironment(), "Main");
        retrieve.setTravelPlanId(getTravelPlanId());        
        retrieve.setLocationId(getLocationId());
        retrieve.sendRequest();
        TestReporter.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");		
		
        setTravelPlanSegmentId(retrieve.getTravelPlanSegmentId());
        setPartyId(retrieve.getPartyId());
        setGuestId(retrieve.getGuestId());
        
        String query = "select A.TC_GRP_NB AS \"TCG _ID\", B.TC_ID AS \"TC_ID\""
        		+ "from RES_MGMT.TC_GRP A "
        		+ "join RES_MGMT.TC B on A.TC_GRP_NB = B.TC_GRP_NB "
        		+ "where A.TPS_ID = '"+getTravelPlanSegmentId()+"'";
	    
	    OracleDatabase odb = new OracleDatabase(getEnvironment(), "DREAMS");
		Object[][] resultSet;
		resultSet = odb.getResultSet(query);

		setTravelComponentGroupingId(resultSet[1][0].toString());
		setTravelComponentId(resultSet[1][1].toString());
	}
	
	public void makeCheckPayment(){	
		/*
		 * Retrieve Folio Balance Due
		 */
		RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
		retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
		retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
		retrieveBalance.setLocationId(getLocationId());
		retrieveBalance.sendRequest();
		
		TestReporter.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(retrieveBalance.getPaymentRequired());
		setDepositDue(retrieveBalance.getDepositRequired());
		setFolioId(retrieveBalance.getFolioId());
		
		/*
		 * Post Check Payment
		 */
		PostCheckPayment postPayment = new PostCheckPayment(environment, "Main");
		postPayment.setAuthorizationNumber(Randomness.randomNumber(4).toString());
		postPayment.setCheckNumber(Randomness.randomNumber(4).toString());
		postPayment.setConvertedAmount(getBalanceDue());
		postPayment.setTenderedAmount(getBalanceDue());
		postPayment.setDocumentOriginator(getPrimaryGuestFirstName() + " " + getPrimaryGuestLastName());
		postPayment.setFolioId(getFolioId());
		postPayment.setLocationId(getLocationId());
		postPayment.setPostingDate(Randomness.generateCurrentXMLDate());
		postPayment.sendRequest();
		
		Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");
		
		setBalanceDue(postPayment.getFolioBalance());
		setOriginalTransactionId(postPayment.getOriginalTransactionId());
		setLatestTransactionId(postPayment.getTransactionId());
		setPaymentId(postPayment.getPaymentId());
	}
	
	public void makeCardPaymentCardOnFile(String scenario){		
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		
		String ccv = datatable.getDataParameter("EnterCCV");
		String amount = datatable.getDataParameter("Amount");
		
		/*
		 * Retrieve Terminal ID
		 */
		CreateOrRetrievePaymentDevice device = new CreateOrRetrievePaymentDevice(getEnvironment(), "CCTID");
		device.setCampusId(ResortInfo.getResortInfo(ResortInfo.ResortColumns.LOCATION_ID, getLocationId(), ResortInfo.ResortColumns.CAMPUS_ID));
		device.sendRequest();
		TestReporter.assertEquals(device.getResponseStatusCode(), "200","Response was not 200");
		setTerminalID(device.getDeviceTerminalId());
		
		/*
		 * Retrieve Folio Balance Due
		 */
		RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
		retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
		retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
		retrieveBalance.setLocationId(getLocationId());
		retrieveBalance.sendRequest();
		
		TestReporter.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(retrieveBalance.getPaymentRequired());
		setDepositDue(retrieveBalance.getDepositRequired());
		setFolioId(retrieveBalance.getFolioId());
		
		/*
		 * Post Card Payment
		 */
		PostCardPayment postPayment = new PostCardPayment(getEnvironment(),"Visa-CreditCard");
		if(amount.equalsIgnoreCase("total") || amount.isEmpty()){
			postPayment.setAmount(getBalanceDue());
		}else{
			postPayment.setAmount(amount.replace("$", "").replace(",", ""));
		}
		postPayment.setFolioId(getFolioId());
		
		postPayment.setBookingReference(ServiceConstants.BookingSource.DREAMS_TC, getTravelPlanId());
		postPayment.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TC, getTravelComponentId());
		postPayment.setLocationId(getLocationId());
		postPayment.setPartyId(getPartyId());
		postPayment.setPrimaryLastname(getPrimaryGuestLastName());
		postPayment.setTravelPlanId(getTravelPlanId());
		postPayment.setTravelPlanSegmentId(getTravelPlanSegmentId());		
		postPayment.setCardNumber(cardNumber);
		postPayment.setRetreivalReferenceNumber();
		if(cardPaymentType.equalsIgnoreCase("PrePaidCard")){
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/expirationDate", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationMonth", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationYear", "fx:RemoveNode");
		}else{
			postPayment.setExpirationDate(cardExpirationDate);
			postPayment.setExpriationMonth(cardExpirationMonth);
			postPayment.setExpirationYear(cardExpirationYear);
		}
		postPayment.setCardHolderName(cardHolderName);
		if(ccv.equalsIgnoreCase("true") && !(cardCcvCode == null)){
			postPayment.setAuthorizationCode(cardCcvCode);
		}else{
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/authorizationCode", "fx:RemoveNode");
		}
		postPayment.setPaymentMethod(cardPaymentMethod);
		postPayment.setPaymentType(cardPaymentType.replace(" ", ""));
//		postPayment.setDeviceTerminalID(getTerminalID());
		postPayment.sendRequest();
		conversationIDs.put("payment", postPayment.getConversationID());
		Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");
		
		setBalanceDue(postPayment.getFolioBalance());
		setOriginalTransactionId(postPayment.getOriginalTransactionId());
		setLatestTransactionId(postPayment.getTransactionId());
		setRRNKey(postPayment.getRRNKey());
		setRRNNumber(postPayment.getRRNNumber());
		setPaymentId(postPayment.getPaymentId());		
	}
	
	public void makeSplitCardPayment(String scenario){		
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		
		cardPaymentTypes = datatable.getDataParameter("PaymentType").split(";");
		cardPaymentMethods = datatable.getDataParameter("PaymentMethod").split(";");
		String[] status = datatable.getDataParameter("Status").split(";");
		String[] delay = datatable.getDataParameter("Delay").split(";");
		String[] ccv = datatable.getDataParameter("EnterCCV").split(";");
		String[] incidentals = datatable.getDataParameter("Incidentals").split(";");
		String[] amounts = datatable.getDataParameter("Amount").split(";");
		
		for(int payments = 0; payments < cardPaymentTypes.length; payments++){
			TestReporter.log("Payment Type [" + payments + "]: " + cardPaymentTypes[payments]);
			TestReporter.log("Payment Method [" + payments + "]: " + cardPaymentMethods[payments]);
			TestReporter.log("Status [" + payments + "]: " + status[payments]);
			TestReporter.log("Delay [" + payments + "]: " + delay[payments]);
			TestReporter.log("Enter CCV [" + payments + "]: " + ccv[payments]);
			boolean incidental = true;
			if (incidentals[payments].equalsIgnoreCase("false")) {
				incidental = false;
			}
			String amount = amounts[payments];
			
			/*
			 * Generate A Card
			 */
			GenerateCard card = new GenerateCard();
			Map<String, String> cardInfo = null;
			try {
				cardInfo = card.getCardInfo(status[payments], delay[payments], card.getCradTypeEnum(cardPaymentMethods[payments]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cardNumbers[payments] = cardInfo.get("AccountNumber").replace("-", "");
			cardExpirationMonths[payments] = cardInfo.get("ExpMonth");
			cardExpirationYears[payments] = cardInfo.get("ExpYear");
			cardExpirationDates[payments] = cardExpirationMonths[payments] + "/" + cardExpirationYears[payments];
			cardCcvCodes[payments] = cardInfo.get("CVV2");
			cardHolderNames[payments] = cardInfo.get("NameOnCard");
			
			/*
			 * Retrieve Folio Balance Due
			 */
			RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
			retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
			retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
			retrieveBalance.setLocationId(getLocationId());
			retrieveBalance.sendRequest();
			
			TestReporter.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
			
			setBalanceDue(retrieveBalance.getPaymentRequired());
			setDepositDue(retrieveBalance.getDepositRequired());
			setFolioId(retrieveBalance.getFolioId());
			
			/*
			 * Post Card Payment
			 */
			PostCardPayment postPayment = new PostCardPayment(getEnvironment(),"Visa-CreditCard");
			if(amount.equalsIgnoreCase("total")){
				postPayment.setAmount(getBalanceDue());
			}else{
				postPayment.setAmount(amounts[payments].replace("$", "").replace(",", ""));
			}
			postPayment.setFolioId(getFolioId());
			
			postPayment.setBookingReference(ServiceConstants.BookingSource.DREAMS_TC, getTravelPlanId());
			postPayment.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TC, getTravelComponentId());
			postPayment.setLocationId(getLocationId());
			postPayment.setPartyId(getPartyId());
			postPayment.setPrimaryLastname(getPrimaryGuestLastName());
			postPayment.setTravelPlanId(getTravelPlanId());
			postPayment.setTravelPlanSegmentId(getTravelPlanSegmentId());		
			postPayment.setCardNumber(cardNumbers[payments]);
			if(cardPaymentTypes[payments].equalsIgnoreCase("PrePaidCard")){
				postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/expirationDate", "fx:RemoveNode");
				postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationMonth", "fx:RemoveNode");
				postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationYear", "fx:RemoveNode");
			}else{
				postPayment.setExpirationDate(cardExpirationDates[payments]);
				postPayment.setExpriationMonth(cardExpirationMonths[payments]);
				postPayment.setExpirationYear(cardExpirationYears[payments]);
			}
			postPayment.setCardHolderName(cardHolderNames[payments]);
			if(ccv[payments].equalsIgnoreCase("true")){
				postPayment.setAuthorizationCode(cardCcvCodes[payments]);
			}else{
				postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/authorizationCode", "fx:RemoveNode");
			}
			postPayment.setPaymentMethod(cardPaymentMethods[payments]);
			postPayment.setPaymentType(cardPaymentTypes[payments].replace(" ", ""));
			postPayment.setRetreivalReferenceNumber();
			postPayment.sendRequest();
			conversationIDs.put("payment" + payments, postPayment.getConversationID());
			Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");
			
			setBalanceDue(postPayment.getFolioBalance());
			rrnNumbers[payments] = postPayment.getRRNNumber();
			paymentIds[payments] = postPayment.getPaymentId();
		}		
	}
	
	public void applyCreditToExistingCard(String scenario, String creditAmount){		
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);
		
		String ccv = datatable.getDataParameter("EnterCCV");
		TestReporter.log("Payment Type: " + cardPaymentType);
		TestReporter.log("Payment Method: " + cardPaymentMethod);
		TestReporter.log("Enter CCV: " + ccv);
		boolean incidentals = true;
		if (datatable.getDataParameter("Incidentals").equalsIgnoreCase("false")) {
			incidentals = false;
		}
		String amount = creditAmount;
		
		/*
		 * Post Card Payment
		 */
		PostCardPayment postPayment = new PostCardPayment(getEnvironment(),"Visa-CreditCard");
		if(amount.equalsIgnoreCase("total")){
			postPayment.setAmount(getBalanceDue());
		}else{
			postPayment.setAmount(amount.replace("$", "").replace(",", ""));
		}
		postPayment.setFolioId(getFolioId());
		
		postPayment.setBookingReference(ServiceConstants.BookingSource.DREAMS_TC, getTravelPlanId());
		postPayment.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TC, getTravelComponentId());
		postPayment.setLocationId(getLocationId());
		postPayment.setPartyId(getPartyId());
		postPayment.setPrimaryLastname(getPrimaryGuestLastName());
		postPayment.setTravelPlanId(getTravelPlanId());
		postPayment.setTravelPlanSegmentId(getTravelPlanSegmentId());		
		postPayment.setCardNumber(cardNumber);

		if(cardPaymentType.equalsIgnoreCase("PrePaidCard")){
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/expirationDate", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationMonth", "fx:RemoveNode");
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/expirationYear", "fx:RemoveNode");
		}else{
			postPayment.setExpirationDate(cardExpirationDate);
			postPayment.setExpriationMonth(cardExpirationMonth);
			postPayment.setExpirationYear(cardExpirationYear);
		}
		postPayment.setCardHolderName(cardHolderName);
		if(ccv.equalsIgnoreCase("true")){
			postPayment.setAuthorizationCode(cardCcvCode);
		}else{
			postPayment.setRequestNodeValueByXPath("/Envelope/Body/postCardPayment/pmtInfo/cardAuthorizationInfoTO/authorizationCode", "fx:RemoveNode");
		}
		postPayment.setPaymentType(cardPaymentType.replace(" ", ""));
		postPayment.setPaymentMethod(cardPaymentMethod);
		postPayment.setRetreivalReferenceNumber();
		postPayment.sendRequest();
		conversationIDs.put("creditPayment", postPayment.getConversationID());
		Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");
		
		setBalanceDue(postPayment.getFolioBalance());
		setOriginalTransactionId(postPayment.getOriginalTransactionId());
		setLatestTransactionId(postPayment.getTransactionId());
		setRRNKey(postPayment.getRRNKey());
		setRRNNumber(postPayment.getRRNNumber());
		setPaymentId(postPayment.getPaymentId());		
	}
	
	
	public void makeFirstNightDeposit(){
		RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
		retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
		retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
		retrieveBalance.setLocationId(getLocationId());
		retrieveBalance.sendRequest();
		
		Assert.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(retrieveBalance.getPaymentRequired());
		setDepositDue(retrieveBalance.getDepositRequired());
		setFolioId(retrieveBalance.getFolioId());
		
		PostCardPayment postPayment = new PostCardPayment(getEnvironment(),"Visa-CreditCard");
		postPayment.setAmount(getDepositDue());
		postPayment.setFolioId(getFolioId());
		postPayment.setBookingReference(ServiceConstants.BookingSource.DREAMS_TP, getTravelPlanId());
		postPayment.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TC, getTravelComponentId());
		postPayment.setLocationId(getLocationId());
		postPayment.setPartyId(getPartyId());
		postPayment.setPrimaryLastname(getPrimaryGuestLastName());
		postPayment.setTravelPlanId(getTravelPlanId());
		postPayment.setTravelPlanSegmentId(getTravelPlanSegmentId());
		postPayment.setRetreivalReferenceNumber();
		postPayment.sendRequest();
		Assert.assertEquals(postPayment.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(postPayment.getFolioBalance());
		setOriginalTransactionId(postPayment.getOriginalTransactionId());
		setLatestTransactionId(postPayment.getTransactionId());
		setRRNKey(postPayment.getRRNKey());
		setRRNNumber(postPayment.getRRNNumber());
		setPaymentId(postPayment.getPaymentId());
		
	}
	
	
	public void addRoom(){
		
		Add addRoom = new Add(getEnvironment(), "Main");

		addRoom.setTravelPlanId(getTravelPlanId());
		addRoom.setTravelPlanSegmentId(getTravelPlanSegmentId());
		addRoom.setPackageCode(getPackageCode());
		addRoom.setResortCode(getResortCode());
		addRoom.setRoomTypeCode(getRoomTypeCode());
		addRoom.setBookingDate(getArrivalDaysOut());
		addRoom.setArrivalDate(getArrivalDaysOut());
		addRoom.setDeptDate(getDepartureDaysOut());
		addRoom.setLocationID(getLocationId());
		addRoom.sendRequest();
		Assert.assertEquals(addRoom.getResponseStatusCode(), "200","Response was not 200");
		
		setTravelComponentGroupingId(addRoom.getTravelComponentGroupingId());
		setTravelComponentId(addRoom.getTravelComponentId());
		setGuestId(addRoom.getGuestId());
		setPartyId(addRoom.getPartyId());
		
	}
	
	public void addRoomTwoAdults(String[] guestIdss){
		
		Add addRoom = new Add(getEnvironment(), "AddAccommTwoAdults");

		addRoom.setTravelPlanId(getTravelPlanId());
		addRoom.setTravelPlanSegmentId(getTravelPlanSegmentId());
		addRoom.setPackageCode(getPackageCode());
		addRoom.setResortCode(getResortCode());
		addRoom.setRoomTypeCode(getRoomTypeCode());
		addRoom.setBookingDate(getArrivalDaysOut());
		addRoom.setArrivalDate(getArrivalDaysOut());
		addRoom.setDeptDate(getDepartureDaysOut());
		addRoom.setLocationID(getLocationId());
		addRoom.setRequestNodeValueByXPath("/Envelope/Body/add/request/roomDetail/roomReservationDetail/guestReferenceDetails[1]/guest/guestId", guestIdss[0]);
		addRoom.setRequestNodeValueByXPath("/Envelope/Body/add/request/roomDetail/roomReservationDetail/guestReferenceDetails[2]/guest/guestId", guestIdss[1]);
		addRoom.sendRequest();
		Assert.assertEquals(addRoom.getResponseStatusCode(), "200","Response was not 200");
		
		setTravelComponentGroupingId(addRoom.getTravelComponentGroupingId());
		setTravelComponentId(addRoom.getTravelComponentId());
		setGuestId(addRoom.getGuestId());
		setPartyId(addRoom.getPartyId());
		
		guestIds = new String[2];
		guestIds[0] = addRoom.getResponseNodeValueByXPath("/Envelope/Body/addResponse/addResponse/roomDetails/roomReservationDetail/guestReferenceDetails[1]/guest/guestId");
		guestIds[1] = addRoom.getResponseNodeValueByXPath("/Envelope/Body/addResponse/addResponse/roomDetails/roomReservationDetail/guestReferenceDetails[2]/guest/guestId");
	}
	
	public void onlineCheckin(String scenario){
		UpdatePreArrivalInformation olci = new UpdatePreArrivalInformation(getEnvironment(), scenario);
		olci.setTravelPlanSegment(getTravelPlanSegmentId());
		olci.sendRequest();
		Assert.assertEquals(olci.getSuccessStatus(), "true");
	}
	

	public void encodePrimaryGuest(){
		RetrieveDetails details = new RetrieveDetails(environment, "Main");
		details.setTPID(getTravelPlanId());
		
		details.sendRequest();
		Assert.assertEquals(details.getResponseStatusCode(), "200");
		System.out.println(details.getResponse());
		setKttwId(details.getKttwId());
		
		gaKttwId = details.getKttwId();
		gaPartyId = details.getPartyId();
		gaTravelPlanId = details.getTravelplanId();
			
		GrantNewAccess newAccess = new GrantNewAccess(environment, "Main");

		setEncoderId(EncoderIDs.getEncoderId(environment, facilityId));
		
		newAccess.setTcgId(getTravelComponentGroupingId());
		newAccess.setGuestId(getGuestId());
		newAccess.setTpId(getTravelPlanId());
		newAccess.setLocationId(getLocationId());
		newAccess.setEncoderId(getEncoderId());
		newAccess.setKttwId(getKttwId());
		newAccess.setFacilityId(getFacilityId());
		newAccess.setResourceId(getResourceId());
		newAccess.setRoomId(getRoomNumber());
		newAccess.setStartDate(DateTimeConversion.ConvertToDateYYYYMMDD(getArrivalDaysOut()) + "T00:00:00");
		newAccess.setEndDate(DateTimeConversion.ConvertToDateYYYYMMDD(getDepartureDaysOut()) + "T00:00:00");
		
		newAccess.setEnvironmentServiceURLSV("GrantNewAccessSV", environment);	//DJS - New function added specifically for Service Virtualization
		
		newAccess.sendRequest();
		Assert.assertEquals(newAccess.getResponseStatusCode(), "200");
		
		if(newAccess.getEncodingStatus().equalsIgnoreCase("FAILEDTOENCODE")){
			newAccess.getEncodingError();
			Reporter.log("An error occurred when encoding. The error message is:\n"+newAccess.getEncodingError()+"\n", true);
		}
		Assert.assertEquals(newAccess.getEncodingStatus().toLowerCase(), "encoded", "The encoding status was found to be ["+newAccess.getEncodingStatus()+"] when [ENCODED] was expected.\n The response is as follows:\n\n"+newAccess.getResponse());
		
		RetrieveDetails retrieveDetails = new RetrieveDetails(environment,"Main");
		retrieveDetails.setTPID(getTravelPlanId());
		retrieveDetails.sendRequest();
		Assert.assertEquals(retrieveDetails.getResponseStatusCode(), "200");
		
		// DJS - since we have used Service Virtualization to stub out the encoder, this information is not needed.
		/*setAccessEncodingStatus(retrieveDetails.getEncodingStatus());
		setRfidMediaId(retrieveDetails.getRfid());
		setManufacturerUID(retrieveDetails.getManufacturerUid());*/
	}
	
	public void addKttwTickets(String scenario){
		BookKeyToTheWorldTickets kttw = new BookKeyToTheWorldTickets(getEnvironment(), scenario);
		kttw.setGuestId(getGuestId());
		kttw.setLocationId(getLocationId());
		kttw.setTravelComponentGroupingId(getTravelComponentGroupingId());
		kttw.setTravelSegmentId(getTravelPlanSegmentId());
		kttw.sendRequest();
		Assert.assertEquals(kttw.getResponseStatusCode(), "200","Response was not 200");
		conversationIDs.put("kttw", kttw.getConversationID());
		
		RetrieveKeyToTheWorldTickets retrieve = new RetrieveKeyToTheWorldTickets(getEnvironment(), scenario);
		retrieve.setTravelPlanSegmentId(getTravelPlanSegmentId());
		retrieve.sendRequest();
		Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");
		
		//DJS - Removed the lines below because causes null pointer. Since this is only adding
		//      one ticket, set up the array so that it can be used appropriately for Cancel ticket.
		
//djs		admissionComponentId[0] = retrieve.getAdmissionComponentId();
//djs		baseAdmissionProductId[0] = retrieve.getBaseAdmissionProductId();
		admissionComponentId = new String[1];
		admissionComponentId[0] = retrieve.getAdmissionComponentId("1");
	}
	
	public void addKttwTicketsTwoAdults(String scenario){
		BookKeyToTheWorldTickets kttw = new BookKeyToTheWorldTickets(getEnvironment(), scenario);
		kttw.setLocationId(getLocationId());
		kttw.setTravelComponentGroupingId(getTravelComponentGroupingId());
		kttw.setTravelSegmentId(getTravelPlanSegmentId());
		
		for(int guestCount = 0; guestCount < guestIds.length; guestCount++){
			kttw.setGuestId(guestIds[guestCount]);
			kttw.sendRequest();
			Assert.assertEquals(kttw.getResponseStatusCode(), "200","Response was not 200");
		}
		
		RetrieveKeyToTheWorldTickets retrieve = new RetrieveKeyToTheWorldTickets(getEnvironment(), scenario);
		retrieve.setTravelPlanSegmentId(getTravelPlanSegmentId());
		retrieve.sendRequest();
		Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");
		admissionComponentId = new String[2];
		baseAdmissionProductId = new String[2];
		admissionComponentId[0] = retrieve.getAdmissionComponentId("1");
		baseAdmissionProductId[0] = retrieve.getBaseAdmissionProductId("1");
		admissionComponentId[1] = retrieve.getAdmissionComponentId("2");
		baseAdmissionProductId[1] = retrieve.getBaseAdmissionProductId("2");
	}
	
	public void addKttwTicketsTwoAdultsExistingTps(String scenario, String[] newGuestIndices){
		BookKeyToTheWorldTickets kttw = new BookKeyToTheWorldTickets(getEnvironment(), scenario);
		kttw.setLocationId(getLocationId());
		kttw.setTravelComponentGroupingId(getTravelComponentGroupingId());
		kttw.setTravelSegmentId(getTravelPlanSegmentId());
		
		for(int guestCount = 0; guestCount < guestIds.length; guestCount++){
			kttw.setGuestId(guestIds[guestCount]);
			kttw.sendRequest();
			Assert.assertEquals(kttw.getResponseStatusCode(), "200","Response was not 200");
		}
		
		RetrieveKeyToTheWorldTickets retrieve = new RetrieveKeyToTheWorldTickets(getEnvironment(), scenario);
		retrieve.setTravelPlanSegmentId(getTravelPlanSegmentId());
		retrieve.sendRequest();
		System.out.println(retrieve.getResponse());
		Assert.assertEquals(retrieve.getResponseStatusCode(), "200","Response was not 200");
		admissionComponentId = new String[newGuestIndices.length];
		baseAdmissionProductId = new String[newGuestIndices.length];
		for(int guestCount = 0; guestCount < guestIds.length; guestCount++){
			admissionComponentId[guestCount] = retrieve.getAdmissionComponentId(newGuestIndices[guestCount]);
			baseAdmissionProductId[guestCount] = retrieve.getBaseAdmissionProductId(newGuestIndices[guestCount]);
		}
	}
	
	public void cancelAccommodation(){
		Cancel cancel = new Cancel(getEnvironment(), "Main");
		cancel.setCancelDate(DateTimeConversion.ConvertToDateYYYYMMDD("0"));
		cancel.setTravelComponentGroupingId(getTravelComponentGroupingId());
		cancel.sendRequest();
		Assert.assertEquals(cancel.getResponseStatusCode(), "200", "Response was not 200");
	}
	
	public void cancelTickets(String scenario){
		CancelKeyToTheWorldTickets cancel = new CancelKeyToTheWorldTickets(getEnvironment(), scenario);
		
		for(int guestCount = 0; guestCount < admissionComponentId.length; guestCount++){
			cancel.setAdmissionComponecntId(admissionComponentId[guestCount]);
			cancel.setTravelComponentGroupingId(getTravelComponentGroupingId());
			cancel.setTravelPlanId(getTravelPlanId());
			cancel.setTravelPlanSegmentId(getTravelPlanSegmentId());
			cancel.sendRequest();
			Assert.assertEquals(cancel.getResponseStatusCode(), "200", "Response was not 200");	
		}
	}
	
	public void createSettlementMethod(){
		CreateSettlementMethod settlement = new CreateSettlementMethod(environment, "Main");
		settlement.setFolioId(getFolioId());
		settlement.sendRequest();
		Assert.assertEquals(settlement.getResponseStatusCode(), "200",settlement.getResponse());
	}
	
	public void createSettlementMethod(String scenario){
		datatable.setVirtualtablePath(Datatable.PAYMENTUI_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("PaymentUI");
		datatable.setVirtualtableScenario(scenario);

		String paymentMethod = null;
		String status = null;
		String delay = null;
		String expressCheckout = null;
		
		/*
		 * In the event that multiple settlement methods are to be applied, then
		 * the best way to ensure that the same card is not used (possibly
		 * causing an error) is to swap card types. All card types will use
		 * Master Cards as secondary settlement methods. When a master card is
		 * first used, then a visa will serve as the alternate card type.
		 * 
		 * First, test whether the card number is null, indicating the first settlement method is to be processed.
		 * Next, if it is not null, then determine the current card type and adjust accordingly.
		 */
		if(cardNumber.isEmpty()){
			paymentMethod = datatable.getDataParameter("PaymentMethod");
			status = datatable.getDataParameter("Status");
			delay = datatable.getDataParameter("Delay");
			expressCheckout = datatable.getDataParameter("ExpressCheckout");
		}else{
			if(!cardPaymentMethod.equalsIgnoreCase("Master Card")){
				paymentMethod = "Master Card";
				delay = "15";
				status = "APPROVED";
				expressCheckout = datatable.getDataParameter("ExpressCheckout");
			}else{
				paymentMethod = "Visa";
				delay = "0";
				status = "APPROVED";
				expressCheckout = datatable.getDataParameter("ExpressCheckout");
			}
		}
		
		GenerateCard card = new GenerateCard();
		Map<String, String> cardInfo = null;
		System.out.println();
		try {
			cardInfo = card.getCardInfo(status, delay, card.getCradTypeEnum(paymentMethod));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardPaymentType = datatable.getDataParameter("PaymentType");
		cardPaymentMethod = datatable.getDataParameter("PaymentMethod");
		cardNumber = cardInfo.get("AccountNumber").replace("-", "");
		cardExpirationMonth = cardInfo.get("ExpMonth");
		cardExpirationYear = cardInfo.get("ExpYear");
		cardExpirationDate = cardExpirationMonth + "/" + cardExpirationYear;
		cardHolderName = cardInfo.get("NameOnCard");
		cardAddressLine1 = cardInfo.get("BillingStreet");
		cardAddressLine2 = cardInfo.get("BillingStreet2");
		cardCity = cardInfo.get("BillingCity");
		cardState = cardInfo.get("BillingState");
		cardPostalCode = cardInfo.get("BillingZip");
		cardCcvCode = cardInfo.get("CVV2");
		
		RetrieveFolioBalanceDue retrieveBalance = new RetrieveFolioBalanceDue(getEnvironment(), "UI booking");
		retrieveBalance.setExternalReference(ServiceConstants.FolioExternalReference.DREAMS_TP, getTravelPlanId());
		retrieveBalance.setFolioType(ServiceConstants.FolioType.INDIVIDUAL);
		retrieveBalance.setLocationId(getLocationId());
		retrieveBalance.sendRequest();
		
		Assert.assertEquals(retrieveBalance.getResponseStatusCode(), "200","Response was not 200");	
		
		setBalanceDue(retrieveBalance.getPaymentRequired());
		setDepositDue(retrieveBalance.getDepositRequired());
		setFolioId(retrieveBalance.getFolioId());
		
		CreateSettlementMethod settlement = new CreateSettlementMethod(environment, "Main");
		settlement.setFolioId(getFolioId());
		if(expressCheckout.equalsIgnoreCase("true"))settlement.setExpressCheckout("true");
		settlement.setSettlementMethod(cardPaymentMethod);
		settlement.setCardNumber(cardNumber);
		settlement.setCardName(cardHolderName);
		settlement.setCardAddressLine1(cardAddressLine1);
		settlement.setRetreivalReferenceNumber(cardNumber,cardExpirationMonth+"/"+cardExpirationYear);
		if(cardAddressLine2.isEmpty() || cardAddressLine2 == null || cardAddressLine2.equalsIgnoreCase("null")){
			settlement.removeAddressLine2();
		}else{
			settlement.setCardAddressLine2(cardAddressLine2);
		}
		settlement.setCardPostalCode(cardPostalCode);
		settlement.setCardState(cardState);
		settlement.setCardExpirationMonth(cardExpirationMonth);
		settlement.setCardExpirationYear(cardExpirationYear);
		
		settlement.sendRequest();
		Assert.assertEquals(settlement.getResponseStatusCode(), "200","Response was not 200");
	}
	

	public String[] getAdmissionComponentId(){
		return admissionComponentId;
	}
	
	public String[] getBaseAdmissionProductId(){
		return baseAdmissionProductId;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	

	public void setPhoneNumber(String number){
		this.phoneNumber = number;
	}
	
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email= email;
	}
	
	public Map<String, String> getConversationIds(){
		return conversationIDs;
	}
	
	public String getArrivalDate(){
		return arrivalDate;
	}
	
	public String getDepartureDate(){
		return departureDate;
	}
	
	public void createChargeAccount(String kttw, String guestId, int expectedNumberOfGuests){
		chargeAcctIds = new String[expectedNumberOfGuests];
		kttwIds = new String[expectedNumberOfGuests];
		
		TestReporter.logStep("Create Charge Account for Guest [" + guestId+"]");
		Create createChargeAccount = new Create(environment, "Main");
		createChargeAccount.setFacilityId(facilityId);
		createChargeAccount.setKttwNumber(kttw);
		createChargeAccount.setPaymentMethodEndDate(getDepartureDate());
		createChargeAccount.setPaymentMethodStartDate(getArrivalDate());
		createChargeAccount.setReferenceNumber(guestId);
		createChargeAccount.setReservationTransactionGuestId(guestId);
		createChargeAccount.setStartDate(getArrivalDate());
		createChargeAccount.sendRequest();
		TestReporter.assertEquals(createChargeAccount.getResponseStatusCode(), "200", "The response code was not 200 as expected. Actual response was [" +createChargeAccount.getResponseStatusCode()+ "]");
		
		TestReporter.logStep("Retrieve Charge Account ID for Guest [" + guestId+"]");
		RetrieveChargeAccountID retChrgActID = new RetrieveChargeAccountID();
		String[][] results = retChrgActID.getChargeIDs(environment, getTravelPlanId(), expectedNumberOfGuests);
		
		//Grab all charge account ids
		for(int ca = 0; ca < expectedNumberOfGuests; ca++){
			chargeAcctIds[ca] = results[ca][0];	
			kttwIds[ca] = results[ca][1];
			TestReporter.log("Charge Account ID: " + chargeAcctIds[ca]);
			TestReporter.log("Experience Card Number: " + kttwIds[ca]);
		}
	}
	
	public String[] getChargeAccountIds(){
		return chargeAcctIds;
	}
	
	public String[] getKttwIds(){
		return kttwIds;
	}
	
	public void updateChargeAccountPin(String chargeAccount){
		TestReporter.logStep("Update PIN for Charge Account ["+chargeAccount+"]");
		UpdatePin update = new UpdatePin(environment, "Main");
		update.setChargeAccountId(chargeAccount);
		update.setPinNumber(Randomness.randomNumber(4));
		pinNumber = update.getPinNumber();
		update.sendRequest();
		TestReporter.assertEquals(update.getResponseStatusCode(), "200", "The response code was not 200 as expected. Actual response was [" +update.getResponseStatusCode()+ "]");
	}
	
	public String getChargeAccountPinNumber(){
		return pinNumber;
	}
	
	public void updateChargeAccountPinForChargeAccounts(){
		pinNumbers = new String[chargeAcctIds.length];
		UpdatePin update = new UpdatePin(environment, "Main");
		for(int ca = 0; ca < chargeAcctIds.length; ca++){
			TestReporter.log("Update PIN for Charge Account ["+chargeAcctIds[ca]+"]");
			update.setChargeAccountId(chargeAcctIds[ca]);
			update.setPinNumber(Randomness.randomNumber(4));
			pinNumbers[ca] = update.getPinNumber();
			try{
				System.out.println(update.getRequest());
				update.sendRequest();
			}catch(Exception e){
				System.out.println(update.getResponse());
				System.out.println();
				System.out.println(update.getResponseStatusCode());
			}
			TestReporter.assertEquals(update.getResponseStatusCode(), "200", "The response code was not 200 as expected. Actual response was [" +update.getResponseStatusCode()+ "]");
		}
	}
	
	public String[] getChargeAccountPinNumbers(){
		return pinNumbers;
	}
	
	public void validateChargeAccountPinNumberIsValid(String pin, String chargeAccount){
		TestReporter.logStep("Validate PIN ["+pin+"]for Charge Account ["+chargeAccount+"]");
		ValidatePin validate = new ValidatePin(environment, "Main");
		validate.setChargeAccountId(chargeAccount);
		validate.setPinNumber(pin);
		validate.sendRequest();
		TestReporter.assertEquals(validate.getResponseStatusCode(), "200", "The response code was not 200 as expected. Actual response was [" +validate.getResponseStatusCode()+ "]");
		TestReporter.assertEquals(validate.getValidationResponse(), "true", "The validation was not true.  The actual value is ["+validate.getValidationResponse()+"].");
	}
	
	public void validateAllChargeAccountPinNumbersAreValid(){
		ValidatePin validate = new ValidatePin(environment, "Main");
		for(int ca = 0; ca < pinNumbers.length; ca++){
			TestReporter.log("Validate PIN ["+pinNumbers[ca]+"]for Charge Account ["+chargeAcctIds[ca]+"]");
			validate.setChargeAccountId(chargeAcctIds[ca]);
			validate.setPinNumber(pinNumbers[ca]);
			validate.sendRequest();
			TestReporter.assertEquals(validate.getResponseStatusCode(), "200", "The response code was not 200 as expected. Actual response was [" +validate.getResponseStatusCode()+ "]");
			TestReporter.assertEquals(validate.getValidationResponse(), "true", "The validation was not true.  The actual value is ["+validate.getValidationResponse()+"].");	
		}
	}
	
	public void setChargeAccounts(String[] ca){
		chargeAcctIds = ca;
	}
	
	public String[] getGuestIds(){
		return guestIds;
	}
	
	public void setTerminalID(String value){
		terminalID = value;
	}
	
	public String getTerminalID(){
		return terminalID;
	}
	
	public String[] getPaymentIds(){
		return paymentIds;
	}
	

	public void verifyTerminalId(){
		RetrieveResponsiblePartyDetails details = new RetrieveResponsiblePartyDetails(environment, "Main");
		details.setSourceAccountingCenterId(getSourceAcccountingCenterId());
		details.setTravelPlanId(getTravelPlanId());
		details.sendRequest();
		TestReporter.assertEquals(details.getResponseStatusCode(), "200", "The response status code was not 200");
		TestReporter.assertNotEquals(details.getTerminalId().trim(), "0001", "The terminal ID [" + details.getTerminalId().trim() + "] was found to be 0001");
	}
	
	public void verifyTerminalIds(int payments){
		RetrieveResponsiblePartyDetails details = new RetrieveResponsiblePartyDetails(environment, "Main");
		details.setSourceAccountingCenterId(getSourceAcccountingCenterId());
		details.setTravelPlanId(getTravelPlanId());
		details.sendRequest();
		TestReporter.assertEquals(details.getResponseStatusCode(), "200", "The response status code was not 200");
		
		for(int payment = 1; payment < payments + 1; payment++){
			try{
				TestReporter.assertNotEquals(details.getTerminalId(payment), "0001", "The terminal ID [" + details.getTerminalId(payment) + "] was found to be 0001");
			}catch(RuntimeException e){
				
			}
		}
	}

//	public void retrieveComments(){
//		RetrieveComments retrieveComments =new RetrieveComments("Sleepy", "retrieveComments");
//		retrieveComments.setparentId(getTravelComponentId());
//		retrieveComments.sendRequest();
//		TestReporter.assertEquals(retrieveComments.getResponseStatusCode(), "200", "The response code was not 200");
//	}
	public void reconcile(){

		Reconcile reconcile =new Reconcile("Sleepy", "reconcile");
		reconcile.setTravelPlanId(getTravelPlanId());
		reconcile.sendRequest();
		TestReporter.assertEquals(reconcile.getResponseStatusCode(), "200", "The response code was not 200");
	}
	
	public void SearchResortReservationsByGuest(){
		SearchResortReservationsByGuest searchRevByGuest = new SearchResortReservationsByGuest("Sleepy", "searchResortReservationsByGuest");
		searchRevByGuest.setreservationNum(getTravelPlanSegmentId());
		searchRevByGuest.sendRequest();
		TestReporter.assertEquals(searchRevByGuest.getResponseStatusCode(), "200", "The response code was not 200");
		
		
	}
	
	public void CreateComments(){
		
		CreateComments createComment = new CreateComments("Sleepy", "createcomments");
		createComment.setparentIds(getTravelComponentId());
		createComment.setcommentText(Randomness.randomString(5));
		createComment.settcId(getTravelComponentId());
		createComment.settpId(getTravelPlanId());
		createComment.settpsId(getTravelPlanSegmentId());
		createComment.sendRequest();
		TestReporter.assertEquals(createComment.getResponseStatusCode(), "200", "The response code was not 200");
		
	}
	
	public void UpdateComments(){
		
	UpdateComments UpdateCommets = new UpdateComments("Sleepy", "updatecomments");
	UpdateCommets.setparentIds(getTravelComponentId());
	UpdateCommets.setcommentText(Randomness.randomString(5));
	UpdateCommets.settcId(getTravelComponentId());
	UpdateCommets.settpId(getTravelPlanId());
	UpdateCommets.settpsId(getTravelPlanSegmentId());
	UpdateCommets.sendRequest();
	TestReporter.assertEquals(UpdateCommets.getResponseStatusCode(), "200", "The response code was not 200");
		
	}
	
   
	public void RemoveRateOverride(){
		RemoveRateOverride removeRateOverride = new RemoveRateOverride("Sleepy", "removeRateOverride");
		removeRateOverride.settravelPlanSegmentId(getTravelPlanSegmentId());
		removeRateOverride.setaccomComponentId(getTravelComponentId());
		removeRateOverride.sendRequest();
		TestReporter.assertEquals(removeRateOverride.getResponseStatusCode(), "200", "The response code was not 200");
	}
	
	public void setPrimaryGuestAddress(String primaryGuestAddress){
		this.primaryGuestAddress1 = primaryGuestAddress;
	}
	
	public String getPrimaryGuestAddress(){
		return primaryGuestAddress1;
	}
	
	public void setPrimaryGuestCity(String primaryGuestCity){
		this.primaryGuestCity = primaryGuestCity;
	}
	
	public String getPrimaryGuestCity(){
		return primaryGuestCity;
	}
	
	public void setPrimaryGuestState(String primaryGuestState){
		this.primaryGuestState = primaryGuestState;
	}
	
	public String getPrimaryGuestState(){
		return primaryGuestState;
	}
	
	public void setPrimaryGuestPostalCode(String primaryGuestPostalCode){
		this.primaryGuestPostalCode = primaryGuestPostalCode;
	}
	
	public String getPrimaryGuestPostalCode(){
		return primaryGuestPostalCode;
	}
	
	public String getPrimaryGuestEmail() {
		return email;
	}

	
	public void setPrimaryGuestEmail(String primaryGuestEmail) {
		this.email = primaryGuestEmail;
	}
	
	public void share(){
		Share share = new Share("Sleepy","share");
		share.setTravelComponentGroupingId(getTravelComponentGroupingId());
	    share.sendRequest();
		TestReporter.assertEquals(share.getResponseStatusCode(), "200", "The response code was not 200");
	}

	public String getTravelComponentGroupingId1() {
        return travelComponentGroupingId;
    }
//    public void updateGuaranteeStatus(){	
//	UpdateGuaranteeStatus  updateGuaranteeStatus= new UpdateGuaranteeStatus("Sleepy","updateGuaranteeStatus");
//	updateGuaranteeStatus.setTravelComponentGroupingId(getTravelComponentGroupingId());
//	updateGuaranteeStatus.sendRequest();
//   TestReporter.assertEquals(updateGuaranteeStatus.getResponseStatusCode(), "200", "The response code was not 200");
//    }
//     public void retrieveTravelPlanMediaCustomization(){ 
//     RetrieveTravelPlanMediaCustomization  retrieveTravelPlanMediaCustomization = new RetrieveTravelPlanMediaCustomization("Sleepy","retrieveTravelPlanMediaCustomization");
//     retrieveTravelPlanMediaCustomization.sendRequest();
//	 TestReporter.assertEquals(retrieveTravelPlanMediaCustomization.getResponseStatusCode(), "200", "The response code was not 200");
//	 }
     public void verifyTerminalIds() {
		// TODO Auto-generated method stub
     }
     public void setPrimaryGuestOdsId(String id){
    	 primaryGuestOdsId = id;
     }
     
     public void setPrimaryGuestPartyId(String id){
    	 primaryGuestPartyId = id;
     }
 }


