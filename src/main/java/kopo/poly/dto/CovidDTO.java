package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidDTO {

    private String mmddhh; //데이터 조회 기준 일시
    private String cnt_deaths; //일일 사망
    private String rate_deaths; //인구 10만명당 사망
    private String cnt_severe_symptoms; //일일 재원 위중증
    private String rate_severe_symptoms; //인구 10만명당 재원 위중증
    private String cnt_hospitalizations; //일일 신규입원
    private String rate_hospitalizations; // 인구 10만명당 신규입원
    private String cnt_confirmations; //일일 확진
    private String rate_confirmations; //인구 10만명당 확진
}
