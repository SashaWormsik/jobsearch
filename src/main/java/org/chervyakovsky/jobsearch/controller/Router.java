package org.chervyakovsky.jobsearch.controller;

/**
 * The class Router.
 */
public class Router {
    /**
     * The page request would be redirected/forwarded to.
     */
    private String page = PagePath.MAIN_PAGE;
    /**
     * Determines whether you would be redirected or forwarded
     */
    private Type type = Type.FORWARD;

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Forward type.
         */
        FORWARD,
        /**
         * Redirect type.
         */
        REDIRECT
    }

    /**
     * Instantiates a new {@link Router}.
     */
    public Router() {
    }

    /**
     * Instantiates a new {@link Router}.
     *
     * @param page The page address request would be redirected/forwarded to.
     */
    public Router(String page) {
        this.page = page;
    }

    /**
     * Instantiates a new {@link Router}.
     *
     * @param page The page address request would be redirected/forwarded to.
     * @param type Determines whether you would be redirected or forwarded
     */
    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    /**
     * Get destination page address String.
     *
     * @return The page address.
     */
    public String getPage() {
        return page;
    }

    /**
     * Set destination page address String.
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Get current type.
     *
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Set type.
     */
    public void setType(Type type) {
        this.type = type;
    }
}
