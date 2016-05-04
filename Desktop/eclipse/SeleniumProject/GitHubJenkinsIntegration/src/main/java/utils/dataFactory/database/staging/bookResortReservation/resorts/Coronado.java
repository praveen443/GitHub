package utils.dataFactory.database.staging.bookResortReservation.resorts;

import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.staging.ReservationDecorator;
import utils.dataFactory.staging.Reservation;

public class Coronado extends ReservationDecorator implements Reservation{
	private int reservations;

	private void setResortInfo(){
		setLocationId(ResortColumns.MINI_CAMPUS, "Disney's Coronado Springs Resort");
		setResortCode(ResortColumns.MINI_CAMPUS,"Disney's Coronado Springs Resort");
		setFacilityId(ResortColumns.MINI_CAMPUS, "Disney's Coronado Springs Resort");
		setSouceAccountingCenterId(ResortColumns.MINI_CAMPUS, "Disney's Coronado Springs Resort");
		setRoomTypeCode("NA");
	}
	
	public Coronado (String environment){
		super(environment);
		this.reservations = 1;
		setResortInfo();
	}
	
	public Coronado (String environment, int reservations){
		super(environment);
		this.reservations = reservations;
		setResortInfo();
	}
	
	public Coronado (String environment, String reservations){
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

