package kopo.poly.service.impl;

import kopo.poly.dto.CovidDTO;

import java.util.List;

public interface ICovidService {
    //코로나 확진자 정보 가져오기
    List<CovidDTO> getCovideRes()throws Exception;
}
