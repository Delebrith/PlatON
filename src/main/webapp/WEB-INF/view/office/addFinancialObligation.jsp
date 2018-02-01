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
                    Dodaj zobowiązanie finansowe
                </div>

                <form action="find" method="GET">
                    <button class='btn btn-primary' type="submit" style="margin: 15px; float: left">
                        Wróć do listy
                    </button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>

                <div class="card-block justify-content-md-center">
                    <form name='addObligationForm' class="form-group justify-content-md-center" action="add" method='post'>
                        <table class="justify-content-md-center" style="width: 100%">
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer indeksu:</td>
                                <td><input type='number' class="form-control" name='studentBookNo' placeholder='Numer indeksu' autofocus /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Typ:</td>
                                <td><input type='text' class="form-control" name='type' placeholder='Typ zobowiązania' /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Wysokość zobowiązania:</td>
                                <td><input type='number' class="form-control" name='amount' placeholder='Kwota' /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Numer konta:</td>
                                <td><input type='text' class="form-control" name='accountNo' placeholder='Numer konta' /></td>
                            </tr>
                            <tr>
                                <th style='padding: 10px; width:30%; margin-left: 15%' align=right>Termin opłacenia:</td>
                                <td><input type='date' class="form-control" name='dueDate' placeholder="Termin" /></td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan=2 style='padding: 10px'>
                                    <input class='btn btn-primary float-right' type="submit" value='Zatwierdź'/>
                                </td>
                            </tr>
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