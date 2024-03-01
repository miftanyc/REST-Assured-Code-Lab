package session6;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
    For XML Schema Conversion following Website can be used
        https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/  or
        https://www.freeformatter.com/xsd-generator.html#before-output    (I Prefer This)

    Format for XML Schema is .xsd
        XML Response(.xml)------> XML Schema(.xsd)
 */

public class XmlSchemaValidation {

    @Test(priority = 1)
    void xmlSchemaTesting(){
        given()
                .contentType(ContentType.XML)
                .get("http://restapi.adequateshop.com/api/traveler")
            .then()
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("travellerXmlSchema.xsd"));
    }


    @Test(priority = 2)
    void  xmlSchemaTestingApproach2(){
        File xmlSchema = new File("./src/main/resources/travellerXmlSchema.xsd");
        given()
                .contentType(ContentType.XML)
                .get("http://restapi.adequateshop.com/api/traveler")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsd(xmlSchema));
    }
}
