import com.bikiegang.ridesharing.pojo.User;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by hpduy17 on 6/28/15.
 */
public class ObjectTransfer {

    @Test
    public void checkObject() throws NoSuchFieldException, IllegalAccessException {
        User A = new User();

        String fieldName = "gender";
        Field f = A.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        System.out.println(f.getInt(A));
    }
}
