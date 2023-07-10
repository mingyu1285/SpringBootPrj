package kopo.poly.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiDTO {
    private String resultCode;
    private String resultCnt;
    private String resultMsg;

    private Map<String, Object> response;
}
