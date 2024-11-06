package stepDefinition;

import java.io.IOException;
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
	RequestSpecification respc;
	RequestSpecification req;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name,String language,String address) throws IOException {
		
		req = requestSpec();
		
		respc = given().spec(req).body(tdb.addPlacePayload(name,address,language));
		
	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
	    
		resp = respc.when().post("/maps/api/place/add/json");
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
