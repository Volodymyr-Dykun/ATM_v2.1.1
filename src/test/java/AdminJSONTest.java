import atm.json.AdminJson;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by Bedrij_V on 14.02.2018.
 */
public class AdminJSONTest {
    @Test
    public void emptyJson() throws Exception {
        AdminJson adminJSON = new AdminJson();
        String link = "src\\main\\resources\\admin.atm.json";
        File file = new File(link);



    }

    @Test
    public void openAtmBalansceForUser() throws Exception {
        AdminJson adminJSON = new AdminJson();
        Integer atmBalance = adminJSON.admin.getBalance();
        Integer userAtmBalance = adminJSON.openAtmBalansceForUser();
        assertEquals(atmBalance,userAtmBalance);
    }

    @Test
    public void testAdminName() throws Exception {
        AdminJson adminJSON = new AdminJson();
        assertEquals("admin",adminJSON.admin.getLogin());
    }


}