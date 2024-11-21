package com.hongone.homepage.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FreQnaBoardDao {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.hongone.homepage.board.FreQnaBoardMapper";

    // 페이징 처리된 QnA 게시판 목록을 조회하는 메서드
    public List<FreQnaBoard> getFreQnaBoards(int offset, int size, String searchKeyword) {
        // 파라미터로 전달할 offset과 limit을 Map에 담기
        Map<String, Object> params = new HashMap<>();
        params.put("searchKeyword", "%" + searchKeyword + "%");
        params.put("offset", offset);
        params.put("size", size);

        // MyBatis 쿼리 호출
        return sqlSession.selectList(NAMESPACE + ".getFreQnaBoards", params);
    }

    public int getTotalCount(String searchKeyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchKeyword", "%" + searchKeyword + "%");
        return sqlSession.selectOne(NAMESPACE + ".getTotalCount", params);
    }
}
