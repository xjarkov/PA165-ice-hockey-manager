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
    <form:form id="matchCreate" action="${pageContext.request.contextPath}/match/admin/create"
               modelAttribute="matchCreate" method="POST">
      <div class="row justify-content-md-center">
        <div class="col col-lg-4">
            <div class="form-floating mb-3">
                <form:select path="homeTeam" id="homeTeam" class="form-select" aria-label="Home team">
                    <c:forEach items="${teams}" var="team">
                        <form:option value="${team.id}" label="${team.name}"/>
                    </c:forEach>
                </form:select>
                <label for="homeTeam">Home team</label>
                <form:errors path="homeTeam" cssClass="help-block"/>
            </div>

            <div class="form-floating mb-3">
                <form:select path="visitingTeam" id="visitingTeam" class="form-select" aria-label="Visiting team">
                    <c:forEach items="${teams}" var="team">
                        <form:option value="${team.id}" label="${team.name}"/>
                    </c:forEach>
                </form:select>
                <label for="visitingTeam">Home team</label>
                <form:errors path="visitingTeam" cssClass="help-block"/>
            </div>

            <div class="form-floating mb-3">
                <form:input type="number" min="0" class="form-control" id="homeTeamScore" path="homeTeamScore"/>
                <label for="homeTeamScore">Home team score</label>
                <form:errors path="homeTeamScore" cssClass="help-block"/>
            </div>

            <div class="form-floating mb-3">
                <form:input type="number" min="0" class="form-control" id="visitingTeamScore" path="visitingTeamScore"/>
                <label for="visitingTeamScore">Visiting team score</label>
                <form:errors path="visitingTeamScore" cssClass="help-block"/>
            </div>

            <div class="form-floating mb-3">
                <form:input type="datetime-local" class="form-control" id="dateTime" path="dateTime"/>
                <label for="dateTime">Date and time</label>
                <form:errors path="dateTime" cssClass="help-block"/>
            </div>

            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-primary" type="submit">Create match</button>
            </div>
    </form:form>
        </div>
        </div>
</jsp:attribute>
</my:pagetemplate>
