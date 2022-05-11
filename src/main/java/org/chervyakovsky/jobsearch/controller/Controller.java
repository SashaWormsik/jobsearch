package org.chervyakovsky.jobsearch.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chervyakovsky.jobsearch.controller.comand.Command;
import org.chervyakovsky.jobsearch.controller.comand.CommandType;
import org.chervyakovsky.jobsearch.exception.CommandException;

import java.io.IOException;

@WebServlet(name = "controller", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();


    public void init() {
        LOGGER.log(Level.INFO, "Servlet '{}' initialization.", this.getServletName());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processing(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processing(request, response);
    }

    public void destroy() {
        LOGGER.log(Level.INFO, "Servlet '{}' destroying", this.getServletName());
    }

    private void processing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String commandStr = request.getParameter(ParameterName.COMMAND);
        Command command = CommandType.define(commandStr);
        try {
            Router router = command.execute(request, response);
            String page = router.getPage();
            if (router.getType() == Router.Type.FORWARD) {
                request.getRequestDispatcher(page).forward(request, response);
            } else if (router.getType() == Router.Type.REDIRECT) {
                response.sendRedirect(page);
            }
        } catch (CommandException exception) {
            LOGGER.log(Level.INFO, "exception in Servlet", exception); // TODO
            throw new ServletException("exception in Servlet", exception);     // TODO
        }
    }
}