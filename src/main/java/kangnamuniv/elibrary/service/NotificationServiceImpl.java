package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.MailDTO;
import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.entity.Loan;
import kangnamuniv.elibrary.repository.BookDAO;
import kangnamuniv.elibrary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
//조건우님의 대출자료인 스프링 부트...에 대한 반납예정일은 05월21일일 입니다.

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final BookDAO bookDAO;

    @Override
    public void sendExpireAlertEmail(List<Loan> loans) {
        for (Loan loan : loans) {
            Long userId = loan.getUserId();
            int bookId = loan.getBookId();
            LocalDateTime dueDate = loan.getDueDate();

            String userEmail = userRepository.findUsernameById(userId);
            String bookTitle = bookDAO.findById(bookId).getTitle();

            String message = "대출자료인 " + bookTitle + "의 반납 예정일은 " + dueDate.getMonthValue() + "월 "
                    + dueDate.getDayOfMonth() + "일 입니다.";

            MailDTO mailDTO = new MailDTO("반납 예정 도서가 있습니다.", message, userEmail);
            sendEmail(mailDTO);
        }
    }

    @Override
    public void sendEmail(MailDTO mailDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getContent());
        message.setTo(mailDTO.getRecipient());

        javaMailSender.send(message);
    }
}
