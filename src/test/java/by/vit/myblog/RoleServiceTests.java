package by.vit.myblog;

import by.vit.myblog.entity.Role;
import by.vit.myblog.service.RoleService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RoleServiceTests {

    @Autowired
    private RoleService roleService;

    @Test
    public void getAll_ShouldReturnNotEmpty() {
        assertFalse(roleService.getAll().isEmpty());
    }

    @Test
    public void getById_ExistingId_ShouldReturnNotNull() {
        assertNotNull(roleService.getById(2L));
    }

    @Test
    public void getById_NotExistingId_ShouldReturnNull() {
        assertNull(roleService.getById(0L));
    }

    @Test
    public void save_Create_Valid_ShouldDoesNotThrow() {
        val role = new Role();
        role.setName("SOME_ROLE_1");

        assertDoesNotThrow(() -> roleService.save(role));
    }

    @Test
    public void save_Update_Valid_ShouldDoesNotThrow() {
        val role = new Role();
        role.setId(3L);
        role.setName("SOME_ROLE_2");

        assertDoesNotThrow(() -> roleService.save(role));
    }

    @Test
    public void save_NullName_ShouldThrowsException() {
        assertThrows(Exception.class, () -> roleService.save(new Role()));
    }

    @Test
    public void save_NotUniqueName_ShouldThrowsException() {
        val role = new Role();
        role.setName("TEST_ROLE_1");

        assertThrows(Exception.class, () -> roleService.save(role));
    }

    @Test
    public void deleteById_ExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleService.deleteById(3L));
    }

    @Test
    public void deleteById_NotExistingId_ShouldDoesNotThrow() {
        assertDoesNotThrow(() -> roleService.deleteById(0L));
    }

}
