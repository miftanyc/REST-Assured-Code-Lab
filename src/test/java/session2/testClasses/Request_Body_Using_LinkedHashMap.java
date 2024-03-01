package session2.testClasses;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

    /*
    Different ways to create POST request body
        1) Post request body using Hashmap
        2) Post request body using Org.JSON
        3) Post request body using POJO Class
        4) Post request body using external json file data
     */

public class Request_Body_Using_LinkedHashMap {

    Properties dataProp;

    // 1) Post request body using Hashmap
    @Test(priority = 1)
    void postRequestUsingLinkedHashMap(){

        //LinkedHashMap provide ordering guarantee; HashMap does NOT.
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("id", "8");
        data.put("name", "John");
        data.put("location", "USA");
        data.put("profession", "Chef");

        List<String> subject = Arrays.asList("Cooking", "Hospitality");
        data.put("subject", subject);

        HashMap<String, String> address = new HashMap<>();
        address.put("street", "Stephn Road");
        address.put("city", "Wayne");
        data.put("address", address);

        given()
                .contentType(ContentType.JSON)
                .body(data)
                //HashMap, LinkedHashMap, POJO Data Does Not require conversion
                //only JSON Format data required conversion data.toString()

        .when()
                .post("http://localhost:3000/students/")

        .then()
                .statusCode(201)
                .body("name", equalTo("John"))
                .body("profession", equalTo("Chef"))
                .body("subject[0]", equalTo("Cooking"))
                .body("subject[1]", equalTo("Hospitality"))
                .body("address.street", equalTo("Stephn Road"))
                .body("address.city", equalTo("Wayne"))
                .log().all();
    }


    @Test (priority = 2, dependsOnMethods = {"postRequestUsingLinkedHashMap"})
    void deleteRequest(){
        when().delete("http://localhost:3000/students/8");
    }
}
