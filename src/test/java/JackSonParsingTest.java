import com.bikiegang.ridesharing.parsing.Parser;
import com.bikiegang.ridesharing.pojo.Location;
import com.bikiegang.ridesharing.pojo.User;
import com.bikiegang.ridesharing.utilities.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpduy17 on 6/25/15.
 */
public class JackSonParsingTest {
    @Test
    public void parseObjectToJSon() throws JsonProcessingException {
        Object user = new User("id","email","password","facebookId","googleId","twitterId","firstName","lastName", "profilePic",DateTimeUtil.now(),User.MALE,User.UNVERIFIED);
        Object location = new Location(1.23,2.34,1,DateTimeUtil.now(),"266/52 ton dan");
        List<Object> objects = new ArrayList<>();
        objects.add(user);
        objects.add(location);
        Object o = new Object();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user);
        System.out.print(json);
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class A{

        @JsonProperty("id")
        public String a = "id";
        @JsonProperty("value")
        public Object b = new ArrayList<>();
    }

    @Test
    public void parseJSonToObject() throws IOException {
        String json = "{\n" +
                "  \"id\" : \"id\",\n" +
                "  \"email\" : \"email\",\n" +
                "  \"password\" : \"password\",\n" +
                "  \"lastName\" : \"lastName\",\n" +
                "  \"currentLocationId\" : 0,\n" +
                "  \"birthDay\" : 1435251748,\n" +
                "  \"gender\" : 1,\n" +
                "  \"status\" : 0\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        User u = (User) Parser.JSonToObject(json,User.class);
        System.out.print(u.getEmail()+" "+u.getFirstName());
    }


}
