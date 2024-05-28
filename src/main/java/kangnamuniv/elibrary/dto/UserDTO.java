package kangnamuniv.elibrary.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserDTO {

    private String username;    //email 형식
    private String password;
}
