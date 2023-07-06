package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;

public interface IMailService {
    int doSendMail(MailDTO pDTO); //메일 발송
}
