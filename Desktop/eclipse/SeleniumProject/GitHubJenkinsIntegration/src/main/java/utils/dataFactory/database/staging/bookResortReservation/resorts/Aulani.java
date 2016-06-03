package utils.dataFactory.database.staging.bookResortReservation.resorts;

import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.staging.Reservation;
import utils.dataFactory.staging.ReservationDecorator;

public class Aulani extends ReservationDecorator implements Reservation{
	private int reservations;
	private String packageCode = "V663Z";
	
	private void setResortInfo(){
		setLocationId(ResortColumns.MINI_CAMPUS, "Aulani, A Disney Resort & Spa, Ko Olina, Hawaii");
		setResortCode(ResortColumns.MINI_CAMPUS, "Aulani, A Disney Resort & Spa, Ko Olina, Hawaii");
		setFacilityId(ResortColumns.MINI_CAMPUS, "Aulani, A Disney Resort & Spa, Ko Olina, Hawaii");
		setSouceAccountingCenterId(ResortColumns.MINI_CAMPUS, "Aulani, A Disney Resort & Spa, Ko Olina, Hawaii");
		setRoomTypeCode("7A");			
		setPackageCode(packageCode);
	}
	
	public Aulani (String environment){
		super(environment);
		this.reservations = 1;
		setResortInfo();
	}
	
	public Aulani (String environment, int reservations){
		super(environment);
		this.reservations = reservations;
		setResortInfo();
	}
	
	public Aulani (String environment, String reservations){
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

