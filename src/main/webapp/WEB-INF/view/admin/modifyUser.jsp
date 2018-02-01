<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<form name='modify' class="form-group justify-content-md-center" action="accountManagement/modify" method='post'>
    <table class="justify-content-md-center" style="width: 100%">
            <tr>
                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Email:</td>
                <td><input type='email' class="form-control" name='email' placeholder='Email' value="${chosenUser.email}" /></td>
            </tr>
            <tr>
                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Pierwsze imię:</td>
                <td><input type='text' class="form-control" name='firstName' placeholder='Pierwsze imię' value="${chosenUser.firstName}"/></td>
            </tr>
            <tr>
                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Drugie imię:</td>
                <td><input type='text' class="form-control" name='secondName' placeholder='Drugie imię' value="${chosenUser.secondName}" /></td>
            </tr>
            <tr>
                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Nazwisko:</td>
                <td><input type='text' class="form-control" name='lastName' placeholder='Nazwisko' value="${chosenUser.lastName}"/></td>
            </tr>
        <%--administrator--%>
        <c:if test="${chosenUser.roles.contains('Adminstrator') || chosenUser.roles.contains('Przedstawiciel władz')}">
            <tr>
                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer pokoju:</td>
                <td><input type='text' class="form-control" name='roomNo' placeholder='Numer pokoju' value="${chosenUser.roomNo}" /></td>
            </tr>
        </c:if>
        <c:if test="${chosenUser.roles.contains('Administrator')}">
            <tr>
                <th style="padding: 10px; width:30%; margin-left: 15%" align=right>Numer telefonu:</td>
                <td><input type="text" class="form-control" name='telephoneNo' placeholder='Numer telefonu' value="${chosenUser.telephoneNo}"/></td>
            </tr>
        </c:if>
        <%--office employee--%>
        <c:if test="${chosenUser.roles.contains('Pracownik administracyjny')}">
            <tr>
            <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Stanowisko:</td>
            <td><input type='text' class="form-control" name='function' placeholder='Stanowisko' value="${chosenUser.function}"/></td>
            </tr>
        </c:if>
        <%--teacher--%>
        <c:if test="${chosenUser.roles.contains('Pracownik naukowy')}">
            <tr>
                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer wewnętrzny:</td>
                <td><input type='text' class="form-control" name='internalNo' placeholder='Numer wewnętrzny' value="${chosenUser.internalNo}"/></td>
            </tr>
        </c:if>
        <%--csrf--%>
        <tr>
            <td>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="role" value="${_csrf.token}"/>
            </td>
        </tr>
            <tr>
                <td colspan="2">
            <button class='btn btn-primary' type="submit" style="margin: 5px; float: right" name="username"
            value="${chosenUser.username}">
                Wprowadź zmiany
            </button>
                </td>
            </tr>
    </table>
</form>
