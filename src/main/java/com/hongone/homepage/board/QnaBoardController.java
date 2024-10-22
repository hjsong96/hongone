package com.hongone.homepage.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/qnaboard")
public class QnaBoardController {

    @Autowired
    private QnaBoardService qnaBoardService;

    @GetMapping
    public String listQnaBoards(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                Model model) {

        // 페이징 처리된 QnA 목록 가져오기
        List<QnaBoard> qnaBoards = qnaBoardService.getAllQnaBoards(page, size);
        int totalPages = qnaBoardService.getTotalPages(size); //전체 페이지 수 계산

        model.addAttribute("qnaBoards", qnaBoards);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "qnaboard";
    }
}
