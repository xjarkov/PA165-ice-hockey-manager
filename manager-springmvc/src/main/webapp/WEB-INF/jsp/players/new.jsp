<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
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
        <form:form method="post" action="${pageContext.request.contextPath}/players/add"
                   modelAttribute="hockeyPlayerCreateDto" cssClass="form-horizontal">
        <div class="form-group ${firstName_error?'has-error':''}">
            <form:label path="firstName" cssClass="col-sm-2 control-label">First Name</form:label>
            <div class="col-sm-10">
                <form:input path="firstName" cssClass="form-control"/>
                <form:errors path="firstName" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${lastName_error?'has-error':''}">
            <form:label path="lastName" cssClass="col-sm-2 control-label">Last Name</form:label>
            <div class="col-sm-10">
                <form:input path="lastName" cssClass="form-control"/>
                <form:errors path="lastName" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${offensiveStrength_error?'has-error':''}">
            <form:label path="offensiveStrength" cssClass="col-sm-2 control-label">Offensive Strength</form:label>
            <div class="col-sm-10">
                <form:input path="offensiveStrength" cssClass="form-control"/>
                <form:errors path="offensiveStrength" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${defensiveStrength_error?'has-error':''}" >
            <form:label path="defensiveStrength" cssClass="col-sm-2 control-label">Defensive Strength</form:label>
            <div class="col-sm-10">
                <form:input path="defensiveStrength" cssClass="form-control"/>
                <form:errors path="defensiveStrength" cssClass="help-block"/>
            </div>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Create player</button>
        </div>
    </form:form>

</jsp:attribute>
</my:pagetemplate>