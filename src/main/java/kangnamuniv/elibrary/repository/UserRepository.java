package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.User;

import java.util.Optional;

public interface UserRepository {

    Long save(User user);

    Optional<User> findByUsername(String username);

    String findUsernameById(Long id);
}
