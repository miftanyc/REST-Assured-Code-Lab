package session8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import session2.lombok.LombokVariable;

public class Step3_UpdateUser {

    @Test
    void updateUser(ITestContext context){
        int id = (Integer) context.getAttribute("user_id");
        LombokVariable payLoad = (LombokVariable) context.getAttribute("payload");
        String bearerToken = "afadc24acac5f27971e86769e38ffd3e44fddf77c3e559d9eb9e49bee333e0ca";

        payLoad.setName("Lal Mia");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearerToken)
                .pathParam("id", id)
                .body(payLoad)
        .when()
                .put("https://gorest.co.in/public/v2/users/{id}")
        .then()
                .statusCode(200)
                .log().body();

        System.out.println("Updated Name: "+payLoad.getName());
    }
}
