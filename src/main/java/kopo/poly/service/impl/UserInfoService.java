package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserInfoMapper;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserInfoService implements IUserInfoService{

    private final IUserInfoMapper userInfoMapper;// 회원관련 SQL 사용하기 위한 Mapper가져오기

    private final IMailService mailService; //메일 발송을 위한 MailService 자바 객체 가져오기


    @Override
    public UserInfoDTO getUserIdExists(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+".getUserIdExists Start!");

        UserInfoDTO rDTO = userInfoMapper.getUserIdExists(pDTO);

        log.info(this.getClass().getName()+".getUserIdExists End!");

        return rDTO;
    }

    @Override
    public UserInfoDTO getEmailExists(UserInfoDTO pDTO) throws Exception {
        log.info(this.getClass().getName()+".emailAuth Start!");
        log.info("pdto email : "+pDTO.getEmail());
        //DB 이메일이 존재하는지 SQL 쿼리 실행
        //SQL 쿼리에 COUNT()를 사용하기 때문에 반드시 조회 결과는 존재함
        UserInfoDTO rDTO = userInfoMapper.getEmailExists(pDTO);
        if (rDTO == null){
            rDTO = new UserInfoDTO();
        }
        String exists_yn = CmmUtil.nvl(rDTO.getExists_yn());

        log.info("exists_yn : "+exists_yn);

        if(exists_yn.equals("N")){
            //6자리 랜덤 숫자 생성
            int authNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);

            log.info("authNumber : " + authNumber);

            //인증번호 발송 로직
            MailDTO dto = new MailDTO();

            dto.setTitle("이메일 중복 확인 인증번호 발송 메일");
            dto.setContents("인증번호는 : " + authNumber+"입니다.");
            dto.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

            mailService.doSendMail(dto); // 이메일 발 송

            dto = null;

            rDTO.setAuthNumber(authNumber); //인증번호 결과값에 넣어주기
        }

        log.info(this.getClass().getName()+".emailAuth End!");
        return rDTO;
    }

    @Override
    public int insertUserInfo(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+".insertUserInfo Start!");

        int res=0;

        res = userInfoMapper.insertUserInfo(pDTO);

        log.info(this.getClass().getName()+".insertUserInfo End!");

        return res;
    }

    @Override
    public UserInfoDTO getLogin(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName()+".getLogin Start!");

        UserInfoDTO rDTO = Optional.ofNullable(userInfoMapper.getLogin(pDTO)).orElseGet(UserInfoDTO::new);

        if (CmmUtil.nvl(rDTO.getUser_id()).length() > 0){
            log.info("로그인 성공");
        }
        log.info(this.getClass().getName()+".getLogin End!");

        return rDTO;
    }


}
