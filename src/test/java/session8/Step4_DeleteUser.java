package session8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import groovy.grape.GrapeIvy;
import io.restassured.http.ContentType;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Step4_DeleteUser {

    @Test
    void deleteUser(ITestContext context){
        int id = (Integer) context.getAttribute("user_id");
        String bearerToken = "afadc24acac5f27971e86769e38ffd3e44fddf77c3e559d9eb9e49bee333e0ca";

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .header("Authorization", "Bearer " + bearerToken)
                .delete("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(204)
                .log().body();

        System.out.println("User Deleted");

    }
}
