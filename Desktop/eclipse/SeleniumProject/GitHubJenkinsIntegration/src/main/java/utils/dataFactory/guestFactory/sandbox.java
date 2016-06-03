package utils.dataFactory.guestFactory;

import org.junit.Test;

public class sandbox {
@Test
public void main(){
	Guest guest = new Guest();
	System.out.println(guest.getFullName());
	System.out.println(guest.getAge());
	System.out.println(guest.getBirthDate());
	System.out.println(guest.getSsn());
	System.out.println(guest.primaryAddress().getAddress1());
	System.out.println(guest.primaryPhone().getNumber());
	System.out.println(guest.primaryEmail().getEmail());
	
}
}
