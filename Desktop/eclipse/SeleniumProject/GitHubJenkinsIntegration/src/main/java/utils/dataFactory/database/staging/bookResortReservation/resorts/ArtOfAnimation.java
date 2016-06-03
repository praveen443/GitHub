package utils.dataFactory.database.staging.bookResortReservation.resorts;

import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.staging.Reservation;
import utils.dataFactory.staging.ReservationDecorator;

public class ArtOfAnimation extends ReservationDecorator implements Reservation{
	private int reservations;

	private void setResortInfo(){
		setLocationId(ResortColumns.MINI_CAMPUS, "Disneys Art of Animation");
		setResortCode(ResortColumns.MINI_CAMPUS, "Disneys Art of Animation");
		setFacilityId(ResortColumns.MINI_CAMPUS, "Disneys Art of Animation");
		setSouceAccountingCenterId(ResortColumns.MINI_CAMPUS, "Disneys Art of Animation");
		setRoomTypeCode("VS");
	}
	
	public ArtOfAnimation (String environment){
		super(environment);
		this.reservations = 1;
		setResortInfo();
	}
	
	public ArtOfAnimation (String environment, int reservations){
		super(environment);
		this.reservations = reservations;
		setResortInfo();
	}
	
	public ArtOfAnimation (String environment, String reservations){
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
