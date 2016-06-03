package utils.dataFactory.database.staging.bookResortReservation.resorts;

import utils.dataFactory.ResortInfo.ResortColumns;
import utils.dataFactory.staging.Reservation;
import utils.dataFactory.staging.ReservationDecorator;

public class VeroBeach extends ReservationDecorator implements Reservation{
	private int reservations;
	private String packageCode = "M794P";

	private void setResortInfo(){
		setLocationId(ResortColumns.MINI_CAMPUS, "Disney's Vero Beach Resort");
		setResortCode(ResortColumns.MINI_CAMPUS, "Disney's Vero Beach Resort");
		setFacilityId(ResortColumns.MINI_CAMPUS, "Disney's Vero Beach Resort");
		setSouceAccountingCenterId(ResortColumns.MINI_CAMPUS, "Disney's Vero Beach Resort");
		setRoomTypeCode("IO");
		setPackageCode(this.packageCode);
	}	
	
	public VeroBeach (String environment){
		super(environment);
		this.reservations = 1;
		setResortInfo();
	}
	
	public VeroBeach (String environment, int reservations){
		super(environment);
		this.reservations = reservations;
		setResortInfo();
	}
	
	public VeroBeach (String environment, String reservations){
		super(environment);
		this.reservations = Integer.parseInt(reservations);
		setResortInfo();
	}

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
	
	@Override
	public void bookDVCCash(){
		for (int resv  = 0; resv < reservations ; resv++){
			super.bookDVCCash();
		}
	}
	
	@Override
	public void bookDVCMemberPoints(){
		for (int resv  = 0; resv < reservations ; resv++){
			super.bookDVCMemberPoints();
		}
	}

}

