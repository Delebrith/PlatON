<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setBundle basename="messages" />
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
    <div id="error">
        Nastąpił błąd w czasie wylogowywania
    </div>
</c:if>
<c:if test="${param.logoutSuccess == true}">
    <div id="success">
        Pomyślnie wylogowano!
    </div>
</c:if>
<html>
<head>
    <title>Logged Out</title>
</head>
<body>
<a href="/login">Login</a>
</body>
</html>