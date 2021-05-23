<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="utf-8">
    <title>Ice Hockey Manager</title>
</head>

<my:pagetemplate>
<jsp:attribute name="body">
<main>
    <h1>Welcome to Ice Hockey Manager</h1>
    <p class="fs-5 col-md-8">Ice Hockey Manager allows you to manage your own ice hockey team. You will became coach of any of the european ice hockey teams and manage them!</p>

    <div class="mb-5">
        <a href="${pageContext.request.contextPath}/team/list" class="btn btn-primary btn-lg px-4">Show me all teams</a>
    </div>
</main>
</jsp:attribute>
</my:pagetemplate>
