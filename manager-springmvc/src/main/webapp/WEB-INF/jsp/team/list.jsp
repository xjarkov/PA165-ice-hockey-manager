<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta charset="utf-8">
    <title>List of available teams</title>
</head>
<my:pagetemplate>
<jsp:attribute name="body">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teams}" var="team">
            <tr>
                <td><c:out value="${team.name}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>

