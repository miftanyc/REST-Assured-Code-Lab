package session2.testClasses;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import session2.lombok.LombokVariable;
import session2.lombok.UsersLombok;

public class Request_Body_Using_Lombok {

    UsersLombok dataStorage = new UsersLombok();

    @Test(priority = 1)
    void postRequestUsingLombok(){

        LombokVariable user12 = dataStorage.user12Data();

        given()
                .contentType(ContentType.JSON)
                .body(user12)
                .post("http://localhost:3000/students/")

                .then()
                .statusCode(201)
                .body("name", equalTo("Tara"))
                .body("profession", equalTo("Developer"))
                .body("subject[0]", equalTo("Java"))
                .body("subject[1]", equalTo("Python"))
                .body("address.street", equalTo("Indira Road"))
                .body("address.city", equalTo("Kalkata"))
                .log().all();

    }

    @Test(priority = 2)
    void deleteRequest(){
        given().delete("http://localhost:3000/students/12").then().statusCode(200);
    }
}
