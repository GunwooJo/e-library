package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.User;

import java.util.Optional;

public interface UserRepository {

    public Long save(User user);

    public Long login(String username, String password);

    public Optional<User> findByUsername(String username);
}
