package session1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;

/*
given()
    content type, set cookies, add auth, add param, set headers info etc......

when()
    get, post, put, delete

then()
    validate status code, extract response, extract headers cookies and response body......
 */
public class HTTPRequests {

    int id;

    @Test(priority = 1)
    void getUsers(){
        given()

        .when()
                .get("https://reqres.in/api/users?page=2")

        .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    void createUser(){
        HashMap <String, String> data = new HashMap();
        data.put("name", "morpheus");
        data.put("job", "leader");

        id = given()
                .contentType(ContentType.JSON)
                .body(data)

        .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

    }

    @Test(priority = 3, dependsOnMethods = "createUser")
    void updateUser(){

        HashMap <String, String> data = new HashMap();
        data.put("name", "lal");
        data.put("job", "tester");

        given()
                .contentType(ContentType.JSON)
                .body(data)

        .when()
                .put("https://reqres.in/api/users/"+id)

        .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4, dependsOnMethods = {"createUser", "updateUser"})
    void deleteUser(){

        given()

        .when()
                .delete("https://reqres.in/api/users/"+id)

        .then()
                .statusCode(204)
                .log().all();

    }

}
