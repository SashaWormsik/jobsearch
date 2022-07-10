package org.chervyakovsky.jobsearch.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The type FooterTag class.
 */
public class FooterTag extends TagSupport {

    /**
     * A Logger object is used to log messages for a application error.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<div class=\"w3-center\">");
            out.write("<div><h5>FIND A JOB FOR THE SOUL</h5></div>");
            out.write("<div>© 2022 - <a href=\"https://github.com/SashaWormsik\" target=\"_blank\">Sasha Wormskiy</a></div>");
            out.write("</div>");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
