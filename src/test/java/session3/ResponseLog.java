package session3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ResponseLog {

    @Test
    void loggingResponse(){
        given().get("https://reqres.in/api/users?page=2&id=3")
                .then()
                .log().body()
                .log().headers()
                .log().cookies();
                //.log.all()
    }
}
