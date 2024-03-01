package session2.lombok;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;

public class UsersLombok {

    public LombokVariable user12Data(){
        LombokVariable user12 = LombokVariable.builder()
                .id("12")
                .name("Tara")
                .location("India")
                .profession("Developer")
                .subject(new String[]{"Java", "Python"})
                .address(new LinkedHashMap<String, String>() {{
                    put("street", "Indira Road");
                    put("city", "Kalkata");
                }})
                .build();
        return user12;
    }

    public LombokVariable user13Data(){
        LombokVariable user13 = LombokVariable.builder()
                .id("13")
                .name("Trisha")
                .location("New Jersey")
                .profession("QA Tester")
                .subject(new String[]{"Java", "Python"})
                .address(new LinkedHashMap<String, String>() {{
                    put("street", "W34 Street");
                    put("city", "Newark");
                }})
                .build();
        return user13;
    }

    public LombokVariable randomUserGen(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName + lastName + "@glock.com";


        LombokVariable randomUser = LombokVariable.builder()
                .name(firstName + " " + lastName)
                .gender("male")
                .email(email)
                .status("inactive")
                .build();

        return randomUser;
    }

}
