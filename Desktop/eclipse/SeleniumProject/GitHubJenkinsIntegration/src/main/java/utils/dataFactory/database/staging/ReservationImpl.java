package utils.dataFactory.database.staging;

import java.util.Map;

public class ReservationImpl extends ReservationDecorator implements Reservation {

	
	@Override
	public String getEnvironment() {
		return getEnvironment();	
	}

	@Override
	public String getPrimaryGuestFirstName() {
		return getPrimaryGuestFirstName();
	}

	@Override
	public void setPrimaryGuestFirstName(String primaryGuestFirstName) {		
		setPrimaryGuestFirstName(primaryGuestFirstName);
	}

	@Override
	public String getPrimaryGuestLastName() {
		return getPrimaryGuestLastName();
	}

	@Override
	public void setPrimaryGuestLastName(String primaryGuestLastName) {
		setPrimaryGuestLastName(primaryGuestLastName); 
	}

	@Override
	public String getNumberOfAdults() {
		return getNumberOfAdults();
	}

	@Override
	public void setNumberOfAdults(int numberOfAdults) {
		setNumberOfAdults(numberOfAdults);
	}

	@Override
	public void setNumberOfAdults(String numberOfAdults) {
		setNumberOfAdults(numberOfAdults); 
	}

	@Override
	public String getNumberOfRooms() {
		return getNumberOfRooms();
	}

	@Override
	public void setNumberOfRooms(String numberOfRooms) {
		setNumberOfRooms(numberOfRooms);
	}

	@Override
	public void setNumberOfRooms(int numberOfRooms) {
		setNumberOfRooms(numberOfRooms);
	}

	@Override
	public String getArrivalDaysOut() {
		return getArrivalDaysOut();
	}

	@Override
	public void setArrivalDaysOut(String arrivalDaysOut) {
		setArrivalDaysOut(arrivalDaysOut);
	}

	@Override
	public void setArrivalDaysOut(int arrivalDaysOut) {
		setArrivalDaysOut(arrivalDaysOut);
	}

	@Override
	public String getDepartureDaysOut() {
		return getDepartureDaysOut();
	}

	@Override
	public void setDepartureDaysOut(String departureDaysOut) {
		setDepartureDaysOut(departureDaysOut);
	}

	@Override
	public void setDepartureDaysOut(int departureDaysOut) {
		setDepartureDaysOut(departureDaysOut);
	}

	@Override
	public String getPackageCode() {
		return getPackageCode();
	}

	@Override
	public void setPackageCode(String packageCode) {
		setPackageCode(packageCode);
	}

	@Override
	public String getResortCode() {
		return getResortCode();
	}

	@Override
	public String getRoomTypeCode() {
		return getRoomTypeCode();
	}
	
	@Override
	public void setRoomTypeCode(String roomTypeCode) {
		setRoomTypeCode(roomTypeCode);
	}

	@Override
	public String getLocationId() {
		return getLocationId();
	}
	

	@Override
	public String getFacilityId() {
		return getFacilityId();
	}
	
	@Override
	public String getSourceAcccountingCenterId(){
		return getSourceAcccountingCenterId();
	}

	@Override
	public String getTravelPlanId() {
		return getTravelPlanId();
	}

	@Override
	public String getTravelPlanSegmentId() {
		return getTravelPlanSegmentId();
	}

	@Override
	public String getTravelComponentGroupingId() {
		return getTravelComponentGroupingId();
	}

	@Override
	public String getTravelComponentId() {
		return getTravelComponentId();
	}

	@Override
	public String getPartyId() {
		return getPartyId();
	}

	@Override
	public String getGuestId() {
		return getGuestId();
	}
	
	@Override
	public String[] getAllGuestIds(){
		return getAllGuestIds();
	}
	
	@Override
	public String[] getAllPartyIds(){
		return getAllPartyIds();
	}
	
	@Override
	public String getRoomNumber(){
		return getRoomNumber();
	}
	@Override
	public String getResourceId(){
		return getResourceId();
	}
	@Override
	public String getAssignmentOwnerNumber(){
		return getAssignmentOwnerNumber();
	}
	
	public void quickBook() {
		// Nothing to do here All Booking classes will override this
	}
	
	@Override
	public void assignRoomToReservation(){
		assignRoomToReservation();
	}
	
	@Override
	public void assignNotReadyRoomToReservation(){
		assignNotReadyRoomToReservation();
	}
	
	@Override
	public void checkInReservation(){
		checkInReservation();
	}
	
	@Override
	public void makeFullPayment(){
		makeFullPayment();
	}
	
