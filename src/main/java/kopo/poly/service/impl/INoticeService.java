package kopo.poly.service.impl;

import kopo.poly.dto.NoticeDTO;

import java.util.List;

public interface INoticeService {


    List<NoticeDTO> getNoticeList() throws Exception;

    NoticeDTO getNoticeInfo(NoticeDTO pDTO, boolean type) throws Exception;

    void insertNoticeInfo(NoticeDTO pDTO) throws Exception;

    void updateNoticeInfo(NoticeDTO pDTO) throws Exception;
}
