<body class="text-bg-dark">
<%@include file="/WEB-INF/project/header.jsp"%>
<main class="container text-bg-dark d-flex justify-content-center" style="height: 75vh">
    <div>
        Send messages here.
    </div>
    <c:if test="${not empty EmailError}">
        <div class="invalid-feedback">
                ${EmailError}
        </div>
    </c:if>

    <c:if test="${not empty InputError}">
        <div class="invalid-feedback">
                ${InputError}
        </div>
    </c:if>


    <div id="form-main">
        <div id="form-div">
            <form class="form" action="${appURL}/massmail" method="post">
                <select name="inputEmail" id="inputEmail" class="form-select text-bg-dark">
                    <option class="text-white" value="All Emails" selected>All Emails</option>
                    <c:forEach items="${emailList}" var="email">
                        <option class="text-white" value="${email}">${email}</option>
                    </c:forEach>
                </select>

                <p class="text">
                    <textarea name="inputContent" class="" id="inputContent" placeholder="Content here..."></textarea>
                </p>


                <button type="submit" class="btn btn-warning">Send Email</button>
            </form>
        </div>
</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
</body>