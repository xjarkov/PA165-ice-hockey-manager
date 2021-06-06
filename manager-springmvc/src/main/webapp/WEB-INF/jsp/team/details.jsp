<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <meta charset="utf-8">
    <title>Team details</title>
</head>
<my:pagetemplate>
<jsp:attribute name="body">
    <h2>${team.name}</h2>
    <table class="table table-hover table-bordered">
        <tr>
            <td>Name</td>
            <td><c:out value="${team.name}"/></td>
        </tr>
        <tr>
            <td>Championship</td>
            <td><c:out value="${team.championship}"/></td>
        </tr>
        <tr>
            <td>Manager</td>
            <td><c:out value="${team.manager.name}"/></td>
        </tr>
        <tr>
            <td>Players</td>
            <td>
                <table class="table">
                    <thead>
                        <tr>
                            <td scope="col">Name</td>
                            <td scope="col">Off</td>
                            <td scope="col">Deff</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${team.hockeyPlayers}" var="player">
                            <tr>
                                <td>${player.fullName}</td>
                                <td>${player.offensiveStrength}</td>
                                <td>${player.defensiveStrength}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </td>
        </tr>
    </table>
</jsp:attribute>
</my:pagetemplate>
