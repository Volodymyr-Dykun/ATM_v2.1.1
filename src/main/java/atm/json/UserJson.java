package atm.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import atm.entity.User;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserJson extends AbstractJson {

    public static final String LINK = "src/main/resources/user.json";
//  public static final String LINK = "src" + File.pathSeparator + "main" + File.pathSeparator + "resources" + File.pathSeparator + "user.json";
    private static List<User> users = new ArrayList<User>();

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        UserJson.users = users;
    }

    // check whether such file exists
    public UserJson()  throws IOException {
        File file = new File(LINK);
        if (file.exists()&&!file.isDirectory()) {
            setUsers(readUserJSON());                  // users = array users with user.atm.json
        } else {
            AbstractJson.writeJson(UserJson.LINK, UserJson.getUsers());
        }
    }

    public void addUser(User user) {
        users.add(user);
    }




    private static List<User> readUserJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = mapper.readValue(new File(LINK), new TypeReference<List<User>>(){});

        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
