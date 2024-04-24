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

                    <%@include file="/WEB-INF/learnx/flashMessage.jsp"%>

                    <!-- Job list START -->
                    <div class="row g-4">
                        <c:forEach items="${listings}" var="listing">
                            <div class="container">
                                <div class="row border m-1 p-1">
                                    <div class="col-md-6 text-start d-flex flex-column">
                                        <div class="mt-2">
                                            <strong>${listing.department_name}</strong>
                                            <c:if test="${listing.featured == true}">
                                                <span class="badge bg-warning">Featured</span>
                                            </c:if>
                                        </div>
                                        <h4 class="my-2">${listing.position}</h4>
                                        <div class="mb-2">
                                            <span class="me-2"><fmt:formatDate value="${listing.posted_at}" type="date" dateStyle="short"/></span>
                                            <span class="me-2">${listing.contract}</span>
                                            <span>${listing.location}</span>
                                        </div>
                                    </div>
                                    <div class="col-md-6 text-md-end text-sm-start d-flex justify-content-md-end align-items-md-center">
                                        <div class="mb-2">
                                            <button>Apply</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <!-- Job list END -->

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
