<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var="language" value="${not empty language ? language : 'en'}" scope="session"></c:set>
<fmt:setLocale value="${language}"></fmt:setLocale>
<fmt:setBundle basename="translation"></fmt:setBundle>
<c:choose>
    <c:when test="${pageContext.request.scheme eq 'https'}">
        <c:set var="appURL" value="${initParam['appURLCloud']}"></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="appURL" value="${initParam['appURLLocal']}"></c:set>
    </c:otherwise>
</c:choose>

