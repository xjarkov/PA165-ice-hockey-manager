<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
  <meta charset="utf-8">
  <title>Test User Detail</title>
</head>
<my:pagetemplate>
<jsp:attribute name="body">
    <h2>${user.name}</h2>
    <table class="table table-hover table-bordered">
      <tr>
        <td>Id</td>
        <td>${user.id}</td>
      </tr>
      <tr>
        <td>Nickname</td>
        <td><c:out value="${user.name}"/></td>
      </tr>
      <tr>
        <td>Email</td>
        <td><c:out value="${user.email}"/></td>
      </tr>
      <tr>
        <td>Team</td>
        <td><c:out value="${user.team.name}"/></td>
      </tr>
    </table>
</jsp:attribute>
</my:pagetemplate>

