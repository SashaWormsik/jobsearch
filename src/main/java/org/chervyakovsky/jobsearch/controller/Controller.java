package org.chervyakovsky.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.command.Command;
import org.chervyakovsky.jobsearch.controller.command.CommandType;
import org.chervyakovsky.jobsearch.exception.CommandException;
import org.chervyakovsky.jobsearch.model.mapper.RequestContent;
import org.chervyakovsky.jobsearch.model.pool.ConnectionPool;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();


    public void init() {
        LOGGER.log(Level.INFO, "'{}' initialization.", this.getServletName());
        ConnectionPool.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
        LOGGER.log(Level.INFO, "'{}' destroying", this.getServletName());
        ConnectionPool.getInstance().destroyPool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); // TODO in filter
        RequestContent requestContent = new RequestContent();
        String commandStr = request.getParameter(ParameterName.COMMAND);
        Command command = CommandType.define(commandStr);
        try {
            requestContent.extractValues(request); // fixme
            Router router = command.execute(requestContent);
            requestContent.insertAttribute(request);  // fixme
            String page = router.getPage();
            Router.Type routerType= router.getType();
            switch (routerType) {
                case FORWARD:
                    request.getRequestDispatcher(page).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(page);
                    break;
                default:
                    throw new ServletException("exception in servlet");     // fixme
            }
        } catch (CommandException exception) {
            LOGGER.log(Level.INFO, "exception in servlet", exception); // TODO
            throw new ServletException("exception in servlet", exception);     // TODO
        }
    }
}