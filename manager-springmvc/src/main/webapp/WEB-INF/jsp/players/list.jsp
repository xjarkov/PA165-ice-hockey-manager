<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="utf-8">
    <title>Free agents</title>
</head>

<my:pagetemplate>
<jsp:attribute name="body">
    <table class="table table-hover table-bordered">
        <thead>
        <tr>
            <th scope="col">Player name</th>
            <th scope="col">Offensive Strength</th>
            <th scope="col">Defensive Strength</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${players}" var="player">
            <tr>
                <td>${player.firstName}&nbsp;${player.lastName}</td>
                <td>${player.offensiveStrength}</td>
                <td>${player.defensiveStrength}</td>
                <td align="right">
                    <my:a href="/players/edit/${player.id}" class="btn btn-primary btn-sm">Edit</my:a>
                    <span></span>
                    <my:a href="/players/remove/${player.id}" class="btn btn-danger btn-sm">Remove</my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <my:a href="/players/create/" class="btn btn-success btn-sm mb-3">Add player</my:a>
</jsp:attribute>
</my:pagetemplate>

