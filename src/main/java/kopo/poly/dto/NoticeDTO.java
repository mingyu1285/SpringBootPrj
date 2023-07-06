package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDTO {

    private String notice_seq; //기본키, 순번
    private String title; //제목
    private String notice_yn; //공지글 여부
    private String contents; //글 내용
    private String user_id; //작성자
    private String read_cnt; //조회수
    private String reg_id; //등록자 아이디
    private String reg_dt; //등록일
    private String chg_id; //수정자 아이디
    private String chg_dt; //수정일
}
