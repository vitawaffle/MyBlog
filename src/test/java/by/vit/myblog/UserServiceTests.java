package by.vit.myblog;

import by.vit.myblog.repository.UserRepository;
import by.vit.myblog.service.UserService;
import org.junit.jupiter.api.Test;
import lombok.val;
import by.vit.myblog.entity.Role;
import by.vit.myblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getPaginated_InRange_ShouldReturnNotEmpty() {
        assertFalse(userService.getPaginated(PageRequest.of(0, 10)).isEmpty());
    }

    @Test
    public void getPaginated_OutOfRange_ShouldReturnEmpty() {
        assertTrue(userService.getPaginated(PageRequest.of(100, 100)).isEmpty());
    }

    @Test
    public void getById_ExistingId_ShouldReturnNotNull() {
        assertNotNull(userService.getById(2L));
    }

    @Test
    public void getById_NotExistingId_ShouldReturnNull() {
        assertNull(userService.getById(0L));
    }

    @Test
    public void save_Create_Valid_ShouldDoesNotThrow() throws ParseException {
        val role = new Role();
        role.setId(1L);

        val user = new User();
        user.setUsername("SOME_USER_1");
        user.setPassword("password");
        user.setActive(true);
        user.setFirstName("John");
        user.setLastName("Wick");
        user.setDob((new SimpleDateFormat("yyyy-MM-dd")).parse("1986-12-12"));
        user.setBio("I'm Barbie girl in a Barbie world...");
        user.setRoles(Collections.singletonList(role));

        assertDoesNotThrow(() -> userService.save(user));
    }

    @Test
    public void save_Update_Valid_ShouldDoesNotThrow() throws ParseException {
        val role = new Role();
        role.setId(1L);

        val user = new User();
        user.setId(3L);
        user.setUsername("SOME_USER_2");
        user.setPassword("password");
        user.setActive(true);
        user.setFirstName("Indiana");
        user.setLastName("Jones");
        user.setDob((new SimpleDateFormat("yyyy-MM-dd").parse("1899-07-01")));
        user.setBio("Listen to your heart...");
        user.setRoles(Collections.singletonList(role));

        assertDoesNotThrow(() -> userService.save(user));
    }

    @Test
    public void save_MinimalInfo_ShouldDoesNotThrow() {
        val user = new User();
        user.setUsername("SOME_USER_3");
        user.setPassword("password");

        assertDoesNotThrow(() -> userService.save(user));
    }

    @Test
    public void save_NullUsername_ShouldThrowsException() {
        val user = new User();
        user.setPassword("password");

        assertThrows(Exception.class, () -> userService.save(user));
    }

    @Test
    public void save_NotUniqueUsername_ShouldThrowsException() {
        val user = new User();
        user.setUsername("TEST_USER_1");
        user.setPassword("password");

        assertThrows(Exception.class, () -> userService.save(user));
    }

    @Test
    public void save_NullPassword_ShouldThrowsException() {
        val user = new User();
        user.setUsername("SOME_USER_4");

        assertThrows(Exception.class, () -> userService.save(user));
    }

    @Test
    public void save_NullRoleId_ShouldThrowsException() {
        val user = new User();
        user.setUsername("SOME_USER_5");
        user.setPassword("password");
        user.setRoles(Collections.singletonList(new Role()));

        assertThrows(Exception.class, () -> userService.save(user));
    }

    @Test
    public void save_NotExistingRoleId_ShouldThrowsException() {
        val role = new Role();
        role.setId(0L);

        val user = new User();
        user.setUsername("SOME_USER_6");
        user.setPassword("password");
        user.setRoles(Collections.singletonList(role));

        assertThrows(Exception.class, () -> userService.save(user));
    }

    @Test
    public void save_Update_NotExistingId_ShouldDoesNotThrow() {
        val user = new User();
        user.setId(0L);
        user.setUsername("SOME_USER_7");
        user.setPassword("password");

        assertDoesNotThrow(() -> userService.save(user));
    }

    @Test
    public void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userService.deleteById(4L));
    }

    @Test
    public void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> userService.deleteById(0L));
    }

    @Test
    public void register_ShouldHasRoleUser() {
        var user = new User();
        user.setUsername("SOME_USER_8");
        user.setPassword("password");

        user = userRepository.findById(userService.register(user)).orElse(null);

        assert user != null;
        assertEquals("USER", user.getRoles().get(0).getName());
    }

    @Test
    public void register_ShouldHasOneRole() {
        var user = new User();
        user.setUsername("SOME_USER_9");
        user.setPassword("password");

        user = userRepository.findById(userService.register(user)).orElse(null);

        assert user != null;
        assertEquals(1, user.getRoles().size());
    }

    @Test
    public void register_ShouldBeActive() {
        var user = new User();
        user.setUsername("SOME_USER_10");
        user.setPassword("password");

        user = userRepository.findById(userService.register(user)).orElse(null);

        assert user != null;
        assertTrue(user.getActive());
    }

}
