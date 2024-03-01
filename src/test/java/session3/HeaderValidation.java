package session3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeaderValidation {

    @Test(priority = 1)
    void validateHeaderApproach1(){
        given().get("https://www.google.com/")
                .then()
                .header("Cache-Control", "private, max-age=0")
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server","gws");
        //The above Header does Not change with request
        //Following Header May change with new requests: Set-Cookie, Date
    }

    @Test(priority = 2)
    void validateHeaderApproach2(){
        Response response = given().get("https://www.google.com/");
        Assert.assertEquals(response.getHeader("Cache-Control"), "private, max-age=0");
        Assert.assertEquals(response.getHeader("Content-Type"), "text/html; charset=ISO-8859-1");
        Assert.assertEquals(response.getHeader("Content-Encoding"), "gzip");
        Assert.assertEquals(response.getHeader("Server"), "gws");
    }

    @Test(priority = 3)
    void printAllHeader(){
        Response response = given().get("https://www.google.com/");
        Headers headers = response.getHeaders();
        for (Header header:headers){
            System.out.println(header.getName()+"  <<----------->>  "+header.getValue());
        }
        //System.out.println(headers.get("Server"));
    }
}
