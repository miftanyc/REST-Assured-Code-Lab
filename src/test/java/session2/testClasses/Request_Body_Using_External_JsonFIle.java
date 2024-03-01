package session2.testClasses;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/*
Different ways to create POST request body
    1) Post request body using Hashmap
    2) Post request body using Org.JSON
    3) Post request body using POJO Class
    4) Post request body using external json file data
 */

public class Request_Body_Using_External_JsonFIle {

    //4) Post request body using external json file data

    @Test(priority = 1)
    void postRequestUsingExternalJsonFile(){

        File file = new File("./src/test/java/session2/jsonFile/userId11.json");
        ObjectMapper objectMapper = new ObjectMapper(); //Use Jackson-databind Dependency

        String data = null;
        try {
            JsonNode rootNode = objectMapper.readTree(file);
            data = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            //Ensure ordering guarantee
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        given()
                .contentType(ContentType.JSON)
                .body(data)
                //Data already in String Format

        .when()
                .post("http://localhost:3000/students/")

        .then()
                .statusCode(201)
                .body("name", equalTo("Layla"))
                .body("profession", equalTo("Mentor"))
                .body("subject[0]", equalTo("Psychology"))
                .body("subject[1]", equalTo("Business Administration"))
                .body("address.street", equalTo("Hoops Street"))
                .body("address.city", equalTo("Jersey City"))
                .log().all();
    }

    @Test(priority = 2)
    void deleteRequest(){
        given().delete("http://localhost:3000/students/11").then().statusCode(200);
    }


    /* =================================================================================
    Data Can be Provided by following JSONObject approach, but ordering is not guarantee

        File file = new File("./src/test/java/session2/jsonFile/userId11.json");
            FileReader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            JSONTokener token = new JSONTokener(reader);
            JSONObject data = new JSONObject(token);
     ================================================================================== */
}
