package org.chervyakovsky.jobsearch.model.mapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestContent {
    private HashMap<String, Object> requestAttribute;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttribute;

    public RequestContent() {
        this.requestAttribute = new HashMap<>();
        this.requestParameters = new HashMap<>();
        this.sessionAttribute = new HashMap<>();
    }

    public HashMap<String, Object> getRequestAttribute() {
        return this.requestAttribute;
    }

    public void setRequestAttribute(HashMap<String, Object> requestAttribute) {
        this.requestAttribute = requestAttribute;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return this.requestParameters;
    }

    public void setRequestParameters(HashMap<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public HashMap<String, Object> getSessionAttribute() {
        return this.sessionAttribute;
    }

    public void setSessionAttribute(HashMap<String, Object> sessionAttribute) {
        this.sessionAttribute = sessionAttribute;
    }

    public void extractValues(HttpServletRequest request) {
        extractRequestParameters(request);
        extractRequestAttribute(request);
        extractSessionAttribute(request);
    }

    public void insertAttribute(HttpServletRequest request) {
        insertAttributeToRequest(request);
        insertAttributeToSession(request);
    }

    public void setNewValueInRequestAttributes(String nameAttribute, Object value) {
        this.requestAttribute.put(nameAttribute, value);
    }

    public void setNewValueInSessionAttribute(String nameAttribute, Object value) {
        this.sessionAttribute.put(nameAttribute, value);
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
