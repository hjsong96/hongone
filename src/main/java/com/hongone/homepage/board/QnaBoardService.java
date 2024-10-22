package com.hongone.homepage.board;

import com.hongone.homepage.board.QnaBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaBoardService {

    @Autowired
    private QnaBoardDao qnaBoardDao;

    // 페이징 처리된 QnA 목록을 가져오는 메서드
    public List<QnaBoard> getAllQnaBoards(int page, int size) {
        // 페이지 번호와 페이지 크기 기반으로 offset 계산
        int offset = (page - 1) * size;

        return qnaBoardDao.findAll(offset, size);
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) qnaBoardDao.count() / size);  // 전체 페이지 수 계산
    }
}