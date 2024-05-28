package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.dto.UserDTO;
import kangnamuniv.elibrary.entity.User;

public interface UserRepository {

    public Long save(User user);

    public Long login(String username, String password);
}
