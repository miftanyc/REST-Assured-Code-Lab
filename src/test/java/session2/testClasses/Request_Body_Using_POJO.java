package session2.testClasses;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import session2.pojoClass.POJO_PostRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

    /*
    Different ways to create POST request body
        1) Post request body using Hashmap
        2) Post request body using Org.JSON
        3) Post request body using POJO Class
        4) Post request body using external json file data
     */
public class Request_Body_Using_POJO {

    // 3) Post request body using POJO Class

    @Test(priority = 1)
    void postRequestUsingPOJOClass(){
        POJO_PostRequest data = new POJO_PostRequest();

        data.setId("10");
        data.setName("Priyanka");
        data.setLocation("USA");
        data.setProfession("Designer");
        data.setSubject(new String[]{"Design","Botique"});
        data.setStreet("W 34 Street");
        data.setCity("Manhattan");

        given()
                .contentType(ContentType.JSON)
                .body(data)

        .when()
                .post("http://localhost:3000/students/")

        .then()
                .statusCode(201)
                .body("name", equalTo("Priyanka"))
                .body("profession", equalTo("Designer"))
                .body("subject[0]", equalTo("Design"))
                .body("subject[1]", equalTo("Botique"))
                .body("address.street", equalTo("W 34 Street"))
                .body("address.city", equalTo("Manhattan"))
                .log().all();

    }

    @Test(priority = 2)
    void deleteRequest(){
        given().delete("http://localhost:3000/students/10").then().statusCode(200);
    }
}
