package resources;

import java.util.ArrayList;
import java.util.List;

import pojoforMaps.Location;
import pojoforMaps.addPlace;

public class TestDataBuild {

	public addPlace addPlacePayload() {
		addPlace p = new addPlace();
		p.setAccuracy(50);
		p.setAddress("38, Deramone Avenue, cohen 09");
		p.setPhone_number("\"(+91) 983 893 3937");
		p.setName("White House Kaka");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		Location l = new Location();
		l.setLat(-38.383422);
		l.setLng(33.427311);
		p.setLocation(l);
		List<String> ty = new ArrayList<String>();
		ty.add("shoe park");
		ty.add("Ormeaue Park");
		p.setTypes(ty);
		
		return p;
	}
}
