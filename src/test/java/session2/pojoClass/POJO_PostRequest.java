package session2.pojoClass;

import java.util.LinkedHashMap;

public class POJO_PostRequest {

    String id;
    String name;
    String location;
    String profession;
    String[] subject;
    LinkedHashMap<String, String> address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }

    public LinkedHashMap<String, String> getAddress() {
        return address;
    }

    public void setStreet(String street){
        if(address==null){
            address = new LinkedHashMap<>();
        }
        address.put("street", street);
    }

    public void setCity(String city){
        if(address==null){
            address = new LinkedHashMap<>();
        }
        address.put("city", city);
    }

}
