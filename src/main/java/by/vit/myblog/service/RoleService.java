package by.vit.myblog.service;

import by.vit.myblog.entity.Role;

import java.util.List;

/**
 * Role service interface.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
public interface RoleService extends MyService<Role, Long> {

    /**
     * This method gets all roles.
     *
     * @return list of roles or empty list.
     */
    List<Role> getAll();

}
