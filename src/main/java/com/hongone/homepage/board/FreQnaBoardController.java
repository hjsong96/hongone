package com.hongone.homepage.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/freQnaBoard")
public class FreQnaBoardController {

    @Autowired
    private FreQnaBoardService freQnaBoardService ;

    @GetMapping
    public String listFreQnaBoards(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
                                Model model) {

        // 페이징 처리된 QnA 목록 가져오기
        List<FreQnaBoard> freQnaBoards = freQnaBoardService.getFreQnaBoards(page, size, searchKeyword);
        int totalPages = freQnaBoardService.getTotalPages(size, searchKeyword); //전체 페이지 수 계산

        model.addAttribute("freQnaBoards", freQnaBoards);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "freQnaBoard";
    }

}
