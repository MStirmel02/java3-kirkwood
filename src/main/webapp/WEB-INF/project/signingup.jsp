<%@include file="/WEB-INF/project/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${appURL}/css/gifBody.css">
<main class="container text-bg-dark h-100">
    <section class="p-0 d-flex align-items-center position-relative overflow-hidden">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8 m-auto">
                    <div class="row my-5">
                        <div class="col-sm-10 col-xl-8 m-auto">
                            <h2>We'd love to have you join us!</h2>

                            <!-- Form START -->
                            <form action="${appURL}/signingup" method="post">
                                <!-- Username -->
                                <div class="mb-4">
                                    <label for="inputUser1" class="form-label">Username *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text text-white bg-dark rounded-start border-0 text-secondary px-3"><i class="bi bi-envelope-fill"></i></span>
                                        <input type="text" class="<c:if test="${not empty results.userError}">is-invalid</c:if> form-control form-control-dark text-bg-dark rounded-end ps-1" placeholder="Username" id="inputUser1" name="inputUser1"  value="${results.username}">
                                        <c:if test="${not empty results.userError}">
                                            <div class="invalid-feedback">
                                                    ${results.userError}
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="mb-4">
                                    <label for="inputPassword1" class="form-label">Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text text-white bg-dark rounded-start border-0 text-secondary px-3"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="<c:if test="${not empty results.password1Error}">is-invalid</c:if> form-control form-control-dark text-bg-dark rounded-end ps-1" placeholder="*********" id="inputPassword1" name="inputPassword1"  value="${results.password1}">
                                        <c:if test="${not empty results.password1Error}">
                                            <div class="invalid-feedback">
                                                    ${results.password1Error}
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="mb-4">
                                    <label for="inputPassword2" class="form-label">Re-Enter Your Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text text-white bg-dark rounded-start border-0 text-secondary px-3"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="<c:if test="${not empty results.password2Error}">is-invalid</c:if> form-control form-control-dark text-bg-dark rounded-end ps-1" placeholder="*********" id="inputPassword2" name="inputPassword2"  value="${results.password2}">
                                        <c:if test="${not empty results.password2Error}">
                                            <div class="invalid-feedback">
                                                    ${results.password2Error}
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- Check box -->
                                <div class="mb-4">
                                    <div class="form-check">
                                        <input type="checkbox" class="<c:if test="${not empty results.agreeError}">is-invalid</c:if> form-check-input" id="checkbox-1" name="checkbox-1" value="agree" <c:if test="${results.agree eq 'true'}">checked</c:if>>
                                        <label class="form-check-label" for="checkbox-1">By signing up, you agree to the <a href="${appURL}/terms-of-use">terms and conditions</a></label>
                                        <c:if test="${not empty results.agreeError}">
                                            <div class="invalid-feedback">
                                                    ${results.agreeError}
                                            </div>
                                        </c:if>
                                    </div>
                                </div>

                                <!-- Button -->
                                <div class="align-items-center mt-0">
                                    <div class="d-flex justify-content-center">
                                        <button class="btn text-white border" type="submit">Sign Up</button>
                                    </div>
                                </div>

                                <input type="hidden" name="redirect" value="${redirect}">

                            </form>
                            <!-- Form END -->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
