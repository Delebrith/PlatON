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

        <div id="account-info" class="col-sm-11 col-md-10 col-lg-10" style="padding: 30px">

            <div class="card">
                <div class="card-header card-info">
                    Zarządzanie zobowiązaniami finansowymi
                </div>
                <div class="card-block justify-content-md-center">

                    <table class="table justify-content-md-center">
                        <tr>
                            <form action="find" method="GET">
                                <td><b>Wyszukaj studenta po numerze indeksu: </b></td>
                                <td><input type="number" class="form-control" name="studentBookNo" placeholder="Numer indeksu"/></td>
                                <td><button class='btn btn-primary' type="submit" style="margin: 5px">
                                    Szukaj
                                    <div class='fa fa-search'></div>
                                </button></td>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <form action="find" method="GET">
                                <td><button class='btn btn-primary' type="submit" style="margin: 5px">
                                    Pokaż wszystkie
                                </button></td>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <form action="add" method="GET">
                                <td><button class='btn btn-primary' type="submit" style="margin: 5px">
                                    Dodaj zobowiązanie
                                </button></td>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </tr>
                    </table>

                    <div id="users" class="row justify-content-md-center table-responsive table-body" style="padding: 10px; margin-bottom: 10px">
                        <br><br>
                        <h5>Lista zobowiązań finansowych</h5>
                            <table class="table justify-content-md-center" width="100%">
                                <tr  style="text-align: center; padding: 5px; border-bottom: 1px solid gray">
                                    <th style="text-align: center">
                                        Student
                                    </th>
                                    <th style="text-align: center">
                                        Numer indeksu
                                    </th>
                                    <th style="text-align: center">
                                        Typ
                                    </th>
                                    <th style="text-align: center">
                                        Wysokość
                                    </th>
                                    <th style="text-align: center">
                                        Numer konta
                                    </th>
                                    <th style="text-align: center">
                                        Termin wpłaty/wypłaty
                                    </th>
                                </tr>
                                <c:forEach var="obligation" items="${foundObligations}">
                                    <tr style="min-width: 100%; padding: 5px; border-bottom: 1px solid gray">
                                        <td>
                                            ${obligation.studentName}
                                        </td>
                                        <td>
                                            ${obligation.studentBookNo}
                                        </td>
                                        <td>
                                            ${obligation.type}
                                        </td>
                                        <td>
                                             ${obligation.amount}
                                        </td>
                                        <td>
                                                ${obligation.accountNo}
                                        </td>
                                        <td>
                                                ${obligation.dueDate}
                                        </td>
                                        <td class="justify-content-md-center">
                                            <div class="btn-group">

                                            <form action="delete" method="POST">
                                                <button name="id" class='btn btn-primary' type="submit" style="margin: 5px"
                                                        value='${obligation.id}'>
                                                    Usuń
                                                </button>
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            </form>
                                            </div>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </table>
                    </div>


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