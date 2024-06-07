package kangnamuniv.elibrary.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MailDTO {

    private String title;
    private String content;
    private String recipient;
}
