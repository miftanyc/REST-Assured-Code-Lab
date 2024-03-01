package session5;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParsingXMLResponseData {

    //Approach 1
    @Test(priority = 1)
    void testXmlResponseApproach1(){
        given()
                .contentType(ContentType.XML)
                .get("http://restapi.adequateshop.com/api/traveler?page=1")

                .then()
                .statusCode(200)
                .statusLine(containsString("OK"))
                .body("TravelerinformationResponse.page", equalTo("1"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"))
                .log().all();

    }

    //Approach 2
    @Test(priority = 2)
    void testXmlResponseApproach2(){
        Response response = given()
                .contentType(ContentType.XML)
                .get("http://restapi.adequateshop.com/api/traveler?page=1");

        Assert.assertEquals(response.getStatusCode(),200);

        String statusMessage = response.getStatusLine().split(" ")[2];//This will only give OK from status line of "HTTP/1.1 200 OK"
        Assert.assertEquals(statusMessage, "OK");

        String pageNumber = response.xmlPath().get("TravelerinformationResponse.page");
       //or String pageNumber = response.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(pageNumber, "1");

        response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name");
       //or response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name"), "Developer");

        System.out.println(response.getBody().asPrettyString()); //use asPrettyString(), Do NOT use toPrettyString()

    }

    //Approach 3
    @Test(priority = 3)
    void testXmlResponseApproach3(){
        Response response = given()
                .contentType(ContentType.XML)
                .get("http://restapi.adequateshop.com/api/travele4r?page=1");

        XmlPath xmlpath = new XmlPath(response.asString());
        List<Object> totalTravellerInformationNodes = xmlpath.getList("TravelerinformationResponse.travelers.Travelerinformation");
        System.out.println("Total Traveler Information present in One Page: "+ totalTravellerInformationNodes.size());
    }
}
