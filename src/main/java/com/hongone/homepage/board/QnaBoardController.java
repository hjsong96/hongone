package com.hongone.homepage.board;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/qnaboard")
public class QnaBoardController {

    @Autowired
    private QnaBoardService qnaBoardService;

    @GetMapping
    public String listQnaBoards(@RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                @RequestParam(value = "searchType", defaultValue = "search_title") String searchType,
                                @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
                                Model model) {

        // 페이징 처리된 QnA 목록 가져오기
        List<QnaBoard> qnaBoards = qnaBoardService.getQnaBoards(page, size, searchType, searchKeyword);
        int totalPages = qnaBoardService.getTotalPages(size, searchType, searchKeyword); //전체 페이지 수 계산

        model.addAttribute("qnaBoards", qnaBoards);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "qnaboard";
    }

    @GetMapping("/write")
    public String showQnaWrite(Model model) {
        /*랜덤 닉네임 생성*/
/*        List<String> actions = List.of("점프하는","노래하는","수영하는","기쁜","올바른","화난","슬픈","행복한","자랑하는","엘레강스","슈퍼","놀고있는","재미있는","활기찬","흔들리는","찬성하는","널따란");
        List<String> animals = List.of("붉은머리꿀빨이새","무스","이구아나","따오기","영국들고양이","돌거북","왕앵무","아르마딜로","노랑머리흑조","혹부리오리","북방가넷","먹황새","캥거루");

        Random random = new Random();
        String action = actions.get(random.nextInt(actions.size()));
        String animal = animals.get(random.nextInt(animals.size()));

        String randomNickname = action + " " + animal;

        model.addAttribute("qna_writer", randomNickname);*/

        return "qnaWrite";
    }

    @PostMapping("/write")
    public String addQnaBoard(@RequestParam("qna_type") int qna_type, @RequestParam("qna_title") String qna_title, @RequestParam("qna_content") String qna_content, @RequestParam("qna_writer") String qna_writer, @RequestParam("qna_pw") String qna_pw) {

        QnaBoard qnaBoard = new QnaBoard();
        qnaBoard.setQna_type(qna_type);
        qnaBoard.setQna_title(qna_title);
        qnaBoard.setQna_writer(qna_writer);
        qnaBoard.setQna_content(qna_content);
        qnaBoard.setQna_pw(qna_pw);

        qnaBoardService.addQnaBoard(qnaBoard);

        return "redirect:/qnaboard";
    }

    @GetMapping("/password-check/{qna_no}")
    public String passwordCheck(@PathVariable("qna_no") int qna_no, Model model) {
        model.addAttribute("qna_no", qna_no);

        return "qnaPWCheck";
    }

    @PostMapping("/password-check/{qna_no}")
    public ResponseEntity<Integer> checkPassword(@PathVariable("qna_no") int qna_no, @RequestParam("qna_pw") String qna_pw) {
        int result = qnaBoardService.checkPassword(qna_no, qna_pw);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/detail/{qna_no}")
    public String showQnaBoardDetail(@PathVariable("qna_no") int qna_no, Model model) {
        QnaBoard qnaBoard = qnaBoardService.findByNo(qna_no);
        System.out.println("QnaBoard: " + qnaBoard); // qnaBoard 객체 로그 확인
        if (qnaBoard != null) {
            model.addAttribute("qnaBoard", qnaBoard);
        }
        return "qnaDetail";
    }

    @PostMapping("/edit/{qna_no}")
    public String editQnaBoard(@PathVariable("qna_no") int qna_no, @ModelAttribute QnaBoard qnaBoard) {

        int isUpdated = qnaBoardService.editQnaBoard(qnaBoard);

        if (isUpdated == 1) {
            return "redirect:/qnaboard";
        } else {
            return "redirect:/qnaboard/detail/" + qna_no;
        }
    }

    @PostMapping("/delete/{qna_no}")
    public ResponseEntity<Void> deleteQnaBoard(@PathVariable("qna_no") int qna_no) {
        try {
            int response = qnaBoardService.deleteQnaBoard(qna_no);
            if (response > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
