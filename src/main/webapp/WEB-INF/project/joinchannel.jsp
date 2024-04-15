<%@include file="/WEB-INF/project/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${appURL}/css/gifBody.css">
<main class="container text-bg-dark h-100">
    <section class="p-0 d-flex align-items-center position-relative overflow-hidden">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8 m-auto">
                    <div class="row my-5">
                        <div class="col-sm-10 col-xl-8 m-auto">
                            <h2>Welcome Back!</h2>

                            <c:choose>
                                <c:when test="${not empty results.loginError}">
                                    <p class="alert alert-danger">
                                            ${results.loginError}
                                    </p>
                                </c:when>
                                <c:when test="${not empty flashMessageWarning}">
                                    <p class="alert alert-warning">
                                            ${flashMessageWarning}
                                    </p>
                                    <c:remove var="flashMessageWarning" scope="session"></c:remove>
                                </c:when>
                                <c:otherwise>
                                    <p class="lead mb-4">Join a channel</p>
                                </c:otherwise>
                            </c:choose>

                            <!-- Form START -->
                            <form action="${appURL}/joinchannel" method="post">
                                <!-- Username -->
                                <div class="mb-4">
                                    <label for="inputChannel" class="form-label">Channel *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text text-white bg-dark rounded-start border-0 text-secondary px-3"><i class="bi bi-envelope-fill"></i></span>
                                        <input type="text" class="form-control form-control-dark text-bg-dark rounded-end ps-1" placeholder="Channel" id="inputChannel" name="inputChannel"  value="${results.username}">
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="mb-4">
                                    <label for="inputPassword1" class="form-label">Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text text-white bg-dark rounded-start border-0 text-secondary px-3"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="form-control form-control-dark text-bg-dark rounded-end ps-1" placeholder="*********" id="inputPassword1" name="inputPassword1"  value="${results.password1}">
                                    </div>
                                </div>

                                <!-- Button -->
                                <div class="align-items-center mt-0">
                                    <div class="d-flex justify-content-center">
                                        <button class="btn text-white border" type="submit">Join</button>
                                    </div>
                                </div>

                                <input type="hidden" name="redirect" value="${redirect}">

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
