package session8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import session2.lombok.LombokVariable;
import session2.lombok.UsersLombok;

public class Step1_CreateUser{
    static UsersLombok lombok;
    static {lombok = new UsersLombok();}

    @Test
    void createUser(ITestContext context){
        String bearerToken = "afadc24acac5f27971e86769e38ffd3e44fddf77c3e559d9eb9e49bee333e0ca";
        LombokVariable payLoad = lombok.randomUserGen();
        context.setAttribute("payload", payLoad);

        int id = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearerToken)
                .body(payLoad)
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        context.setAttribute("user_id", id);


        System.out.println(id);
        System.out.println(payLoad.getName());
        System.out.println(payLoad.getEmail());
    }

}
