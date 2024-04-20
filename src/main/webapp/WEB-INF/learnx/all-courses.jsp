<%@include file="/WEB-INF/learnx/top.jsp"%>
<main>
    <!-- =======================
Page content START -->
    <section class="pb-0 py-sm-5">
        <div class="container">
            <h1>${pageTitle}</h1>
            <div class="row">

                <!-- Main content START -->
                <div class="col-xl-9 col-xxl-8">

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

                    <!-- Course list START -->
                    <div class="row g-4">
                        <c:forEach items="${courses}" var="course">
                        <!-- Card list START -->
                        <div class="col-12">
                            <div class="card shadow overflow-hidden p-2">
                                <div class="row g-0">
                                    <div class="col-md-5 overflow-hidden">
                                        <img src="${appURL}/images/learnx/${course.picture}" class="rounded-2" alt="${course.name}">
                                    </div>
                                    <div class="col-md-7">
                                        <div class="card-body">
                                            <!-- Badge and rating -->
                                            <div class="d-flex justify-content-between align-items-center mb-2">
                                                <!-- Badge -->
                                                <a href="${appURL}/courses?category=${course.categoryId}" class="badge text-bg-primary mb-2 mb-sm-0">${course.categoryName}</a>
                                            </div>

                                            <!-- Title -->
                                            <h5 class="card-title"><a href="#" data-bs-toggle="modal" data-bs-target="#courseModal${course.id}">${course.name}</a></h5>
                                            <p class="text-truncate-2 d-none d-lg-block">${course.description}</p>

                                            <!-- Info -->
                                            <ul class="list-inline">
                                                <li class="list-inline-item h6 fw-light"><i class="fas fa-signal
                                                    <c:choose><c:when test="${course.level eq 'Beginner'}">text-success</c:when><c:when test="${course.level eq 'Intermediate'}">text-warning</c:when><c:otherwise>text-danger</c:otherwise></c:choose> me-2"></i>
                                                    <a href="${appURL}/courses?skill-level=${course.level}">${course.level}</a>
                                                </li>
                                            </ul>

                                            <!-- Teacher and Enroll -->
                                            <div class="d-sm-flex justify-content-sm-between align-items-center">
                                                <!-- Teacher -->
                                                <div class="d-flex align-items-center">
                                                    <p class="mb-0 ms-2">Taught by: ${course.teacherFirstName}&nbsp;${course.teacherLastName}</p>
                                                </div>

                                                <div class="d-flex align-items-center">
                                                    <c:set var="cost" value="1234.567"></c:set>
                                                    <p class="mb-0 ms-2">Cost: <fmt:formatNumber value="${cost}" type="currency" currencyCode="USD" /></p>
                                                </div>

                                                <c:if test="${activeUser.privileges eq 'student'}">
                                                <!-- Enroll -->
                                                <div class="mt-3 mt-sm-0">
                                                    <a href="${appURL}/enroll?course=${course.id}" class="btn btn-dark">Enroll</a>
                                                </div>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Card list END -->

                        <!-- Course Modal -->
                        <div class="modal fade" id="courseModal${course.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">${course.name}</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        ...
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        </c:forEach>
                    </div>
                    <!-- Course list END -->

                </div>
                <!-- Main content END -->

                <!-- Right sidebar START -->
                <div class="col-lg-3 col-xxl-4">
                    <!-- Responsive offcanvas body START -->
                    <div class="offcanvas-xl offcanvas-end" tabindex="-1" id="offcanvasSidebar">
                        <div class="offcanvas-header bg-light">
                            <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Advance Filter</h5>
                            <button  type="button" class="btn-close" data-bs-dismiss="offcanvas" data-bs-target="#offcanvasSidebar" aria-label="Close"></button>
                        </div>
                        <div class="offcanvas-body p-3 p-xl-0">
                            <form action="${appURL}/courses" method="GET">
                                <!-- Category START -->
                                <div class="card card-body shadow p-4 mb-4">
                                    <!-- Title -->
                                    <h4 class="mb-4">Category</h4>
                                    <div class="row">
                                        <!-- Category group -->
                                        <div class="col-xxl-6">
                                            <!-- Checkbox -->
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="category" value="${categories[0].id}" id="flexCheckDefault9">
                                                <label class="form-check-label" for="flexCheckDefault9">${categories[0].name} (${categories[0].numCourses})</label>
                                            </div>
                                            <!-- Checkbox -->
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="category" value="${categories[1].id}" id="flexCheckDefault10">
                                                <label class="form-check-label" for="flexCheckDefault10">${categories[1].name} (${categories[1].numCourses})</label>
                                            </div>
                                            <!-- Checkbox -->
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="category" value="${categories[2].id}" id="flexCheckDefault11">
                                                <label class="form-check-label" for="flexCheckDefault11">${categories[2].name} (${categories[2].numCourses})</label>
                                            </div>
                                            <!-- Checkbox -->
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" name="category" value="${categories[3].id}" id="flexCheckDefault12">
                                                <label class="form-check-label" for="flexCheckDefault12">${categories[3].name} (${categories[3].numCourses})</label>
                                            </div>
                                        </div>

                                    </div><!-- Row END -->
                                </div>
                                <!-- Category END -->

                                <!-- Skill level START -->
                                <div class="card card-body shadow p-4 mb-4">
                                    <!-- Title -->
                                    <h4 class="mb-3">Skill level</h4>
                                    <ul class="list-inline mb-0">
                                        <!-- Item -->
                                        <li class="list-inline-item mb-2">
                                            <input type="radio" class="btn-check" name="skill-level" value="all-levels" id="btn-check-12">
                                            <label class="btn btn-light btn-primary-soft-check" for="btn-check-12">All levels</label>
                                        </li>
                                        <!-- Item -->
                                        <li class="list-inline-item mb-2">
                                            <input type="radio" class="btn-check" name="skill-level" value="beginner" id="btn-check-9">
                                            <label class="btn btn-light btn-primary-soft-check" for="btn-check-9">Beginner</label>
                                        </li>
                                        <!-- Item -->
                                        <li class="list-inline-item mb-2">
                                            <input type="radio" class="btn-check" name="skill-level" value="intermediate" id="btn-check-10">
                                            <label class="btn btn-light btn-primary-soft-check" for="btn-check-10">Intermediate</label>
                                        </li>
                                        <!-- Item -->
                                        <li class="list-inline-item mb-2">
                                            <input type="radio" class="btn-check" name="skill-level" value="advanced" id="btn-check-11">
                                            <label class="btn btn-light btn-primary-soft-check" for="btn-check-11">Advanced</label>
                                        </li>
                                    </ul>
                                </div>
                                <!-- Skill level END -->
                                <!-- Button -->
                                <div class="d-grid p-2 p-xl-0 bg-body text-center">
                                    <button type="submit" class="btn btn-primary mb-0">Filter Results</button>
                                </div>

                            </form><!-- Form End -->
                        </div>


                    </div>
                    <!-- Responsive offcanvas body END -->
                </div>
                <!-- Right sidebar END -->

            </div><!-- Row END -->
        </div>
    </section>
    <!-- =======================
    Page content END -->
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>