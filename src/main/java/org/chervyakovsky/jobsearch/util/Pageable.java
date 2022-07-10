package org.chervyakovsky.jobsearch.util;

/**
 * The Pageable util class.
 * It is intended for the organization of pagination.
 */
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

    /**
     * Returns the current page.
     *
     * @return returns the current page.
     */
    public int getPage() {
        return this.page;
    }

    /**
     * Sets the current page.
     *
     * @param page the current page.
     */
    public void setPage(int page) {
        this.page = page;
        this.offset = (page - 1) * this.size;
    }

    /**
     * Returns the size of the list of items.
     *
     * @return size.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the size of the offset.
     *
     * @return the size of the offset.
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Returns the number of pages.
     *
     * @return the number of pages.
     */
    public int getPageCount() {
        return this.pageCount;
    }

    /**
     * Counts the number of pages by the number of results found.
     *
     * @param numberOfRow total number of results found.
     */
    public void calculatePageCount(int numberOfRow) {
        this.pageCount = (int) Math.ceil((float) numberOfRow / this.size);
    }
}