	@Override
	public void makeCardPaymentNegative(String scenario){
		makeCardPaymentNegative(scenario);
	}
	
	@Override
	public void makeCardPayment(String scenario){
		makeCardPayment(scenario);
	}
	
	@Override
	public void makeCardPaymentCardOnFile(String scenario){
		makeCardPaymentCardOnFile(scenario);
	}
	
	@Override
	public void applyCreditToExistingCard(String scenario, String creditAmount){
		applyCreditToExistingCard(scenario, creditAmount);
	}
	
	@Override
	public void makeCheckPayment(){
		makeCheckPayment();
	}
	
	@Override
	public void makeSplitCardPayment(String scenario){
		makeSplitCardPayment(scenario);
	}
	
	@Override
	public void makeFirstNightDeposit(){
		makeFullPayment();
	}
	
	@Override
	public void bookDVCCash(){
		bookDVCCash();
	}
	
	@Override
	public void bookRoomOnly(){
		bookRoomOnly();
	}
	
	@Override
	public void bookRoomOnlyTwoAdults(){
		bookRoomOnlyTwoAdults();
	}
	
	@Override
	public void bookRoomOnlyTwoAdultsTwoChildren(){
		bookRoomOnlyTwoAdultsTwoChildren();
	}
	
	@Override
	public void bookWholesaleTwoAdultsNoTickets(){
		bookWholesaleTwoAdultsNoTickets();
	}
	
	@Override
	public void addRoom(){
		addRoom();
	}
	
	@Override

	public void addRoomTwoAdults(String[] guestIds){
		addRoomTwoAdults(guestIds);
	}
	
	@Override
	public void bookGroupBooking(){
		bookGroupBooking();
	}
	
	@Override
	public void bookDVCMemberPoints(){
		bookDVCMemberPoints();
	}

	@Override
	public String getFolioId(){
		return getFolioId();
	}

	@Override
	public String getDepositDue(){
		return getDepositDue();
	}	

	@Override
	public String getBalanceDue(){
		return getBalanceDue();
	}
	
	@Override
	public String getOriginalTransactionId() {
		return getOriginalTransactionId();
	}

	@Override
	public String getLatestTransactionId() {
		return getLatestTransactionId();
	}

	@Override
	public String getPaymentId() {
		return getPaymentId();
	}

	@Override
	public String getRRNNumber() {
		return getRRNNumber(); 
	}

	@Override
	public String getRRNKey() {
		return getRRNKey();
	}
	
	@Override
	public void setDVCMemberID(String DVCMemberID) {
		setDVCMemberID(DVCMemberID);
	}
	
	@Override
	public void setDVCresortCode(String DVCresortCode) {
		setDVCresortCode(DVCresortCode);
	}
	
	@Override
	public String getDVCMemberID() {
		return getDVCMemberID();
	}
	
	@Override
	public String getDVCresortCode() {
		return getDVCresortCode();
	}
	
	@Override
	public void onlineCheckin(String scenario){
		onlineCheckin(scenario);
	}
	
	@Override
	public void encodePrimaryGuest(){
		encodePrimaryGuest();
	}
	@Override
	public void addKttwTickets(String scenario){
		addKttwTickets(scenario);
	}
	
	@Override
	public String getEncoderId(){
		return getEncoderId();
	}
	
	@Override
	public String getKttwId(){
		return getKttwId();
	}
	
	@Override
	public String getManufacturerUID(){
		return getManufacturerUID();
	}
	
	@Override
	public String getRfidMediaId(){
		return getRfidMediaId();
	}
	
	@Override
	public String getAccessEncodingStatus(){
		return getAccessEncodingStatus();
	}
	
	@Override
	public String getGuestRoomIndicator(){
		return getGuestRoomIndicator();
	}
	
	@Override
	public String getMediaAccessId(){
		return getMediaAccessId();
	}
	
	@Override
	public void addKttwTicketsTwoAdults(String scenario){
		addKttwTicketsTwoAdults(scenario);
	}
	
	@Override
	public void addKttwTicketsTwoAdultsExistingTps(String scenario, String[] newGuestIndices){
		addKttwTicketsTwoAdultsExistingTps(scenario, newGuestIndices);
	}
	
	@Override
	public void cancelAccommodation(){
		cancelAccommodation();
	}
	
	@Override
	public String[] getAdmissionComponentId(){
		return getAdmissionComponentId();
	}
	
	@Override
	public String[] getBaseAdmissionProductId(){
		return getBaseAdmissionProductId();
	}
	
	@Override
	public void cancelTickets(String scenario){
		cancelTickets(scenario);
	}
	
