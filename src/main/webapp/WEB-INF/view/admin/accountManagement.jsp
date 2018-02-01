<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="messages" />

<!DOCTYPE HTML>
<head>
    <meta charset="UTF-8">
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
            ${msg}
        </div>
    </c:if>
    <c:if test="${param.success!= null}">
        <div id="success" class="success" style="max-width: 40%; padding-top: 20px">
            ${msg}
        </div>
    </c:if>

    <div class="row justify-content-md-center" style="text-align: center">

        <div id="account-info" class="col-sm-10 col-md-8 col-lg-8" style="padding: 30px">

            <div class="card">
                <div class="card-header card-info">
                    Zarządzanie kontami
                </div>
                <div class="card-block justify-content-md-center">

                    <form action="accountManagement" method="GET">
                        <table class="table justify-content-md-center">
                            <tr>
                                <td><b>Wyszukaj użytkownika po nazwie: </b></td>
                                <td><input type="text" class="form-control" name="username" placeholder="Nazwa użytkownika"/></td>
                                <td><button class='btn btn-primary' type="submit" style="margin: 5px">
                                    Szukaj
                                    <div class='fa fa-search'></div>
                                </button></td>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </tr>
                        </table>
                    </form>

                    <c:if test="${param.username == null}">


                    <div id="users" class="row justify-content-md-center table-responsive table-body" style="padding: 10px; margin-bottom: 10px">
                            <table class="table justify-content-md-center" width="100%">
                                <tr  style="text-align: center; padding: 5px; border-bottom: 1px solid gray">
                                    <th style="width: 30%; text-align: center">
                                        Użytkownik
                                    </th>
                                    <th style="width: 30%; text-align: center">
                                        Rola
                                    </th>
                                    <th style="width: 40%; text-align: center">
                                        Opcje
                                    </th>
                                </tr>
                                <c:forEach var="user" items="${userList}">
                                    <tr style="min-width: 100%; padding: 5px; border-bottom: 1px solid gray">
                                        <td>
                                            ${user.username}
                                        </td>
                                        <td>
                                            <c:forEach var="role" items="${user.roles}">
                                                ${role}
                                            </c:forEach>
                                        </td>
                                        <td class="justify-content-md-center">
                                            <div class="btn-group">
                                            <form action="" method="GET">
                                            <button name="username" class='btn btn-primary' type="submit" style="margin: 5px"
                                                    value='${user.username}'>
                                                Modyfikuj
                                            </button>
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            </form>

                                            <form action="accountManagement/delete" method="POST">
                                                <button name="username" class='btn btn-primary' type="submit" style="margin: 5px"
                                                        value='${user.username}'>
                                                    Usuń konto
                                                </button>
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            </form>
                                            </div>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </table>
                    </div>
                    </c:if>

                    </div>
                    <c:if test="${param.username != null && chosenUser != null}">
                        <div id="userData">
                            <h4>Modyfikowane konto: </h4>
                            <b>Nazwa użytkownika:</b> ${chosenUser.username}
                            <b>Email:</b> ${chosenUser.email} <br>
                            <b>Imiona:</b> ${chosenUser.firstName} ${chosenUSer.secondName}
                            <b>Nazwisko: </b> ${chosenUser.lastName} <br>
                            <b>Uprawnienia: </b>
                                <c:forEach var="role" items="${chosenUser.roles}">
                                    ${role}
                                </c:forEach>
                            <br>
                                <c:if test="${chosenUser.roles.contains('Student')}">
                                    <b>Numer indeksu: </b> ${chosenUser.studentBookNo}
                                </c:if>
                                <c:if test="${chosenUser.roles.contains('Administrator') || chosenUser.roles.contains('Przedstawiciel władz')}">
                                    <b>Numer pokoju: </b> ${chosenUser.roomNo}
                                </c:if>
                                <c:if test="${chosenUser.roles.contains('Administrator')}">
                                    <b>Numer pokoju: </b> ${chosenUser.telephoneNo}
                                </c:if>
                                <c:if test="${chosenUser.roles.contains('Pracownik administracyjny')}">
                                    <b>Stanowisko: </b> ${chosenUser.function}
                                </c:if>
                                <%--teacher--%>
                                <c:if test="${chosenUser.roles.contains('Pracownik naukowy')}">
                                    <b>Numer wewnętrzny: </b> ${chosenUser.internalNo}
                                </c:if>
                        </div>
                        <hr>
                        <div id="modifyData" style="padding-bottom: 10px">
                            <c:import url="modifyUser.jsp"></c:import>
                        </div>
                        <h4>Resetowanie hasła</h4>
                        <div id="reset">
                            <form action="accountManagement/resetPassword" method="POST">
                                <table class="table justify-content-md-center">
                                    <tr>
                                        <td>
                                <b>Miejsce urodzenia: </b>
                                        </td>
                                        <td>
                                <input type="text" class="form-control" name="placeOfBirth" placeholder="Miejsce urodzenia"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                <b>Numer dokumentu tożsamości: </b>
                                        </td>
                                        <td>
                                <input type="text" class="form-control" name="idCardNo" placeholder="Numer dokumentu tożsamości"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                <button class='btn btn-primary' type="submit" style="margin: 5px; float: right" name="username"
                                        value="${chosenUser.username}">
                                    Resetuj hasło
                                </button>
                                        </td>
                                    </tr>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </table>
                            </form>
                        </div>
                    </c:if>
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