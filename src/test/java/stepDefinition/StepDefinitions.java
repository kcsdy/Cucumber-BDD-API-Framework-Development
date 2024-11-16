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
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoforMaps.Location;
import pojoforMaps.addPlace;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {

	TestDataBuild tdb = new TestDataBuild();
	Response response;
	RequestSpecification req;
	ResponseSpecification resspec;
	static String placeId;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
		req = given().spec(requestSpec()).body(tdb.addPlacePayload(name, address, language));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {

		APIResource respp = APIResource.valueOf(resource);
		System.out.println(respp.getResouce());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (httpMethod.equalsIgnoreCase("POST"))
			response = req.when().post(respp.getResouce());
		else if (httpMethod.equalsIgnoreCase("GET"))
		{
			response = req.when().get(respp.getResouce());
		}
	}

	@Then("the response should have status code of {int}")
	public void the_response_should_have_status_code_of(Integer int1) {
		response.then().assertThat().statusCode(int1);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		Assert.assertTrue(getJsonPath(response, key).equalsIgnoreCase(value));
	}

	@Then("verify the place_id to validate {string} using {string}")
	public void verify_the_place_id_to_validate_using(String name, String resource) throws IOException {

		// get API call

		placeId = getJsonPath(response, "place_id");
		req = given().spec(requestSpec()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "GET");
		String Name = getJsonPath(response, "name");
		Assert.assertTrue(Name.equalsIgnoreCase(name));

	}
	
	@Given("Delete place Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    
		req = given().spec(requestSpec()).body(tdb.deletePlacePayload(placeId));
	}

}
