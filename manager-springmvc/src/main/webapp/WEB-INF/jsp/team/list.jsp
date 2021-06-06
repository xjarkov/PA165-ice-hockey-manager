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
    <table class="table table-hover table-bordered">
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Championship</th>
                <th scope="col">Manager</th>
                <th scope="col"></th>
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
                    <c:if test="${authenticatedUser.admin}">
                        <td align="right">
                            <my:a href="/team/edit/${team.id}" class="btn btn-primary btn-sm">Edit</my:a>
                            <span></span>
                            <my:a href="/team/${team.id}/delete" class="btn btn-danger btn-sm">Remove</my:a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${authenticatedUser.admin}">
        <my:a href="/team/new/" class="btn btn-success btn-sm mb-3">Add team</my:a>
    </c:if>
</jsp:attribute>
</my:pagetemplate>

