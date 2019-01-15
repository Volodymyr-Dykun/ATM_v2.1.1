package atm.json;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import atm.entity.Admin;

import java.io.File;
import java.io.IOException;

public class AdminJson extends AbstractJson {
    public static final String LINK = "src/main/resources/admin.json";            // path, where will be stored admin.atm.json
    public static Admin admin;

    public AdminJson() throws IOException {
        File file = new File(LINK);
        if (!file.exists() || file.isDirectory()) {
            Admin admin = new Admin();
            admin.setLogin("admin");
            admin.setPassword(1234);
            admin.setBalance(100000);
            AdminJson.writeJson(AdminJson.LINK, admin);
        }
            admin = readAdminJSON();
        }

    public Integer openAtmBalansceForUser() throws IOException {
            return admin.getBalance();
    }

    private static Admin readAdminJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
           admin = mapper.readValue(new File(LINK), Admin.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

}
