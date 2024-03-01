package session8;

import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Step2_GetUser {

    @Test
    void getUser(ITestContext context){
        int id= (Integer) context.getAttribute("user_id"); //Object can only be cast to Wrapper class. That's why Integer casting
        String bearerToken = "afadc24acac5f27971e86769e38ffd3e44fddf77c3e559d9eb9e49bee333e0ca";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearerToken)
                .pathParam("id", id)
        .when()
                .get("https://gorest.co.in/public/v2/users/{id}")
        .then()
                .statusCode(200)
                .log().body();
    }
}
