package stepDefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoforMaps.Location;
import pojoforMaps.addPlace;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {

	TestDataBuild tdb = new TestDataBuild();
	Response resp;
	RequestSpecification req;
	@Given("Add Place Payload")
	public void add_place_payload() {
		
		req = requestSpec();
		
	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
	    
		resp = given().log().all().spec(req).body(tdb.addPlacePayload()).when().post("/maps/api/place/add/json");
	}

	@Then("the response should have status code of {int}")
	public void the_response_should_have_status_code_of(Integer int1) {
		resp.then().log().all().assertThat().statusCode(int1)
				.extract().response().asString();
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String response = resp.then().assertThat().statusCode(200)
				.extract().response().asString();
		JsonPath js = new JsonPath(response);
		Assert.assertTrue(js.getString(key).equalsIgnoreCase(value));
	}

}
