<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="utf-8">
    <title>List of available matches</title>
</head>

<my:pagetemplate>
<jsp:attribute name="body">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Home team</th>
            <th scope="col">Visiting team</th>
            <th scope="col">Score</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${matches}" var="match">
            <tr>
                <td><my:a href="/team/${match.homeTeam.id}"><c:out value="${match.homeTeam.name}"/></my:a></td>
                <td><my:a href="/team/${match.visitingTeam.id}"><c:out value="${match.visitingTeam.name}"/></my:a></td>
                <td>${match.homeTeamScore}</td>
                <td>${match.visitingTeamScore}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>

