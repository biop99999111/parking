package com.boot.json.model;

public class Page {

	private int startPage;
    private int endPage;
    private boolean prev, next;
    private int total;
    private Criteria cri;

    public Page(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;
        // 페이지 끝 번호, 시작 번호 계산 로직...
}
}