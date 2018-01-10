<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div id="toolbar">
    <div id="logo">
        <button>PlatON</button>
    </div>
    <div id="buttons">
        <security:authorize access="hasRole('ROLE_ADMIN')"><button id="admin-options">Admin</button></security:authorize>
        <security:authorize access="hasRole('ROLE_STUDENT')"><button id="student-options">Student</button></security:authorize>
        <button id="annonymous-options">Gość</button>
    </div>
</div>