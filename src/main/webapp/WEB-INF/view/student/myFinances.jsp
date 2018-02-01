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

    <div class="row justify-content-md-center" style="text-align: center">

        <div id="account-info" class="col-sm-11 col-md-10 col-lg-10" style="padding: 30px">

            <div class="card">
                <div class="card-header card-info">
                    Moje zobowiązania finansowe
                </div>
                <div class="card-block justify-content-md-center">

                    <div id="obligationList" class="row justify-content-md-center table-responsive table-body" style="padding: 10px; margin-bottom: 10px">
                        <table class="table justify-content-md-center" width="100%">
                            <tr  style="text-align: center; padding: 5px; border-bottom: 1px solid gray">
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
                            <c:forEach var="obligation" items="${allMyFinances}">
                                <tr style="min-width: 100%; padding: 5px; border-bottom: 1px solid gray">
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