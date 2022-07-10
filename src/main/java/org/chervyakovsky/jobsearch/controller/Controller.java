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

import java.io.IOException;

/**
 * The type Controller class. Manage requests and forms responses for clients.
 * Override GET and POST methods.
 *
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    /**
     * A Logger object is used to log messages for a application info.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Called by the servlet container to indicate to a servlet that the
     * servlet is being placed into service.
     *
     * @throws ServletException if an exception occurs that interrupts the servlet's normal operation
     */
    public void init() {
        LOGGER.log(Level.INFO, "'{}' initialization.", this.getServletName());
    }

    /**
     * Called by the server to allow a servlet to handle a GET request.
     *
     * @param request  an {@link HttpServletRequest} object that contains the request
     *                 the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is  detected when the servlet handles the GET request
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Called by the server to allow a servlet to handle a GET request.
     *
     * @param request  an {@link HttpServletRequest} object that contains the request
     *                 the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Called by the servlet container to indicate to a servlet that the
     * servlet is being taken out of service.
     */
    public void destroy() {
        LOGGER.log(Level.INFO, "'{}' destroying", this.getServletName());
    }

    /**
     * The method of processing the request according to the received {@link CommandType}
     *
     * @param request  an {@link HttpServletRequest} object that contains the request
     *                 the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @throws ServletException if the request cannot be processed
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestContent requestContent = new RequestContent();
        String commandStr = request.getParameter(ParameterName.COMMAND);
        Command command = CommandType.define(commandStr);
        try {
            requestContent.extractValues(request);
            Router router = command.execute(requestContent);
            requestContent.insertAttribute(request);
            switch (router.getType()) {
                case FORWARD:
                    request.getRequestDispatcher(router.getPage()).forward(request, response);
                    break;
                case REDIRECT:
                    response.sendRedirect(router.getPage());
                    break;
                default:
                    throw new ServletException();
            }
        } catch (CommandException exception) {
            LOGGER.log(Level.INFO, exception);
            throw new ServletException(exception);
        }
    }
}