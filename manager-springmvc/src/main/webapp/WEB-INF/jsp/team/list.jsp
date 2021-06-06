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
    <a href="${pageContext.request.contextPath}/team/new">
        <button class="btn btn-primary">New team</button>
    </a>
    <table class="table table-hover table-striped table-bordered">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Championship</th>
            <th scope="col">Manager</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teams}" var="team">
            <tr>
                <td><my:a href="/team/${team.id}"><c:out value="${team.name}"/></my:a></td>
                <td><c:out value="${team.championship}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${team.manager != null}">
                            <my:a href="/user/${team.manager.id}"><c:out value="${team.manager.name}"/></my:a>
                        </c:when>
                        <c:otherwise>
                            No manager
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>

