package session6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
    Serialization       POJO Object ----> JSON
    De-serialization    JSON -------> POJO Object

    Body(POJO Object)  --> Serialization --> Converted to JSON --> Request Send --> JSON Response Received --> De-Serialization --> Converted to POJO Object
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import session2.lombok.LombokVariable;
import session2.lombok.UsersLombok;
import session2.pojoClass.POJO_PostRequest;

import java.sql.SQLOutput;
import java.util.Arrays;

public class SerializationDeserialization {

    @Test(priority = 1) //Serialization
    void pojoToJsonConversion(){
        UsersLombok user = new UsersLombok();
        LombokVariable user13Data = user.user13Data();

        //Covert java object --> json object (serialization)
        ObjectMapper ojbMapper = new ObjectMapper(); //From jackson data-bind dependency
        String user13 = null;
        try {
            user13 = ojbMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user13Data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println(user13);

    }

    @Test(priority = 2) //De-Serialization
    void jsonToPojoConversion(){
        String jsonData = "{\n" +
                "  \"id\" : \"13\",\n" +
                "  \"name\" : \"Trisha\",\n" +
                "  \"location\" : \"New Jersey\",\n" +
                "  \"profession\" : \"QA Tester\",\n" +
                "  \"subject\" : [ \"Java\", \"Python\" ],\n" +
                "  \"address\" : {\n" +
                "    \"street\" : \"W34 Street\",\n" +
                "    \"city\" : \"Newark\"\n" +
                "  }\n" +
                "}";

        //Conversion of JSON Data to POJO Object
        ObjectMapper objectMapper = new ObjectMapper(); //From jackson data-bind dependency

        LombokVariable userLombokVariable = null;
        try {
            userLombokVariable = objectMapper.readValue(jsonData, LombokVariable.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ID: "+userLombokVariable.getId());
        System.out.println("Name: "+userLombokVariable.getName());
        System.out.println("Location: "+userLombokVariable.getLocation());
        System.out.println("Profession: "+userLombokVariable.getProfession());
        System.out.println("Subject: "+ Arrays.toString(userLombokVariable.getSubject()));
        System.out.println("1st Subject: "+ userLombokVariable.getSubject()[0]);
        System.out.println("2nd Subject: "+ userLombokVariable.getSubject()[1]);
        System.out.println("Address: "+userLombokVariable.getAddress());


    }
}
