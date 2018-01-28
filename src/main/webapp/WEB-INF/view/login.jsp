<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="messages" />

<!DOCTYPE HTML>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">

    <!-- webfonts -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <!-- style -->
    <link rel="stylesheet" href="<c:url value='../resources/style.css'/>" type="text/css">

</head>
<body>
<%@ include file="toolbar.jsp" %>
<center>

    <img src="<c:url value='../resources/images/logo_WEiTI.JPG'/>" style="max-width: 10%" alt="LOGO WEITI"/>
<div id='login-panel' style='min-width: 40%; padding: 20px;'>
    <h3 style='padding: 30px'>Zaloguj się</h3>
    <form name='loginForm' class="form-group" action="login" method='POST'>
        <table>
            <tr>
                <th style='padding: 10px; width:20%' align=right >Użytkownik:</td>
                <td><input type='text' class="form-control" name='username' placeholder='Login' value='' autofocus></td>
            </tr>
            <tr>
                <th style='padding: 10px' align=right>Hasło:</td>
                <td><input type='password' class="form-control" name='password' placeholder='Hasło' /></td>
                <td>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </td>
            </tr>
            <tr>
                <td colspan=2 style='padding: 10px'>
                    <input name="submit" class='btn btn-primary float-right' type="submit" value='Zatwierdź'/>
                </td>
            </tr>
        </table>
        <c:if test="${param.error != null}">
            <div id="error" class="error" style="max-width: 40%">
                Błędne dane logowania
            </div>
        </c:if>
    </form>
</div>
</center>

<%@ include file="footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>