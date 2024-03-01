package session2.testClasses;


import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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



public class Request_Body_Using_Org_JSON {

    //2) Post request body using Org.JSON
    @Test(priority = 1)
    void postRequestUsingOrgJSON(){

        //JSON Object Does Not provide ordering guarantee Use LinkedHashMap for This
        JSONObject data = new JSONObject();
        data.put("id", "9");
        data.put("name", "Moyna");
        data.put("location", "USA");
        data.put("profession", "Mechanics");

        List<String> subject = Arrays.asList("Automobile", "Mechanical");
        data.put("subject", subject);

        HashMap<String, String> address = new HashMap<>();
        address.put("street", "Ryerson Ave");
        address.put("city", "Paterson");
        data.put("address", address);

        given()
                .contentType(ContentType.JSON)
                .body(data.toString())
                //HashMap, LinkedHashMap, POJO Data Does Not require conversion
                //only JSON Format data required conversion data.toString()
        .when()
                .post("http://localhost:3000/students/")
        .then()
                .statusCode(201)
                .body("name", equalTo("Moyna"))
                .body("profession", equalTo("Mechanics"))
                .body("subject[0]", equalTo("Automobile"))
                .body("subject[1]", equalTo("Mechanical"))
                .body("address.street", equalTo("Ryerson Ave"))
                .body("address.city", equalTo("Paterson"))
                .log().all();
    }

    @Test (priority = 2)
    void deleteRequest(){
        when().delete("http://localhost:3000/students/9");
    }
}
