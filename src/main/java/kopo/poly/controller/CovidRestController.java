package kopo.poly.controller;

import kopo.poly.dto.CovidDTO;
import kopo.poly.service.impl.ICovidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/covid")
@RestController
public class CovidRestController {

    private final ICovidService covidService;

    @GetMapping("apiCovid")
    public List<CovidDTO> main() throws Exception{
        log.info(this.getClass().getName()+"");

        List<CovidDTO> covidDTOList = covidService.getCovideRes();

        return covidDTOList;
    }


}
