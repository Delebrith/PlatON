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
                    Moje przedmioty
                </div>
                <div class="card-block justify-content-md-center">

                    <div id="realisations" class="row justify-content-md-center table-responsive table-body" style="padding: 10px; margin-bottom: 10px">
                        <br><br>
                        <h5>Realizacje przedmiotów</h5>
                        <table class="table justify-content-md-center" width="100%">
                            <tr  style="text-align: center; padding: 5px; border-bottom: 1px solid gray">
                                <th style="text-align: center">
                                    Kod przedmiotu
                                </th>
                                <th style="text-align: center">
                                    Realizacja
                                </th>
                                <th style="text-align: center">
                                    Nazwa
                                </th>
                                <th style="text-align: center">
                                    ECTS
                                </th>
                                <th style="text-align: center">
                                    Zaliczenie
                                </th>
                                <th style="text-align: center">
                                    Semestr
                                </th>
                                <th style="text-align: center">
                                    Stan
                                </th>
                            </tr>
                            <c:forEach var="subject" items="${subjects}">
                                <tr style="min-width: 100%; padding: 5px; border-bottom: 1px solid gray">
                                    <td>
                                            ${subject.subjectCode}
                                    </td>
                                    <td>
                                            ${subject.realCode}
                                    </td>
                                    <td>
                                            ${subject.subjectName}
                                    </td>
                                    <td>
                                            ${subject.ects}
                                    </td>
                                    <td>
                                            ${subject.passMethod}
                                    </td>
                                    <td>
                                            ${subject.semCode}
                                    </td>
                                    <td>
                                            ${subject.enrollStatus}
                                    </td>
                                    <td class="justify-content-md-center">
                                        <div class="btn-group">

                                            <form name="dismissEnroll" action="subjects" method="POST">
                                                <input type="hidden" name="code" value="${subject.realCode}"/>
                                                <input type="hidden" name="subjectCode" value="${subject.subjectCode}"/>
                                                <input type="hidden" name="semesterCode" value="${subject.semCode}"/>
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                <button name="id" class='btn btn-primary' type="submit" style="margin: 5px">
                                                    Usuń
                                                </button>
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