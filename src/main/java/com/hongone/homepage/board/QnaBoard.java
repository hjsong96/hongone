package com.hongone.homepage.board;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class QnaBoard {
    private int qna_no;
    private int qna_type;
    private String qna_title;
    private String qna_writer;
    private String qna_content;
    private String qna_date;
    private String qna_update_date;
    private int qna_status;
    private String qna_delYn;

    public int getQna_no() {
        return qna_no;
    }

    public void setQna_no(int qna_no) {
        this.qna_no = qna_no;
    }

    public int getQna_type() {
        return qna_type;
    }

    public void setQna_type(int qna_type) {
        this.qna_type = qna_type;
    }

    public String getQna_title() {
        return qna_title;
    }

    public void setQna_title(String qna_title) {
        this.qna_title = qna_title;
    }

    public String getQna_writer() {
        return qna_writer;
    }

    public void setQna_writer(String qna_writer) {
        this.qna_writer = qna_writer;
    }

    public String getQna_content() {
        return qna_content;
    }

    public void setQna_content(String qna_content) {
        this.qna_content = qna_content;
    }

    public String getQna_date() {
        return qna_date;
    }

    public void setQna_date(String qna_date) {
        this.qna_date = qna_date;
    }

    public String getQna_update_date() {
        return qna_update_date;
    }

    public void setQna_update_date(String qna_update_date) {
        this.qna_update_date = qna_update_date;
    }

    public int getQna_status() {
        return qna_status;
    }

    public void setQna_status(int qna_status) {
        this.qna_status = qna_status;
    }

    public String getQna_delYn() {
        return qna_delYn;
    }

    public void setQna_delYn(String qna_delYn) {
        this.qna_delYn = qna_delYn;
    }
}
