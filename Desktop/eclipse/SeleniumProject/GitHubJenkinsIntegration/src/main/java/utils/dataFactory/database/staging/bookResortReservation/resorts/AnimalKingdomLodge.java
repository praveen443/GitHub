package utils.dataFactory.database.staging.bookResortReservation.resorts;

import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.staging.Reservation;
import utils.dataFactory.staging.ReservationDecorator;

public class AnimalKingdomLodge extends ReservationDecorator implements Reservation{
	private int reservations;

	private void setResortInfo(){
		setLocationId(ResortColumns.MINI_CAMPUS, "Disney's Animal Kingdom Lodge");
		setResortCode(ResortColumns.MINI_CAMPUS, "Disney's Animal Kingdom Lodge");
		setFacilityId(ResortColumns.MINI_CAMPUS, "Disney's Animal Kingdom Lodge");
		setSouceAccountingCenterId(ResortColumns.MINI_CAMPUS, "Disney's Animal Kingdom Lodge");
		setRoomTypeCode("QA");
	}
	
	public AnimalKingdomLodge (String environment){
		super(environment);
		this.reservations = 1;
		setResortInfo();
	}
	
	public AnimalKingdomLodge (String environment, int reservations){
		super(environment);
		this.reservations = reservations;
		setResortInfo();
	}
	
	public AnimalKingdomLodge (String environment, String reservations){
		super(environment);
		this.reservations = Integer.parseInt(reservations);
		setResortInfo();
	}

	@Override
	public void quickBook(){		
		for (int resv  = 0; resv < reservations ; resv++){
			super.quickBook();
		}
	}
	
	@Override
	public void quickBookWithAddress(){
		for (int resv  = 0; resv < reservations ; resv++){			
			super.quickBookWithAddress();
		}
	}
	
	
	@Override
	public void bookRoomOnly(){
		for (int resv  = 0; resv < reservations ; resv++){
			super.bookRoomOnly();
		}
	}
	
	@Override
	public void bookGroupBooking(){
		for (int resv  = 0; resv < reservations ; resv++){
			super.bookGroupBooking();
		}
	}
}

