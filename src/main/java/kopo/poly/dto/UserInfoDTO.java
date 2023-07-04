package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
    private String user_id;
    private String user_name;
    private String password;
    private String email;
    private String addr1;
    private String addr2;
    private String reg_id;
    private String reg_dt;
    private String chg_id;
    private String chg_dt;
}
