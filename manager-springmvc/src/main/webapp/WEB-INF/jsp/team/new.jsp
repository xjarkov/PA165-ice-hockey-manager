
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="utf-8">
    <title>New Team</title>
</head>
<my:pagetemplate>
<jsp:attribute name="body">
    <form:form id="teamCreate" action="${pageContext.request.contextPath}/team/create" modelAttribute="teamCreate" method="post">
        <form:label path="name">Name:</form:label>
        <form:input type="text" path="name" required="required"/><br>

        <form:label path="championship">Championship</form:label>
        <form:select path="championship">
            <c:forEach items="${championships}" var="championship">
                <form:option value="${championship}" label="${championship.toString()}"/>
            </c:forEach>
        </form:select><br>

        <button class="btn btn-primary" type="submit">Create</button>
    </form:form>
</jsp:attribute>
</my:pagetemplate>
</html>
