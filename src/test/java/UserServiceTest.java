import com.pahanaedu.bookshopmanagement.user.dto.UserDTO;
import com.pahanaedu.bookshopmanagement.user.model.User;
import com.pahanaedu.bookshopmanagement.user.service.UserService;
import com.pahanaedu.bookshopmanagement.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    void testRegisterUser() throws SQLException {
        UserDTO u = new UserDTO();
        u.setName("Alice");
        u.setEmail("alice@mail.com");
        u.setPassword("password123");

        userService.save(u);
        UserDTO saved = userService.isUserExist("alice@mail.com", "password123");

        assertNotNull(saved);
        assertEquals("Alice", saved.getName());
    }

    @Test
    void testLoginUser() throws SQLException {
        UserDTO user = userService.isUserExist("admin@gmail.com", "Admin#1234");
        boolean isValid = user != null;

        assertTrue(isValid, "User login should succeed with correct password");
    }
}
