package com.hongone.homepage.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QnaBoardDao {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.hongone.homepage.board.QnaBoardMapper";

    // 페이징 처리된 QnA 게시판 목록을 조회하는 메서드
    public List<QnaBoard> findAll(int offset, int size) {
        // 파라미터로 전달할 offset과 limit을 Map에 담기
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("size", size);

        // MyBatis 쿼리 호출
        return sqlSession.selectList(NAMESPACE + ".findAll", params);
    }

    public int count() {
        return sqlSession.selectOne(NAMESPACE + ".count");
    }
}
