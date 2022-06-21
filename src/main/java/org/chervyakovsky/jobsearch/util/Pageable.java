package org.chervyakovsky.jobsearch.util;

public class Pageable {

    private int page = 1;
    private int size = 10;
    private int offset = 0;
    private int pageCount = 1;

    public Pageable() {
    }

    public Pageable(int page, int size) {
        this.page = page;
        this.size = size;
        this.offset = (page - 1) * size;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
        this.offset = (page - 1) * this.size;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
        this.offset = (this.page - 1) * size;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void calculatePageCount(int numberOfRow) {
        this.pageCount = (int) Math.ceil((float) numberOfRow / this.size);
    }
}
