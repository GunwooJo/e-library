package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.User;
import kangnamuniv.elibrary.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryUserRepository implements UserRepository{

    private Long sequence = 0L;
    private final Map<Long, User> db = new HashMap<>();

    public MemoryUserRepository() { //admin 계정 생성
        User admin = User.builder()
                .id(sequence++)
                .username("admin@naver.com")
                .password("admin")
                .role(UserRole.ROLE_ADMIN)
                .build();
        db.put(admin.getId(), admin);
    }

    @Override
    public Long save(User user) {
        user.setId(sequence++);
        db.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return db.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
