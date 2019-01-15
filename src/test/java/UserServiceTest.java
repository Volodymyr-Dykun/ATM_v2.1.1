import org.junit.Test;
import atm.service.UserService;

import java.lang.reflect.Method;

public class UserServiceTest {

    @Test
    public void testMenu() throws Exception {
        UserService userService = new UserService();
        Method method = userService.getClass().getDeclaredMethod("userMenu");
        method.setAccessible(true);
        method.invoke(userService);
    }
}
