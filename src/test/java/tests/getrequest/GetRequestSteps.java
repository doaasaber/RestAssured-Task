package tests.getrequest;

import commonsteps.BaseUrl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestSteps {
    private Response response;

    @Given("the base URL is set")
    public void initiateTest(){
        BaseUrl.propInitiate();
        RestAssured.baseURI=BaseUrl.props.getProperty("Url.Base");
    }

    @When("user send a GET request to : {string} {string}")
    public void userSendAGetRequest(String endpoint1,String endpoint2) {
        response = given().when().get(endpoint1+'/'+endpoint2);
        //response = given().when().get(endpoint2);
    }

    @Then("validate on response status code : \"([^\"]*)\"$")
    public void validateOnResponseStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("validate on response not null or empty")
    public void validateOnResponseNotNullOrEmpty() {
    String responseBody=response.toString();
        if (responseBody == null || responseBody.trim().isEmpty()) {
            throw new AssertionError("Response body is empty");
        }
    }
    @And("validate on response contain {string} {string}")
    public void validateOnResponseContain(String endpoint1,String endpoint2){
        String responseBody = response.getBody().asString();
        assertThat(responseBody.contains(endpoint1), equalTo(true));
        assertThat(responseBody.contains(endpoint2), equalTo(true));
    }



}
