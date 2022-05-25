<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="org.chervyakovsky.jobsearch.controller.ParameterName" %>
<%@ page import="org.chervyakovsky.jobsearch.model.entity.status.UserRoleStatus" %>
<%@include file="fragment/lang_and_user_role.jsp" %>

<html>
<head>
    <%@ include file="fragment/head.jsp" %>
    <title><fmt:message key="title.registration"/></title>
</head>

<body>
<div class="wrapper">
    <header class="header">
        <%@include file="fragment/header.jsp" %>
    </header>

    <main class="main">



        <div class="w3-auto w3-padding" style="max-width:600px">
            <div class="w3-center">
                <h5><fmt:message key="text.registration.page"/></h5>
            </div>
        </div>


            <div class="w3-auto" style="max-width:600px">
                <div class="w3-center">
                    <button id="buttonCompany"
                            onclick="clickFormEvent('company')"
                            class="w3-button w3-xlarge w3-block w3-light-green">
                        <h1><fmt:message key="registration.button.enter.company"/></h1>
                    </button>

                    <button id="buttonWorker"
                            onclick="clickFormEvent('worker')"
                            class="w3-button w3-xlarge w3-block w3-green">
                        <h1><fmt:message key="registration.button.enter.worker"/></h1>
                    </button>
                </div>
            </div>



            <!--WORKER FORM -->
            <div id="worker" style="display: none">
                <%@include file="fragment/WorkerRegistration.jsp" %>
            </div>
            <!--COMPANY FORM -->
            <div id="company" style="display: none">
                <%@include file="fragment/CompanyRegistration.jsp" %>
            </div>

    </main>
    <footer class="footer">
        <%@include file="fragment/footer.jsp" %>
    </footer>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/js.js"></script>
</body>
</html>


