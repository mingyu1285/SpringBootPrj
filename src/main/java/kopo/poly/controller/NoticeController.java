package kopo.poly.controller;

import kopo.poly.dto.NoticeDTO;
import kopo.poly.service.impl.INoticeService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final INoticeService noticeService;

    @GetMapping(value = "/notice/noticeList")
    public String noticeList(ModelMap model)
        throws Exception{

        log.info(this.getClass().getName()+".noticeList Start!");

        List<NoticeDTO> rList = noticeService.getNoticeList();
        if (rList == null) rList = new ArrayList<>();

        model.addAttribute("rList", rList);

        log.info(this.getClass().getName()+".noticeList End!");

        return "/notice/noticeList";
    }

    @GetMapping(value = "/notice/noticeReg")
    public String NoticeReg(){

        log.info(this.getClass().getName()+".noticeReg Start!");
        log.info(this.getClass().getName()+".noticeReg End!");

        return"/notice/noticeReg";
    }

    @PostMapping(value = "/notice/noticeInsert")
    public String noticeInsert(HttpServletRequest request, ModelMap model, HttpSession session){

        log.info(this.getClass().getName()+".noticeInsert Start!");

        String msg = "";
        String url = "";

        try{
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String title = CmmUtil.nvl(request.getParameter("title"));
            String notice_yn = CmmUtil.nvl(request.getParameter("notice_yn"));
            String contents = CmmUtil.nvl(request.getParameter("contents"));


            log.info("session user_id : "+ user_id);
            log.info("title : "+ title);
            log.info("notice_yn : "+ notice_yn);
            log.info("contents : "+ contents);

            NoticeDTO pDTO = new NoticeDTO();
            pDTO.setUser_id(user_id);
            pDTO.setTitle(title);
            pDTO.setNotice_yn(notice_yn);
            pDTO.setContents(contents);

            noticeService.insertNoticeInfo(pDTO);

            msg = "등록되었습니다.";
            url = "/notice/noticeList";
        }catch (Exception e){
            msg = "실패하였습니다. : "+ e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            model.addAttribute("msg",msg);
            model.addAttribute("url",url);
            log.info(this.getClass().getName()+ ".noticeInsert End!");
        }

        return  "/redirect";
    }

    @GetMapping(value = "/notice/noticeInfo")
    public String noticeInfo(HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName()+ ".noticeInfo Start!");

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

        log.info("nSeq : " + nSeq);

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNotice_seq(nSeq);

        NoticeDTO rDTO = Optional.ofNullable(noticeService.getNoticeInfo(pDTO, true)).orElseGet(NoticeDTO::new);

        model.addAttribute("rDTO", rDTO);

        log.info(this.getClass().getName()+".noticeInfo End!");

        return "/notice/noticeInfo";
    }

    @GetMapping(value = "/notice/noticeEditInfo")
    public String noticeEditInfo(HttpServletRequest request, ModelMap model) throws Exception{
        log.info(this.getClass().getName()+".noticeEditInfo Start!");

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

        log.info("nSeq : "+ nSeq);

        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNotice_seq(nSeq);

        NoticeDTO rDTO = noticeService.getNoticeInfo(pDTO, false);
        if (rDTO == null) rDTO = new NoticeDTO();

        model.addAttribute("rDTO", rDTO);

        log.info(this.getClass().getName()+".noticeEditInfo End!");

        return "/notice/noticeEditInfo";
    }

    @PostMapping(value = "/notice/noticeUpdate")
    public String noticeUpdate(HttpSession session, ModelMap model, HttpServletRequest request){

        log.info(this.getClass().getName()+".noticeUpdate Start!");

        String msg = "";
        String url = "";

        try{
            String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
            String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));
            String title = CmmUtil.nvl(request.getParameter("title"));
            String notice_yn = CmmUtil.nvl(request.getParameter("notice_yn"));
            String contents = CmmUtil.nvl(request.getParameter("contents"));


            log.info("session user_id : "+ user_id);
            log.info("nSeq : "+ nSeq);
            log.info("title : "+ title);
            log.info("notice_yn : "+ notice_yn);
            log.info("contents : "+ contents);

            NoticeDTO pDTO = new NoticeDTO();
            pDTO.setUser_id(user_id);
            pDTO.setNotice_seq(nSeq);
            pDTO.setTitle(title);
            pDTO.setNotice_yn(notice_yn);
            pDTO.setContents(contents);

            noticeService.updateNoticeInfo(pDTO);

            msg = "수정되었습니다.";
            url = "/notice/noticeInfo?nSeq="+nSeq;

        }catch (Exception e){
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();
        }finally {
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            log.info(this.getClass().getName()+".noticeUpdate End!");
        }

        return "/redirect";
    }
}
