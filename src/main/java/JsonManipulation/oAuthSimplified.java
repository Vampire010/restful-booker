package JsonManipulation;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class oAuthSimplified {
	
	@Test
    public void testOAuth2Authentication() {
        // Set the base URI for the token endpoint
        RestAssured.baseURI = "https://authorization-server.com";

        // Define the request parameters
        String grantType = "authorization_code";
        String clientId = "XbBhX1jcsUCkpRKQSRfdY1ie";
        String clientSecret = "QYkSGcOpSJylRJNCpX3KsD66v6n4HQXQ_4C7glGpum_uR04P";
        String redirectUri = "https://www.oauth.com/playground/authorization-code.html";
        String code = "Dzd7wzmF4EBOYGnlHv4AG6ob_5qORwy2bUq1cjnjOj1bRrjl";

        // Send a POST request to obtain the access token
        Response response = given()
                                .formParam("grant_type", grantType)
                                .formParam("client_id", clientId)
                                .formParam("client_secret", clientSecret)
                                .formParam("redirect_uri", redirectUri)
                                .formParam("code", code)
                            .when()
                                .post("/token");

        // Verify the response status code
        response.then().assertThat().statusCode(302);

    }
}
