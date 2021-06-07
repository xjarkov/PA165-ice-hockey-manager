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
    <c:if test="${authenticatedUser.admin}">
        <my:a href="/match/admin/create/" class="btn btn-success btn-sm mb-3">Create new match</my:a>
        <br>
    </c:if>
    <table class="table table-hover table-striped table-bordered">
        <thead>
        <tr>
            <th scope="col">Home team</th>
            <th scope="col">Visiting team</th>
            <th scope="col">Score</th>
            <th scope="col">Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${matches}" var="match">
            <tr>
                <td>
                    <my:a href="/team/${match.homeTeam.id}"><c:out value="${match.homeTeam.name}"/></my:a>
                    <c:if test="${match.homeTeamScore != null && match.homeTeamScore > match.visitingTeamScore}">
                        <span class="badge bg-success">WIN</span>
                    </c:if>
                </td>
                <td>
                    <my:a href="/team/${match.visitingTeam.id}"><c:out value="${match.visitingTeam.name}"/></my:a>
                    <c:if test="${match.visitingTeamScore != null && match.homeTeamScore < match.visitingTeamScore}">
                        <span class="badge bg-success">WIN</span>
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${match.visitingTeamScore != null && match.homeTeamScore != null}">
                            ${match.homeTeamScore} : ${match.visitingTeamScore}
                        </c:when>
                        <c:otherwise>
                            Not played
                             <c:if test="${authenticatedUser.admin}">
                                (<a href="${pageContext.request.contextPath}/match/admin/simulate/${match.id}">Simulate</a>)
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${match.dateFormatted()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>

