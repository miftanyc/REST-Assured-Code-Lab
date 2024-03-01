package session5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileUploadApiTest {

    @Test(priority = 1)
    void singleFileUpload(){
        File file = new File("./src/main/java/testData/testData/static import resassured.txt");

        given()
                .multiPart("file", file)
                .contentType("multipart/form-data")
                .post("http://localhost:8080/uploadFile")
            .then()
                .statusCode(200)
                .body("fileName", equalTo("static import resassured.txt"))
                .log().all();
    }

    @Test(priority = 2)
    void multiFileUploadApproach1(){
        File file1 = new File("./src/main/java/testData/testData/fileForUploadTesting.txt");
        File file2 = new File("./src/main/java/testData/testData/static import resassured.txt");

        given()
                .multiPart("files",file1)
                .multiPart("files", file2)
                .contentType("multipart/form-data")
                .post("http://localhost:8080/uploadMultipleFiles")
            .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("fileForUploadTesting.txt"))
                .body("[1].fileName",equalTo("static import resassured.txt"))
                .log().all();
    }

    @Test(priority = 3)
    void multiFileUploadAppraoch2(){
        File file1 = new File("./src/main/java/testData/testData/fileForUploadTesting.txt");
        File file2 = new File("./src/main/java/testData/testData/static import resassured.txt");

        File[] fileArray = {file1, file2}; //Won't work in every API

        given()
                .multiPart("files",fileArray)
                .contentType("multipart/form-data")
                .post("http://localhost:8080/uploadMultipleFiles")
            .then()
                .statusCode(200)
                .body("[0].fileName", equalTo("fileForUploadTesting.txt"))
                .body("[1].fileName",equalTo("static import resassured.txt"))
                .log().all();
    }
}
