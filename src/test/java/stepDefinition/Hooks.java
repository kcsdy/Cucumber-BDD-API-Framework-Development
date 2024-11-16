package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException {
		StepDefinitions sd = new StepDefinitions();

		if (StepDefinitions.placeId == null) {
			sd.add_place_payload_with("Berry", "Asia", "Bengali");
			sd.user_calls_with_http_request("AddPlaceAPI", "POST");
			sd.verify_the_place_id_to_validate_using("Berry", "GetPlaceAPI");
		}
	}
}
