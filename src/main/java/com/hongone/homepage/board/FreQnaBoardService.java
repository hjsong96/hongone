package com.hongone.homepage.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreQnaBoardService {

    @Autowired
    private FreQnaBoardDao freQnaBoardDao;

    // 페이징 처리된 QnA 목록을 가져오는 메서드
    public List<FreQnaBoard> getFreQnaBoards(int page, int size, String searchKeyword) {
        // 페이지 번호와 페이지 크기 기반으로 offset 계산
        int offset = (page - 1) * size;

        return freQnaBoardDao.getFreQnaBoards(offset, size, searchKeyword);
    }

    public int getTotalPages(int size, String searchKeyword) {
        int totalCount = freQnaBoardDao.getTotalCount(searchKeyword);
        return (int) Math.ceil((double) totalCount / size);
    }

}