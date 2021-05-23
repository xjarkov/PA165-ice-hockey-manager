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
    <div class="alert alert-warning" role="alert">
        Please select your team to continue.
    </div>
    <c:if test="${not empty team_has_manager}">
        <div class="alert alert-danger" role="alert"><c:out value="${team_has_manager}"/></div>
    </c:if>
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Championship</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teams}" var="team">
            <tr>
                <td><my:a href="/team/${team.id}"><c:out value="${team.name}"/></my:a></td>
                <td><c:out value="${team.championship}"/></td>
                <td align="right">
                    <my:a href="/team/${team.id}/select" class="btn btn-primary btn-sm">Choose</my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
