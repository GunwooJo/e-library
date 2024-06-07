package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.MailDTO;
import kangnamuniv.elibrary.entity.Loan;

import java.util.List;

public interface NotificationService {

    void sendExpireAlertEmail(List<Loan> loans);

    void sendEmail(MailDTO mailDTO);
}
