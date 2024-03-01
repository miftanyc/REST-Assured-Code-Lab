package session3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

    @Test(priority = 1)
    void testPathAndQueryParameters(){

        given()
                .pathParam("apiEndpoint", "api")
                .pathParam("endpoint", "users")
                .queryParam("page", 2)
                .queryParam("id", 3)
                .get("https://reqres.in/{apiEndpoint}/{endpoint}")
                        //https://reqres.in/api/users?page=2&id=3
                //query parameter does not require providing, it will spontaneously applied.

        .then()
                .statusCode(200)
                .log().all();






    }
}
