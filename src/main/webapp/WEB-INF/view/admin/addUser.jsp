<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="messages" />

<!DOCTYPE HTML>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">

    <!-- webfonts -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <!-- style -->
    <link rel="stylesheet" href="<c:url value='../../resources/style.css'/>" type="text/css">

</head>
<body>
<%@ include file="../toolbar.jsp" %>

<center>

    <c:if test="${param.error != null}">
        <div id="error" class="error" style="max-width: 40%; padding-top: 20px">
            Dodawanie użytkownika nie powiodło się
        </div>
    </c:if>
    <c:if test="${param.success!= null}">
        <div id="success" class="success" style="max-width: 40%; padding-top: 20px">
            Dodawanie użytkownika powiodło się!
        </div>
    </c:if>

    <div class="row justify-content-md-center" style="text-align: center">

        <div id="account-info" class="col-sm-10 col-md-8 col-lg-8" style="padding: 30px">

            <div class="card">
                <div class="card-header card-info">
                    Dodaj nowego użytkownika
                </div>
                <div class="card-block justify-content-md-center">
                    <h5>Wybierz rolę</h5>
                    <div id="buttons" class="row justify-content-md-center" style="padding: 10px; margin-bottom: 10px">
                        <c:forEach var="role" items="${possibleRoles}">
                            <form action="addUser" method="GET">
                            <button name="role" class='btn btn-primary float-right' type="submit" style="margin: 5px"
                                    value='${role.name}'>${role.displayName}</button>
                            </form>
                        </c:forEach>
                    </div>
                    <form name='addUserForm' class="form-group justify-content-md-center" action="addUser" method='post'>
                        <table class="justify-content-md-center" style="width: 100%">
                            <c:if test="${param.role == 'ROLE_TEACHER' || param.role == 'ROLE_ADMIN' || param.role == 'ROLE_AUTHORITY'
                             || param.role == 'ROLE_OFFICE'  || param.role == 'ROLE_STUDENT'}">
                            <%--<tr>--%>
                                <%--<th style='padding: 10px; width:30%; margin-left: 15%' align=right >Nazwa użytkownika:</td>--%>
                                <%--<td><input type='text' class="form-control" name='username'--%>
                                           <%--placeholder='Nazwa użytkownika' value='' autofocus></td>--%>
                            <%--</tr>--%>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Email:</td>
                                <td><input type='email' class="form-control" name='email' placeholder='Email' autofocus /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Pierwsze imię:</td>
                                <td><input type='text' class="form-control" name='firstName' placeholder='Pierwsze imię' /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Drugie imię:</td>
                                <td><input type='text' class="form-control" name='secondName' placeholder='Drugie imię' /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Nazwisko:</td>
                                <td><input type='text' class="form-control" name='lastName' placeholder='Nazwisko' /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Miejsce urodzenia:</td>
                                <td><input type='text' class="form-control" name='placeOfBirth' placeholder='Miejsce urodzenia' /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer dokumentu tożsamości:</td>
                                <td><input type='text' class="form-control" name='idCardNo' placeholder='Numer dokumentu tożsamości' /></td>
                            </tr>
                            </c:if>
                            <%--student--%>
                            <c:if test="${param.role == 'ROLE_STUDENT'}">
                            <tr id="indexNo">
                                <%--<th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer indeksu:</td>--%>
                                <%--<td><input type='text' class="form-control" name='indexNo' placeholder='Numer indeksu' /></td>--%>
                                <%--<td>--%>
                                    <input type="hidden" name="role" value="ROLE_STUDENT"/>
                                </td></tr>
                            </c:if>
                            <%--administrator--%>
                            <c:if test="${param.role == 'ROLE_ADMIN' || param.role == 'ROLE_AUTHORITY'}">
                            <tr id="roomNo">
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer pokoju:</td>
                                <td><input type='text' class="form-control" name='roomNo' placeholder='Numer pokoju' /></td>
                            </tr>
                            </c:if>
                            <c:if test="${param.role == 'ROLE_ADMIN'}">
                            <tr id="telephoneNo">
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer telefonu:</td>
                                <td><input type='text' class="form-control" name='telephoneNo' placeholder='Numer telefonu' /></td>
                                <td>
                                    <input type="hidden" name="role" value="ROLE_ADMIN"/>
                                </td></tr>
                            </c:if>
                            <%--office employee--%>
                            <c:if test="${param.role == 'ROLE_OFFICE'}">
                            <tr id="function">
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Stanowisko:</td>
                                <td><input type='text' class="form-control" name='function' placeholder='Stanowisko' /></td>
                                <td>
                                    <input type="hidden" name="role" value="ROLE_OFFICE"/>
                                </td>
                            </tr>
                            </c:if>
                            <%--authority--%>
                            <c:if test="${param.role == 'ROLE_AUTHORITY'}">
                                    <td>
                                        <input type="hidden" name="role" value="ROLE_AUTHORITY"/>
                                    </td>
                            </c:if>
                            <%--teacher--%>
                            <c:if test="${param.role == 'ROLE_TEACHER'}">
                            <tr id="internalNo">
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer wewnętrzny:</td>
                                <td><input type='text' class="form-control" name='internalNo' placeholder='Numer wewnętrzny' /></td>
                                <td>
                                    <input type="hidden" name="role" value="ROLE_TEACHER"/>
                                </td>
                            </tr>

                            </c:if>
                            <%--csrf--%>
                            <tr>
                                <td>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </td>
                            </tr>
                            <c:if test="${param.role == 'ROLE_TEACHER' || param.role == 'ROLE_ADMIN' || param.role == 'ROLE_AUTHORITY'
                             || param.role == 'ROLE_OFFICE'  || param.role == 'ROLE_STUDENT'}">

                            <tr>
                                <td colspan=2 style='padding: 10px'>
                                    <input class='btn btn-primary float-right' type="submit" value='Zatwierdź'/>
                                </td>
                            </tr>
                            </c:if>
                        </table>

                    </form>
                </div>
            </div>
        </div>
    </div>

</center>

<%@ include file="../footer.jsp" %>


<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>