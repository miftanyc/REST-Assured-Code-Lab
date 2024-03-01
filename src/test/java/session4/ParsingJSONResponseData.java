package session4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJSONResponseData {

    @Test (priority = 1)
    void testJsonResponseApproach1(){
        given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/store")
            .then()
                .statusCode(200)
                .header("Content-Type", "application/json")
                .body("books[3].title", equalTo("Devdas"));
    }

    @Test(priority = 2)
    void testJsonResponseApproach2(){
        Response response = given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/store");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/json");
        Assert.assertEquals(response.jsonPath().get("books[3].title").toString(), "Devdas");
    }

    @Test(priority = 3)
    void testJsonResponseApproach3(){

        Response response = given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/store");

        JSONObject jo = new JSONObject(response.asString());
        //First convert response body to string format using "asString()" than JsonObject can convert String to JSON Object
        //Direct conversion of Response to JSON Object not possible
        //Must use asStrng() not toString()
            //asString() only return string value of response body
            //toString() return string value of response header, body, cookies, status code, status message and everything

        boolean status = false;

        for (int i = 0; i < jo.getJSONArray("books").length(); i++) {
            String title = jo.getJSONArray("books").getJSONObject(i).get("title").toString();
            //System.out.println("Title of the Book: "+title);
            if(title.equals("Devdas")){
                status = true;
                break;
            }

        }

        Assert.assertTrue(status, "Book Not Found in Response");
    }

    @Test(priority = 4)
    void totalPriceOfAllBooks(){
        Response response = given().contentType(ContentType.JSON).get("http://localhost:3000/store");

        JSONObject jsonObject = new JSONObject(response.asString());
        double totalPrice=0;

        for (int i = 0; i < jsonObject.getJSONArray("books").length(); i++) {
            String price = jsonObject.getJSONArray("books").getJSONObject(i).get("price").toString();
            totalPrice=totalPrice+Double.parseDouble(price);

        }
        System.out.println("Total Price of All Books: "+ totalPrice);
    }
}
        /*=====================================================================================
        asString() Method:
            [] response.asString() is a method provided by RestAssured to retrieve the response body as a String.
            [] This method is specifically designed to extract and return the body of the HTTP response as a plain text String.
            [] It is commonly used when the response body is expected to be text, JSON, XML, or any other format that can be represented as a String.

            Example: String responseBody = response.asString();


        toString() Method:
            [] response.toString() is a method inherited from the Object class, and it returns a string representation of the object.
            [] In the case of an HTTP response in RestAssured, calling toString() on the response object may not provide the actual response body. Instead, it may include additional information like status codes, headers, and other details.

            Example: String responseDetails = response.toString();
             ======================================================================================*/