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
    private HttpSession session;

    public RequestContent() {
        this.requestAttribute = new HashMap<>();
        this.requestParameters = new HashMap<>();
        this.sessionAttribute = new HashMap<>();
    }

    public HashMap<String, Object> getRequestAttribute() {
        return this.requestAttribute;
    }


    public HashMap<String, String[]> getRequestParameters() {
        return this.requestParameters;
    }

    public HashMap<String, Object> getSessionAttribute() {
        return this.sessionAttribute;
    }


    public HttpSession getSession() {
        return session;
    }

    public void extractValues(HttpServletRequest request) {
        extractRequestParameters(request);
        extractRequestAttribute(request);
        extractSessionAttribute(request);
        this.session = request.getSession(false);
    }

    public void insertAttribute(HttpServletRequest request) {
        insertAttributeToRequest(request);
        insertAttributeToSession(request);
    }

    public void setParameterInAttribute(){
        for (Map.Entry<String, String[]> entry : this.requestParameters.entrySet()) {
            this.requestAttribute.put(entry.getKey(), entry.getValue()[0]);
        }
    }

    public void setNewValueInRequestAttributes(String nameAttribute, Object value) {
        this.requestAttribute.put(nameAttribute, value);
    }

    public void setNewValueInSessionAttribute(String nameAttribute, Object value) {
        this.sessionAttribute.put(nameAttribute, value);
    }

    public void removeSessionAttribute(String attributeName){
        this.session.removeAttribute(attributeName);
        this.sessionAttribute.remove(attributeName);
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
