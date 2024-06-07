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

        User user = User.builder()
                .id(sequence++)
                .username("test@naver.com")
                .password("test")
                .role(UserRole.ROLE_USER)
                .build();
        db.put(user.getId(), user);

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

    @Override
    public String findUsernameById(Long id) {
        User foundUser = db.values().stream()
                .filter(user -> user.getId().equals(id))
                .findAny().orElseThrow(() -> new RuntimeException("해당 id를 가진 user 없음."));
        return foundUser.getUsername();
    }
}
