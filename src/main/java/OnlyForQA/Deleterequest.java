package OnlyForQA;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import org.testng.annotations.Test;


public class Deleterequest {
	ValidatableResponse validatableResponse;
	
	@Test
	public void DeleteBooking()
	{
		
		validatableResponse = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/5")
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete()
                .then()
                .assertThat().statusCode(201);         
 
        System.out.println("Response :" + validatableResponse.extract().asPrettyString());
	}
}
