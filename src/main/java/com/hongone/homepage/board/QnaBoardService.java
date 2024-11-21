package com.hongone.homepage.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaBoardService {

    @Autowired
    private QnaBoardDao qnaBoardDao;

    // 페이징 처리된 QnA 목록을 가져오는 메서드
    public List<QnaBoard> getQnaBoards(int page, int size, String searchType, String searchKeyword) {
        // 페이지 번호와 페이지 크기 기반으로 offset 계산
        int offset = (page - 1) * size;

        return qnaBoardDao.getQnaBoards(offset, size, searchType, searchKeyword);
    }

    public int getTotalPages(int size, String searchType, String searchKeyword) {
        int totalCount = qnaBoardDao.getTotalCount(searchType, searchKeyword);
        return (int) Math.ceil((double) totalCount / size);
    }

    public void addQnaBoard(QnaBoard qnaBoard) {
        qnaBoardDao.addQnaBoard(qnaBoard);
    }

    public QnaBoard findByNo(int qna_no) {
        return qnaBoardDao.findByNo(qna_no);
    }

    public int editQnaBoard(QnaBoard qnaBoard) {
        return qnaBoardDao.editQnaBoard(qnaBoard);
    }

    public int deleteQnaBoard(int qnaNo) {
        return qnaBoardDao.deleteQnaBoard(qnaNo);
    }


    public int checkPassword(int qna_no, String qna_pw) {
        return qnaBoardDao.checkPassword(qna_no, qna_pw);
    }
}