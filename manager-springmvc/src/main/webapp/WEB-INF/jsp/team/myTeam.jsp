<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
  <meta charset="utf-8">
  <title>List of available hockey players</title>
</head>

<my:pagetemplate>
<jsp:attribute name="body">
    <table class="table table-hover table-striped table-bordered">
      <thead>
      <tr>
        <th scope="col">Player name</th>
        <th scope="col">Offensive Strength</th>
        <th scope="col">Defensive Strength</th>
        <th scope="col">Team</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${players}" var="player">
            <tr>
              <td>${player.firstName}&nbsp;${player.lastName}</td>
              <td>${player.offensiveStrength}</td>
              <td>${player.defensiveStrength}</td>
              <td>
                    <c:choose>
                        <c:when test="${player.team == null}">
                            <c:out value="Removed from the team"/>
                        </c:when>
                        <c:otherwise>
                            <my:a href="/team/remove/${player.id}" class="btn btn-danger btn-sm">Remove from my team</my:a>
                        </c:otherwise>
                    </c:choose>
              </td>
            </tr>
        </c:forEach>
      </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>
