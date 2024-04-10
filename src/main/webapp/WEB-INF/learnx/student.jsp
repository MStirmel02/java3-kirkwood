<%@include file="/WEB-INF/learnx/top.jsp"%>
<main>
    <%@include file="/WEB-INF/learnx/left-side-bar-header.jsp"%>
    <div class="container">
        <div class="row">
            <%@include file="/WEB-INF/learnx/left-side-bar.jsp"%>

            <div class="col-xl-9">

                <!-- Counter boxes START -->
                <div class="row mb-4">
                    <!-- Counter item -->
                    <div class="col-sm-6 col-lg-4 mb-3 mb-lg-0">
                        <div class="d-flex justify-content-center align-items-center p-4 bg-orange bg-opacity-15 rounded-3">
                            <span class="display-6 lh-1 text-orange mb-0"><i class="fas fa-tv fa-fw"></i></span>
                            <div class="ms-4">
                                <div class="d-flex">
                                    <h5 class="purecounter mb-0 fw-bold"
                                        data-purecounter-start="0"
                                        data-purecounter-end="${fn:length(enrollments)}"
                                        data-purecounter-delay="200"></h5>
                                </div>
                                <p class="mb-0 h6 fw-light">Total Courses</p>
                            </div>
                        </div>
                    </div>
                    <!-- Counter item -->
                    <%--                    <div class="col-sm-6 col-lg-4 mb-3 mb-lg-0">--%>
                    <%--                        <div class="d-flex justify-content-center align-items-center p-4 bg-purple bg-opacity-15 rounded-3">--%>
                    <%--                            <span class="display-6 lh-1 text-purple mb-0"><i class="fas fa-clipboard-check fa-fw"></i></span>--%>
                    <%--                            <div class="ms-4">--%>
                    <%--                                <div class="d-flex">--%>
                    <%--                                    <h5 class="purecounter mb-0 fw-bold" data-purecounter-start="0" data-purecounter-end="52"	data-purecounter-delay="200">0</h5>--%>
                    <%--                                </div>--%>
                    <%--                                <p class="mb-0 h6 fw-light">Lessons Complete</p>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                    <!-- Counter item -->
                    <%--                    <div class="col-sm-6 col-lg-4 mb-3 mb-lg-0">--%>
                    <%--                        <div class="d-flex justify-content-center align-items-center p-4 bg-success bg-opacity-10 rounded-3">--%>
                    <%--                            <span class="display-6 lh-1 text-success mb-0"><i class="fas fa-medal fa-fw"></i></span>--%>
                    <%--                            <div class="ms-4">--%>
                    <%--                                <div class="d-flex">--%>
                    <%--                                    <h5 class="purecounter mb-0 fw-bold" data-purecounter-start="0" data-purecounter-end="8" data-purecounter-delay="300">0</h5>--%>
                    <%--                                </div>--%>
                    <%--                                <p class="mb-0 h6 fw-light">Course Certificates</p>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                    </div>--%>
                </div>
                <!-- Counter boxes END -->

                <div class="card bg-transparent border rounded-3">
                    <!-- Card header START -->
                    <div class="card-header bg-transparent border-bottom">
                        <h3 class="mb-0">My Courses</h3>
                    </div>
                    <!-- Card header END -->

                    <c:choose>
                        <c:when test="${not empty flashMessageSuccess}">
                            <div class="alert alert-success mb-2">
                                    ${flashMessageSuccess}
                            </div>
                            <c:remove var="flashMessageSuccess" scope="session"></c:remove>
                        </c:when>
                        <c:when test="${not empty flashMessageWarning}">
                            <div class="alert alert-warning mb-2">
                                    ${flashMessageWarning}
                            </div>
                            <c:remove var="flashMessageWarning" scope="session"></c:remove>
                        </c:when>
                        <c:when test="${not empty flashMessageDanger}">
                            <div class="alert alert-danger mb-2">
                                    ${flashMessageDanger}
                            </div>
                            <c:remove var="flashMessageDanger" scope="session"></c:remove>
                        </c:when>
                    </c:choose>

                    <!-- Card body START -->
                    <div class="card-body">

                        <!-- Course list table START -->
                        <div class="table-responsive border-0">
                            <table class="table table-dark-gray align-middle p-4 mb-0 table-hover">
                                <!-- Table head -->
                                <thead>
                                <tr>
                                    <th scope="col" class="border-0 rounded-start">Course Title</th>
                                    <%--                                    <th scope="col" class="border-0">Total Lessons</th>--%>
                                    <%--                                    <th scope="col" class="border-0">Completed Lessons</th>--%>
                                    <%--                                    <th scope="col" class="border-0 rounded-end">Percent Complete</th>--%>
                                </tr>
                                </thead>
                                <!-- Table body START -->
                                <tbody>

                                <c:forEach items="${enrollments}" var="enrollment">
                                    <!-- Table item -->
                                    <tr>
                                        <!-- Table data -->
                                        <td>
                                            <div class="d-flex align-items-center">
                                                <div class="mb-0 ms-2">
                                                    <!-- Title -->
                                                        <%--                                                enrollment.key returns a Course --%>
                                                    <h6><a href="#">${enrollment.key.name}</a></h6>
                                                </div>
                                            </div>
                                        </td>

                                    </tr>
                                </c:forEach>

                                </tbody>
                                <!-- Table body END -->
                            </table>
                        </div>
                        <!-- Course list table END -->
                    </div>
                    <!-- Card body START -->
                </div>


            </div><%-- End right col --%>
        </div><%-- End row --%>
    </div><%-- End container --%>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>