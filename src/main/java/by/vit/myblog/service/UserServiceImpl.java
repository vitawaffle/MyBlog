package by.vit.myblog.service;

import by.vit.myblog.entity.Password;
import by.vit.myblog.entity.Role;
import by.vit.myblog.entity.User;
import by.vit.myblog.exception.UnauthorizedException;
import by.vit.myblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * User service.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /** User repository. */
    private final UserRepository userRepository;

    /** Password encoder. */
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getPaginated(final Pageable pageable) {
        val users = userRepository.findAll();
        val startItem = pageable.getPageNumber() * pageable.getPageSize();
        var page = Collections.<User>emptyList();
        if (startItem >= 0 && startItem < users.size())
            page = users.subList(startItem, Math.min(startItem + pageable.getPageSize(), users.size()));
        return new PageImpl<>(page, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), users.size());
    }

    @Override
    public Long signIn(final User user) {
        val role = new Role();
        role.setId(1L);

        user.setId(null);
        user.setActive(true);
        user.setRoles(Collections.singletonList(role));
        return save(user);
    }

    @Override
    public Long updatePassword(final String username, final Password password) {
        val user = userRepository.findByUsername(username);
        if (!passwordEncoder.matches(password.getCurrentPassword(), user.getPassword()))
            throw new UnauthorizedException();
        user.setPassword(password.getNewPassword());
        return save(user);
    }

    @Override
    public User getById(final Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Long save(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public void deleteById(final Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {}
    }

}
