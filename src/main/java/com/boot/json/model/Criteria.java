package com.boot.json.model;

import lombok.Data;

@Data
public class Criteria {

	private int pageNum;   // 현재 페이지 번호
    private int amount;    // 한 페이지당 보여줄 게시물 수

    public Criteria() {
        this(1, 10); // 기본값: 1페이지, 10개씩
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
