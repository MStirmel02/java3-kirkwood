<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css">
    <link rel="stylesheet" type="text/css" href="${appURL}/css/project.css">
    <link rel="stylesheet" type="text/css" href="${appURL}/css/loading.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.js"></script>
    <script src="${appURL}/js/loading.js"></script>
<!--
<style>
    <%@include file="../../css/project.css" %>
    <%@include file="../../css/loading.css" %>
</style>
-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <button class="nav-link" href="#">Log in</button>
            </li>
            <li class="nav-item">
                <button class="nav-link" href="#">Create account</button>
            </li>
        </ul>
    </div>
</nav>