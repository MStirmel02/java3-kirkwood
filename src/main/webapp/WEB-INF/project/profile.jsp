<%@include file="header.jsp" %>

<main>
    <%@include file="/WEB-INF/project/profilesideheader.jsp"%>
    <div class="container">
        <div class="row">
            <%@include file="/WEB-INF/project/profilesidebar.jsp"%>
            <div class="col-xl-9">
                <div class="card border rounded-3">
                    <div class="card-body">

                        <c:if test="${not empty flashMessageWarning}">
                            <div class="alert alert-warning">
                                    ${flashMessageWarning}
                            </div>
                            <c:remove var="flashMessageWarning"></c:remove>
                        </c:if>


                        <form action="${appURL}/profile" method="POST">
                            <div class="row g-4">
                                <!-- First Name -->
                                <div class="col-md-6">
                                    <label for="firstNameInput" class="form-label">Email Address</label>
                                    <input type="text" class="form-control" id="firstNameInput" name="firstNameInput" value="${fn:escapeXml(activeUser.getEmail())}">
                                </div>

                                <!-- Save button -->
                                <div class="d-sm-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary mb-0">Save changes</button>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>



        </div>
    </div>
</main>














<%@include file="footer.jsp" %>
