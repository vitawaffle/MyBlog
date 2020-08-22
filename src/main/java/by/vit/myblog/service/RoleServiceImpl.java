package by.vit.myblog.service;

import by.vit.myblog.entity.Role;
import by.vit.myblog.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Role service.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    /** Role repository. */
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(final Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Long save(final Role role) {
        return roleRepository.save(role).getId();
    }

    @Override
    public void deleteById(final Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {}
    }

}
