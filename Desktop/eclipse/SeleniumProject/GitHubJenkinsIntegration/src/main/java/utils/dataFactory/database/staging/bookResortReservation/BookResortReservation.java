package utils.dataFactory.database.staging.bookResortReservation;

import utils.dataFactory.staging.Reservation;
import utils.dataFactory.staging.bookResortReservation.resorts.*;


public class BookResortReservation{
	private String environment;
	
	public BookResortReservation(String environment){
		this.environment = environment;
	}

	public BookResortReservation() {	
	}
	
	public AllStarMovies ALL_STAR_MOVIES(String environment){
		return new AllStarMovies (environment);
	}
	
	public AllStarMusic ALL_STAR_MUSIC(String environment){
		return new AllStarMusic(environment);
	}
	
	public AllStarSports ALL_STAR_SPORTS(String environment){
		return new AllStarSports(environment);
	}

	public AnimalKingdomLodge ANIMAL_KINGDOM_LODGE(String environment){
		return new AnimalKingdomLodge(environment);
	}
	
	public AnimalKingdomVillas ANIMAL_KINGDOM_VILLAS(String environment){
		return new AnimalKingdomVillas(environment);
	}
	
	public AnimalKingdomVillasJambo ANIMAL_KINGDOM_VILLAS_JAMBO(String environment){
		return new AnimalKingdomVillasJambo(environment);
	}
	
	public ArtOfAnimation ART_OF_ANIMATION(String environment){
		return new ArtOfAnimation(environment);
	}	
	
	public Aulani AULANI_RESORT(String environment){
		return new Aulani(environment);
	}	

	public AulaniVillas AULANI_VILLAS(String environment){
		return new AulaniVillas(environment);
	}	

	public BayLakeTowers BAY_LAKE_TOWERS(String environment){
		return new BayLakeTowers(environment);
	}
	
	public BeachClub BEACH_CLUB(String environment){
		return new BeachClub(environment);
	}
	
	public BoardwalkInn BOARDWALK_INN(String environment){
		return new BoardwalkInn(environment);
	}
	
	public BoardwalkVillas BOARDWALK_VILLAS(String environment){
		return new BoardwalkVillas(environment);
	}
	
	public Contemporary CONTEMPORARY(String environment){
		return new Contemporary(environment);
	}
	
	public Contemporary CONTEMPORARY(Reservation oldRes){
		return new Contemporary(oldRes);
	}
	
	public Coronado CORONADO(String environment){
		return new Coronado(environment);
	}
	
	public FortWilderness FORT_WILDERNESS(String environment){
		return new FortWilderness(environment);
	}

	public FortWildernessCabins FORT_WILDERNESS_CABINS(String environment){
		return new FortWildernessCabins(environment);
	}
	
	public GrandFloridian GRAND_FLORIDIAN(String environment){
		return new GrandFloridian(environment);
	}
	
	public GrandFloridianVillas GRAND_FLORIDIAN_VILLAS(String environment){
		return new GrandFloridianVillas(environment);
	}
	
	public HiltonHeadIsland HILTON_HEAD_ISLAND(String environment){
		return new HiltonHeadIsland(environment);
	}
	
	public Polynesian POLYNESIAN(String environment){
		return new Polynesian(environment);
	}
	
	public PopCentury POP_CENTURY(String environment){
		return new PopCentury(environment);
	}
	
	public SaratogaSprings SARATOGA_SPRINGS(String environment){
		return new SaratogaSprings(environment);
	}
	
	public VeroBeach VERO_BEACH(String environment){
		return new VeroBeach(environment);
	}
		
	public OldKeyWest OLD_KEY_WEST(String environment){
		return new OldKeyWest(environment);
	}	
}


