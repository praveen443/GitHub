package utils.dataFactory.database.staging.bookResortReservation.resorts;

import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.staging.Reservation;
import utils.dataFactory.staging.ReservationDecorator;

public class AllStarMovies extends ReservationDecorator implements Reservation{
	private int reservations;

	private void setResortInfo(){
		setLocationId(ResortColumns.MINI_CAMPUS, "Disney's All-Star Movies Resort");
		setResortCode(ResortColumns.MINI_CAMPUS, "Disney's All-Star Movies Resort");
		setFacilityId(ResortColumns.MINI_CAMPUS, "Disney's All-Star Movies Resort");
		setSouceAccountingCenterId(ResortColumns.MINI_CAMPUS, "Disney's All-Star Movies Resort");
		setRoomTypeCode("EA");
	}
	
	public AllStarMovies (String environment){
		super(environment);
		this.reservations = 1;
		 setResortInfo();
	}
	
	public AllStarMovies (String environment, int reservations){
		super(environment);
		this.reservations = reservations;
		setResortInfo();
	}
	
	public AllStarMovies (String environment, String reservations){
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

