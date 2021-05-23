<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta charset="utf-8">
    <title>New match</title>
</head>

<my:pagetemplate>
<jsp:attribute name="body">
    <form id="matchCreate" action="${pageContext.request.contextPath}/match/create" method="post">
        <label for="ht">Home team:</label>
        <select id="ht" name="homeTeam">
            <c:forEach items="${teams}" var="team">
                <option>${team.name}</option>
            </c:forEach>
        </select><br>

        <label for="vt">Visiting team:</label>
        <select id="vt" name="visitingTeam">
            <c:forEach items="${teams}" var="team">
                <option>${team.name}</option>
            </c:forEach>
        </select><br>

        <label for="hts">Home team score:</label>
        <input type="number" min="0" id="hts" name="homeTeamScore"><br>

        <label for="vts">Visiting team score:</label>
        <input type="number" min="0" id="vts" name="visitingTeamScore"><br>

        <label for="dt">Date and time:</label>
        <input type="datetime-local" id="dt" name="dateTimeDto"><br>

        <button type="submit">Create</button>
    </form>
</jsp:attribute>
</my:pagetemplate>
