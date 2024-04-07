<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html lang="en" class="data-bs-theme-dark">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css">
    <link rel="stylesheet" type="text/css" href="${appURL}/css/project.css">
    <link rel="stylesheet" type="text/css" href="${appURL}/css/loading.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.js"></script>
    <script src="${appURL}/js/loading.js"></script>

</head>
    <header class="p-3 text-bg-dark border-bottom border-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="${appURL}/home" class="nav-link px-2 text-white">Home</a></li>
                </ul>

                <div class="text-end">
                    <c:choose>
                        <c:when test="${empty sessionScope.activeUser}">
                            <a href="${appURL}/login" class="btn btn-outline-light me-2">Log In</a>
                            <a href="${appURL}/signingup" class="btn btn-warning">Sign Up</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${appURL}/signingout" class="btn btn-outline-danger me-2">Sign out</a>
                            <a href="${appURL}/profile" class="btn btn-secondary"><c:out value="${sessionScope.activeUser.getUserID()}" /></a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </header>