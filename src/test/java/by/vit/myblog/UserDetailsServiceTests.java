package by.vit.myblog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserDetailsServiceTests {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void loadUserByUsername_ExistingUsername_ShouldReturnNotNull() {
        assertNotNull(userDetailsService.loadUserByUsername("TEST_USER_1"));
    }

    @Test
    public void loadUserByUsername_NotExistingUsername_ShouldThrowsUsernameNotFoundException() {
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(""));
    }

}
