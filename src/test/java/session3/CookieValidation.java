package session3;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CookieValidation {

    @Test(priority = 1)
    void validateCookie(){

        //iIea behind cookie validation is every single time you send a request cookie will be different for each time

        Map<String, String> firstTime = new LinkedHashMap<>();
        boolean flag = true;
        for (int i = 0; i < 2; i++) {
            Response response = given().get("https://www.google.com/");
            Map<String, String> cookies = response.getCookies();
            //System.out.println(cookies.keySet()); //print out all the key of Cookies

            if(i==0){
                for (String key_FirstTime:cookies.keySet()){
                    String cookie_Value_FirstTime = response.getCookie(key_FirstTime); //or cookies.get(key_FirstTime);
                    firstTime.put(key_FirstTime, cookie_Value_FirstTime);
                }
            }
            if(i==1){
                for (String key_SecondTime:cookies.keySet()){
                    String cookie_Value = response.getCookie(key_SecondTime);

                    if(key_SecondTime!="1P_JAR"){
                        if(cookie_Value==firstTime.get(key_SecondTime)){
                            flag=false;
                            break;
                        }
                    }
                }
            }
        }
        Assert.assertTrue(flag);
    }
}
