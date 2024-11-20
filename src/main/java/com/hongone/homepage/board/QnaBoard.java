package com.hongone.homepage.board;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
public class QnaBoard {
    private int qna_no;
    private int qna_type;
    private String qna_title;
    private String qna_writer;
    private String qna_pw;
    private String qna_content;
    private String qna_date;
    private String qna_update_date;
    private int qna_status;
    private String qna_delYn;

}
