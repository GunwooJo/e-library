package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.UserDTO;
import kangnamuniv.elibrary.entity.User;
import kangnamuniv.elibrary.entity.UserRole;
import kangnamuniv.elibrary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long signUp(UserDTO userDTO) {

        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .role(UserRole.ROLE_USER)
                .build();

        return userRepository.save(user);
    }
}
