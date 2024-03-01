package session7;
/*
        1. Go to the following link: https://github.com/DiUS/java-faker
        2. Then Copy the dipendency into pom.xml file.
        3. Create Object of Faker Class and
        4. Use the Object to Call Method
 */

import com.github.javafaker.Faker;

public class UseOfFakersDependency {

    public static void main(String[] args) {
        Faker faker = new Faker();

        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        String fullName = faker.name().fullName();

        String username = faker.name().username();
        String password = faker.internet().password();

        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().cellPhone();

        System.out.println("firstname: "+ firstname);
        System.out.println("lastname: "+ lastname);
        System.out.println("fullName: "+ fullName);
        System.out.println("username: "+ username);
        System.out.println("password: "+ password);
        System.out.println("email: "+ email);
        System.out.println("phone: "+phone);

    }
}
