package com.pcwk.ehr.board.domain;

import oracle.sql.DATE;

public class BoardVO {

    int article_no;
    String article_user_id;
    String article_title;
    String article_contents;
    int article_board_div;
    int article_read_cnt;
    String article_reg_date;
    String article_mod_date;

    public BoardVO() {
    }

    public BoardVO(int article_no, String article_user_id, String article_title, String article_contents,
            int article_board_div, int article_read_cnt, String article_reg_date, String article_mod_date) {
        super();
        this.article_no = article_no;
        this.article_user_id = article_user_id;  // 수정된 부분: article_reg_user_id -> article_user_id
        this.article_title = article_title;
        this.article_contents = article_contents;
        this.article_board_div = article_board_div;
        this.article_read_cnt = article_read_cnt;
        this.article_reg_date = article_reg_date;
        this.article_mod_date = article_mod_date;
    }

    public int getArticle_no() {
        return article_no;
    }
    public void setArticle_no(int article_no) {
        this.article_no = article_no;
    }
    public String getArticle_user_id() {  // 수정된 부분: article_reg_user_id -> article_user_id
        return article_user_id;
    }
    public void setArticle_user_id(String article_user_id) {  // 수정된 부분: article_reg_user_id -> article_user_id
        this.article_user_id = article_user_id;
    }
    public String getArticle_title() {
        return article_title;
    }
    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }
    public String getArticle_contents() {
        return article_contents;
    }
    public void setArticle_contents(String article_contents) {
        this.article_contents = article_contents;
    }
    public int getArticle_board_div() {
        return article_board_div;
    }
    public void setArticle_board_div(int article_board_div) {
        this.article_board_div = article_board_div;
    }
    public int getArticle_read_cnt() {
        return article_read_cnt;
    }
    public void setArticle_read_cnt(int article_read_cnt) {
        this.article_read_cnt = article_read_cnt;
    }
    public String getArticle_reg_date() {
        return article_reg_date;
    }
    public void setArticle_reg_date(String article_reg_date) {
        this.article_reg_date = article_reg_date;
    }
    public String getArticle_mod_date() {
        return article_mod_date;
    }
    public void setArticle_mod_date(String article_mod_date) {
        this.article_mod_date = article_mod_date;
    }

    @Override
    public String toString() {
        return "BoardVO [article_no=" + article_no + ", article_user_id=" + article_user_id + ", article_title="
                + article_title + ", article_contents=" + article_contents + ", article_board_div=" + article_board_div
                + ", article_read_cnt=" + article_read_cnt + ", article_reg_date=" + article_reg_date
                + ", article_mod_date=" + article_mod_date + "]";
    }

}
