<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="utf-8">
    <title>Team management</title>
</head>

<my:pagetemplate>
<jsp:attribute name="body">
    <div class="container">
        <div class="row">
            <div class="col">
                <h2>My team</h2>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Offense</th>
                        <th scope="col">Defense</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${players}" var="player">
                        <tr>
                            <td>${player.firstName}&nbsp;${player.lastName}</td>
                            <td>${player.offensiveStrength}</td>
                            <td>${player.defensiveStrength}</td>
                            <td align="right">
                                <my:a href="/team/remove/${player.id}" class="btn btn-danger btn-sm ms-auto">
                                    Remove from my team
                                </my:a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col">
                <h2>Free agents</h2>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Offense</th>
                        <th scope="col">Defense</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${freeAgents}" var="freeAgent">
                            <tr>
                                <td>${freeAgent.firstName}&nbsp;${freeAgent.lastName}</td>
                                <td>${freeAgent.offensiveStrength}</td>
                                <td>${freeAgent.defensiveStrength}</td>
                                <td align="right">
                                    <my:a href="/player/recruit/${freeAgent.id}" class="btn btn-success btn-sm">
                                        Assign to my team
                                    </my:a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</jsp:attribute>
</my:pagetemplate>
