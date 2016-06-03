package utils.dataFactory.database.staging;

import java.util.Map;

import core.interfaces.impl.internal.ImplementedBy;

@ImplementedBy(ReservationImpl.class)
public interface Reservation{

	public String getEnvironment() ;
	
	
	/*
	 * book accommodation sales
	 */
	public void addRoom();
	public void addRoomTwoAdults(String[] guestIds);
	public void bookRoomOnly();
	public void bookRoomOnlyTwoAdults();
	public void bookRoomOnlyTwoAdultsTwoChildren();
	public void bookWDTCTwoAdultsTwoChildren();
	public void bookWholesaleTwoAdultsNoTickets();
	public void bookGroupBooking();
	public abstract void quickBook();
	public abstract void quickBookWithAddress();

	public String getPrimaryGuestFirstName() ;
	public void setPrimaryGuestFirstName(String primaryGuestFirstName);
	

	public String getPrimaryGuestLastName() ;
	public void setPrimaryGuestLastName(String primaryGuestLastName);

	public String getNumberOfAdults() ;
	public void setNumberOfAdults(int numberOfAdults) ;
	public void setNumberOfAdults(String numberOfAdults) ;

	public String getNumberOfRooms() ;
	public void setNumberOfRooms(String numberOfRooms) ;
	public void setNumberOfRooms(int numberOfRooms) ;
	
	public String getArrivalDaysOut();
	public void setArrivalDaysOut(String arrivalDaysOut) ;
	public void setArrivalDaysOut(int arrivalDaysOut);
	public String getArrivalDate();

	public String getDepartureDaysOut() ;
	public void setDepartureDaysOut(String departureDaysOut) ;
	public void setDepartureDaysOut(int departureDaysOut) ;
	public String getDepartureDate();
	
	public String getPackageCode();
	public void setPackageCode(String packageCode) ;

	public String getResortCode();
	
	public String getRoomTypeCode() ;
	
	public void setRoomTypeCode(String roomTypeCode) ;
	
	public String getLocationId();
	
	public String getFacilityId();
	
	public String getSourceAcccountingCenterId();

	public String getTravelPlanId();
	
	public String getTravelPlanSegmentId() ;

	public String getTravelComponentGroupingId() ;
	
	public String getTravelComponentId();
	
	public String getPartyId();
	
	public String getGuestId() ;
	public String[] getAllGuestIds();
	public String[] getAllPartyIds();
	
	public void setResponseStatusCode(String code);
	public String getResponseStatusCode();
	
	public String getPhoneNumber();
	
	/*
	 * Cancel Accommodation Sales
	 */
	public void cancelAccommodation();

	/*
	 * Assign Room Gets
	 */
	
	public void assignRoomToReservation();
	public void assignRoomToReservation(String roomTypeCode, String environment);
	public void assignNotReadyRoomToReservation();
	
	public String getRoomNumber();
	
	public String getResourceId();
	
	public String getAssignmentOwnerNumber();
	
	/*
	 * Check in
	 */
	
	public void checkInReservation();
	
	/*
	 * Make Payments
	 */
	
	public void makeFullPayment();
	public void makeCardPayment(String scenario);
	public void makeCardPaymentNegative(String scenario);
	public void makeCardPaymentCardOnFile(String scenario);
	public void applyCreditToExistingCard(String scenario, String creditAmount);
	public void makeSplitCardPayment(String scenario);
	public void makeCheckPayment();
	
	public void makeFirstNightDeposit();
	
	public String getFolioId();
	
	public String getDepositDue();
	
	public String getBalanceDue();
	
	public String getOriginalTransactionId();
	
	public String getLatestTransactionId() ;
	
	public String getPaymentId();
	
	public String getRRNNumber(); 
	
	public String getRRNKey() ;
	
	/*
	 * Set Settlement
	 */
	public void createSettlementMethod();
	public void createSettlementMethod(String scenario);

	/*
	 * DVC 
	 * 
	 */
	
	public void bookDVCCash();
	
	public void bookDVCMemberPoints();
	
	public void setDVCMemberID(String DVCMemberID);
	
	public String getDVCMemberID();
	
	public void setDVCresortCode(String DVCresortCode);
	
	public String getDVCresortCode();
	
	/*
	 * Online Checkin
	 */
	public void onlineCheckin(String scenario);
	
	/*
	 * Admission Sales
	 */
	public void addKttwTickets(String scenario);
	public void addKttwTicketsTwoAdults(String scenario);
	public void addKttwTicketsTwoAdultsExistingTps(String scenario, String[] newGuestIndices);
	public String[] getAdmissionComponentId();	
	public String[] getBaseAdmissionProductId();
	
	public void cancelTickets(String scenario);
	
	/*
	 * Encoding
	 */
	public void encodePrimaryGuest();
	public String getEncoderId();
	public String getKttwId();
	public String getManufacturerUID();
	public String getRfidMediaId();
	public String getAccessEncodingStatus();
	public String getGuestRoomIndicator();
	public String getMediaAccessId();
	
	/*
	 * Conversation IDs
	 */
	public Map<String, String> getConversationIds();
	
	/*
	 * Charge Accounts
	 * 
	 */
	public void createChargeAccount(String kttw, String guestId, int expectedNumberOfGuests);
	public String[] getChargeAccountIds();
	public String[] getKttwIds();
	public void updateChargeAccountPin(String chargeAccount);	
	public String getChargeAccountPinNumber();	
	public void updateChargeAccountPinForChargeAccounts();	
	public String[] getChargeAccountPinNumbers();	
	public void validateChargeAccountPinNumberIsValid(String pin, String chargeAccount);	
	public void validateAllChargeAccountPinNumbersAreValid();	
	public void setChargeAccounts(String[] ca);
	public String[] getGuestIds();
	public String[] getPaymentIds();
	
	/*

	 * Folio
	 */
	public void verifyTerminalId();
	public void verifyTerminalIds(int i);
	
	/*
	 
	 * Accommodation Sales
	 */
	

	public void share();	
    
	/*
	 
	 * updateGuaranteeStatus
	  
	 */
//	  public void updateGuaranteeStatus();
	  
	  /*
	   *
	   * retrieveTravelPlanMediaCustomization
	   
	   */
//	  public void retrieveTravelPlanMediaCustomization();
	  
	  
    public String  getPrimaryGuestEmail();
	public void setPrimaryGuestEmail(String primaryGuestEmail);

	
	public void setPrimaryGuestAddress(String primaryGuestAddress);
	public String getPrimaryGuestAddress();
	public void setPrimaryGuestCity(String primaryGuestCity);
	public String getPrimaryGuestCity();
	public void setPrimaryGuestState(String primaryGuestState);
	public String getPrimaryGuestState();
	public void setPrimaryGuestPostalCode(String primaryGuestPostalCode);
	public String getPrimaryGuestPostalCode();
	
    public void SearchResortReservationsByGuest();
    public void RemoveRateOverride();
//    public void retrieveComments();
    public void CreateComments();
    public void UpdateComments();
    public void setPhoneNumber(String number);
	  
	/*
	 * Travel Plan Sales Orders
	 */
	public void createDayGuest();
	
	public void setPrimaryGuestOdsId(String id);
	public void setPrimaryGuestPartyId(String id);
	
		
		
	
}

