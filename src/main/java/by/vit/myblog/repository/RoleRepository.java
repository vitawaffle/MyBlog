package by.vit.myblog.repository;

import by.vit.myblog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Role repository interface.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * This method finds role by name.
     *
     * @param name - role name.
     * @return role or null.
     */
    Role findByName(String name);

}
