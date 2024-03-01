package session5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileDownloadApiTest {

    //First Create A Upload Call that can be used to validate Downloaded File
    @Test(priority = 1)
    void uploadFile(){
        File dataFile = new File("./src/main/java/testData/testData/fileForUploadTesting.txt");
        given()
                .contentType("multipart/form-data")
                .multiPart("file", dataFile)
                .post("http://localhost:8080/uploadFile");
    }

   // @Test(priority = 2, dependsOnMethods = {"uploadFile"})
    void downloadFileValidation(){
        given()
                .get("http://localhost:8080/downloadFile/fileForUploadTesting.txt")

            .then()
                .statusCode(200)
                .log().all();
    }

}
