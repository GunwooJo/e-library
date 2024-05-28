package kangnamuniv.elibrary.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private Long id;
    private String username;    //email 형식
    private String password;
    private UserRole role;
}
