package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

    //Load Test Data Prop File
    public static Properties loadDataProp(){
        Properties dataProp = new Properties();
        File file = new File("./src/main/java/testData/testData/testData.properties");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            dataProp.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataProp;
    }

}
