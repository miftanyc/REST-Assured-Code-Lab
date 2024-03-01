package session6;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
    Covert the JSON Response Body to JSON Schema - For that I can use the following website:
        https://jsonformatter.org/json-to-jsonschema

    Keep in min Schema for response Json file is Also in Json Format
        i.e response.json [json response body] ---------- ResponseSchema.json []
                        {for xml response body schema is .xsd}
 */

public class JsonSchemaValidation {

    @Test(priority = 1)
    void jsonSchemaTesting(){
        given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/store")
            .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
            //matchesJsonSchemaInClasspath can only detect file that located in Resource location
    }


    @Test(priority = 2)
    void jsonSchemaTestingApproach2(){
        File jsonSchema = new File("./src/main/resources/storeJsonSchema.json");

        given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/store")
            .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
        //matchesJsonSchema can detect file anywhere in a project, just give File location via File class
    }
}
