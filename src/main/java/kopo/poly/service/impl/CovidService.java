package kopo.poly.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kopo.poly.dto.ApiDTO;
import kopo.poly.dto.CovidDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service("CovidService")
public class CovidService implements ICovidService{

    private final RestTemplate restTemplate;
    @Value("${api.service.key}")
    private String apiKey;

    @Override
    public List<CovidDTO> getCovideRes() throws Exception {
        log.info(this.getClass().getName()+"코로나 확진자 정보 가져오기 시작!");

        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("apis.data.go.kr")
                .path("/1790387/covid19CurrentStatusKorea/covid19CurrentStatusKoreaJason")
                .queryParam("serviceKey", apiKey)
                .build().toUriString();

        log.info("생성된 url 확인 : "+ url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.getInterceptors().add(((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        }));

        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<ApiDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, ApiDTO.class);

        log.info("api 조회 결과는 ? :"+response.getStatusCode());
        log.info("api 조회 결과 헤더에 담긴 정보는? : "+ response.getHeaders());
        log.info("api 조회 실제 데이터는 ? : "+ response.getBody().getResponse().get("result"));

        List<CovidDTO> covidDTOList = new ObjectMapper().convertValue(response.getBody().getResponse().get("result"), new
                TypeReference<List<CovidDTO>>() {


                });

        log.info(this.getClass().getName()+"코로나 확진자 정보 가져오기 종료!");

        return covidDTOList;
    }
}