	@Override
	public void createSettlementMethod(){
		createSettlementMethod();
	}
	
	@Override
	public void createSettlementMethod(String scenario){
		createSettlementMethod(scenario);
	}
	
	@Override
	public void bookWDTCTwoAdultsTwoChildren(){
		bookWDTCTwoAdultsTwoChildren();
	}
	
	@Override
	public String getPhoneNumber(){
		return getPhoneNumber();
	}
	
	@Override
	public Map<String, String> getConversationIds(){
		return getConversationIds();
	}
	
	@Override
	public String getDepartureDate(){
		return getDepartureDate();
	}
	
	@Override
	public String getArrivalDate(){
		return getArrivalDate();
	}
	
	/*
	 * Charge Accounts
	 * 
	 */
	@Override
	public void createChargeAccount(String kttw, String guestId, int expectedNumberOfGuests){
		createChargeAccount(kttw, guestId, expectedNumberOfGuests);
	}
	
	@Override
	public String[] getChargeAccountIds(){
		return getChargeAccountIds();
	}
	
	@Override
	public String[] getKttwIds(){
		return getKttwIds();
	}
	
	@Override
	public void updateChargeAccountPin(String chargeAccount){
		updateChargeAccountPin(chargeAccount);
	}
	
	@Override	
	public String getChargeAccountPinNumber(){
		return getChargeAccountPinNumber();
	}
	
	@Override
	public void updateChargeAccountPinForChargeAccounts(){
		updateChargeAccountPinForChargeAccounts();
	}
	
	@Override	
	public String[] getChargeAccountPinNumbers(){
		return getChargeAccountPinNumbers();
	}
	
	@Override	
	public void validateChargeAccountPinNumberIsValid(String pin, String chargeAccount){
		validateChargeAccountPinNumberIsValid(pin, chargeAccount);
	}
	
	@Override	
	public void validateAllChargeAccountPinNumbersAreValid(){
		validateAllChargeAccountPinNumbersAreValid();
	}
	
	@Override
	public void setChargeAccounts(String[] ca){
		setChargeAccounts(ca);
	}
	
	@Override
	public String[] getGuestIds(){
		return getGuestIds();
	}
	
	@Override
	public String[] getPaymentIds(){
		return getPaymentIds();
	}
	
	@Override
	public void verifyTerminalId(){
		verifyTerminalId();
	}
	
	public void verifyTerminalIds(){
		verifyTerminalIds();
	}
	

	@Override
	public void share(){
		share();
		
	}
	
//	@Override
//	public void updateGuaranteeStatus(){
//		updateGuaranteeStatus();
//	}
	
	
	@Override
   public void RemoveRateOverride(){
	   RemoveRateOverride();
   }
	
//	@Override
//	public void retrieveComments(){
//		 retrieveComments();
//	}
	
	@Override
	public void CreateComments(){
	 CreateComments();
	}
	
	@Override
	public void UpdateComments(){
		
	}
	public void retrieveTravelPlanMediaCustomization(){
		retrieveTravelPlanMediaCustomization();
	
	
	}
	
	@Override
	public String getPrimaryGuestEmail(){
		return getPrimaryGuestEmail();
	}

	public void setPrimaryGuestEmail(String primaryGuestEmail){
		
	}
	


	@Override
	public void setPrimaryGuestAddress(String primaryGuestAddress){
		setPrimaryGuestAddress(primaryGuestAddress);
	}

	@Override
	public String getPrimaryGuestAddress(){
		return getPrimaryGuestAddress();
	}

	@Override
	public void setPrimaryGuestCity(String primaryGuestCity){
		setPrimaryGuestCity(primaryGuestCity);
	}

	@Override
	public String getPrimaryGuestCity(){
		return getPrimaryGuestCity();
	}

	@Override
	public void setPrimaryGuestState(String primaryGuestState){
		setPrimaryGuestState(primaryGuestState);
	}

	@Override
	public String getPrimaryGuestState(){
		return getPrimaryGuestState();
	}

	@Override
	public void setPrimaryGuestPostalCode(String primaryGuestPostalCode){
		setPrimaryGuestPostalCode(primaryGuestPostalCode);
	}

	@Override
	public String getPrimaryGuestPostalCode(){
		return getPrimaryGuestPostalCode();
	}
	
	
	
	
	public void createDayGuest(){
		createDayGuest();
	}
	
	@Override
	public void setPrimaryGuestOdsId(String id){
		setPrimaryGuestOdsId(id);
	}
	
	@Override
	public void setPrimaryGuestPartyId(String id){
		setPrimaryGuestPartyId(id);
	}
}

