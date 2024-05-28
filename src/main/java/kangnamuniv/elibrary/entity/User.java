package kangnamuniv.elibrary.entity;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;    //email 형식
    private String password;
    private UserRole role;
}
