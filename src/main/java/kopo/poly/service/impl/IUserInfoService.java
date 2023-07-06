package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;


public interface IUserInfoService {

    //아이디 중복체크
    UserInfoDTO getUserIdExists(UserInfoDTO pDTO) throws Exception;

    //이메일 주소 중복 체크 및 인증 값
    UserInfoDTO getEmailExists(UserInfoDTO pDTO) throws Exception;

    //회원가입하기
    int insertUserInfo(UserInfoDTO pDTO) throws Exception;

    //로그인을위해 아이디와 비밀번호가 일치하는지 확인
    UserInfoDTO getLogin(UserInfoDTO pDTO) throws Exception;



}
