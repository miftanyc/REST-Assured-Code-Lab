package session7;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AuthenticationTypes {

    @Test(priority = 1)
    void testBasicAuth(){
        given()
                .contentType(ContentType.JSON)
                .auth().basic("postman", "password")
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();
    }

    @Test(priority = 2)
    void testDigestAuth(){
        given()
                .contentType(ContentType.JSON)
                .auth().digest("postman", "password")
                .get("https://postman-echo.com/digest-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();
    }

    @Test(priority = 3)
    void testPreemptiveAuth(){
        given()
                .contentType(ContentType.JSON)
                .auth().preemptive().basic("postman", "password")
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated", equalTo(true))
                .log().body();
    }

    @Test(priority = 4)
    void testBearerToken(){
        String bearerToken = "afadc24acac5f27971e86769e38ffd3e44fddf77c3e559d9eb9e49bee333e0ca";
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+bearerToken)
                .get("https://gorest.co.in/public/v2/users/2139093")
        .then()
                .statusCode(200)
                .log().body();
    }

    @Test(priority = 5, enabled = false)
    void testOAuth1(){  //No oauth1 api available to test. Just the procedure are noted here
        given()
                .contentType(ContentType.JSON)
                .auth().oauth("consumerKey", "customerSecret", "accessToken", "tokenSecrete")
                //4 Element are required to authenticate oauth1: "consumerKey", "customerSecret", "accessToken", "tokenSecrete".

                .get("url to be provided")
        .then()
                .statusCode(200);

    }


    @Test(priority = 6, enabled = false)
    void testOAuth2(){
        String aAuth2Token = "Paste it From Postman Collection Not Provided Here for Github Upload Reason";
        //aAuth2Token Stored in Postman. For Security purpose not mentioned here.

        given()
                .contentType(ContentType.JSON)
                .auth().oauth2( aAuth2Token) //Stored in Postman. For Security purpose not mentioned here.

                .get("https://api.github.com/users/mojombo")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(priority = 7)
    void testApiKey(){
        String apiKey = "c0b4660ebeb1c3df5ebcc3364d1f7e16";
        given()
                .contentType(ContentType.JSON)
                .queryParam("q", "München,DE")
                .queryParam("appid", apiKey)
                .get("https://api.openweathermap.org/data/2.5/forecast")
        .then()
                .statusCode(200)
                .log().body();
    }
}
