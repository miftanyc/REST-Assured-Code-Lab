package session2.lombok;

import lombok.*;

import java.util.LinkedHashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode

public class LombokVariable {

    private String id;
    private String name;
    private String location;
    private String profession;
    private String[] subject;
    private LinkedHashMap<String, String> address;


    private String email;
    private String gender;
    private String status;
}
