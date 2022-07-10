package org.chervyakovsky.jobsearch.model.mapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * The type RequestContent class.
 */
public class RequestContent {
    private HashMap<String, Object> requestAttribute;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttribute;
    private HttpSession session;

    public RequestContent() {
        this.requestAttribute = new HashMap<>();
        this.requestParameters = new HashMap<>();
        this.sessionAttribute = new HashMap<>();
    }

    /**
     * Returns a map of the request attributes.
     *
     * @return a map of the request attributes.
     */
    public HashMap<String, Object> getRequestAttribute() {
        return this.requestAttribute;
    }

    /**
     * Returns a map of request parameters.
     *
     * @return a map of request parameters.
     */
    public HashMap<String, String[]> getRequestParameters() {
        return this.requestParameters;
    }

    /**
     * Returns a map of session attributes.
     *
     * @return a map of session attributes.
     */
    public HashMap<String, Object> getSessionAttribute() {
        return this.sessionAttribute;
    }

    /**
     * Returns the session object.
     *
     * @return session
     */
    public HttpSession getSession() {
        return session;
    }

    /**
     * Extracts parameters and attributes from the session and request,
     * and puts them in the corresponding map of this RequestContent object.
     *
     * @param request the request
     * @see HttpServletRequest
     */
    public void extractValues(HttpServletRequest request) {
        extractRequestParameters(request);
        extractRequestAttribute(request);
        extractSessionAttribute(request);
        this.session = request.getSession(false);
    }

    /**
     * Inserts attributes from the maps of this object into the session and request attribute maps.
     *
     * @param request the request
     * @see HttpServletRequest
     */
    public void insertAttribute(HttpServletRequest request) {
        insertAttributeToRequest(request);
        insertAttributeToSession(request);
    }

    /**
     * Moves values from a map with parameters to a map with attributes of this object.
     */
    public void setParameterInAttribute() {
        for (Map.Entry<String, String[]> entry : this.requestParameters.entrySet()) {
            this.requestAttribute.put(entry.getKey(), entry.getValue()[0]);
        }
    }

    /**
     * Sets a new value in the request attribute map.
     *
     * @param nameAttribute the name of the attribute.
     * @param value         the value of this attribute.
     */
    public void setNewValueInRequestAttributes(String nameAttribute, Object value) {
        this.requestAttribute.put(nameAttribute, value);
    }

    /**
     * Sets a new value in the session attribute map.
     *
     * @param nameAttribute the name of the attribute.
     * @param value         the value of this attribute.
     */
    public void setNewValueInSessionAttribute(String nameAttribute, Object value) {
        this.sessionAttribute.put(nameAttribute, value);
    }

    /**
     * Returns the parameter value as a string.
     *
     * @param parameterName the name of the parameter.
     * @return the value associated with that name, or null, it there is no such value.
     */
    public String getParameterFromRequest(String parameterName) {
        String[] parameterValues = this.requestParameters.get(parameterName);
        if (parameterValues != null && parameterValues.length > 0 && parameterValues[0] != null) {
            return parameterValues[0];
        }
        return null;
    }

    private void insertAttributeToRequest(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : this.requestAttribute.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    private void insertAttributeToSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        for (Map.Entry<String, Object> entry : this.sessionAttribute.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    private void extractRequestParameters(HttpServletRequest request) {
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            this.requestParameters.put(entry.getKey(), entry.getValue());
        }
    }

    private void extractRequestAttribute(HttpServletRequest request) {
        Enumeration<String> nameAttributes = request.getAttributeNames();
        while (nameAttributes.hasMoreElements()) {
            String name = nameAttributes.nextElement();
            Object value = request.getAttribute(name);
            this.requestAttribute.put(name, value);
        }
    }

    private void extractSessionAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> nameSessionAttribute = session.getAttributeNames();
        while (nameSessionAttribute.hasMoreElements()) {
            String name = nameSessionAttribute.nextElement();
            Object value = session.getAttribute(name);
            this.sessionAttribute.put(name, value);
        }
    }
}
