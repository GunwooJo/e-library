package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.MailDTO;

public interface NotificationService {

    void sendExpireAlertEmail();

    void sendEmail(MailDTO mailDTO);
}
