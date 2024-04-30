<%@include file="/WEB-INF/project/header.jsp"%>
<script src="${appURL}/js/project/adminnav.js"></script>
<main class="text-bg-dark">
    <section class="pb-0 py-sm-5">
        <div class="container">
            <h1>${pageTitle}</h1>
            <div class="row">

                <div class="col-xl-9 col-xxl-8">

                    <div class="row g-4">
                        <c:if test="${not empty DeleteError}">
                            <div class="text-danger">
                                    ${DeleteError}
                            </div>
                        </c:if>
                        <c:if test="${not empty selection}">
                            <c:choose>
                                <c:when test="${selection eq 'channels'}">
                                    <script>
                                        $( document ).ready(function() {
                                            selectChannels();
                                        });
                                    </script>
                                </c:when>
                                <c:when test="${selection eq 'users'}">
                                    <script>
                                        $( document ).ready(function() {
                                            selectUsers();
                                        });
                                    </script>
                                </c:when>
                            </c:choose>
                        </c:if>

                        <div class="d-flex justify-content-center">
                            <button onclick="selectChannels()" id="channelbtn" class="btn btn-primary w-50 border-0 border-bottom border-right border-primary" style="background-color: transparent">
                                Channels
                                <c:set var="selection" value="channels"/>
                            </button>
                            <button onclick="selectUsers()" id="userbtn" class="btn btn-primary w-50 border-0 border-bottom border-left border-primary" style="background-color: transparent">
                                Users
                                <c:set var="selection" value="users"/>
                            </button>
                        </div>


                        <div class="overflow-auto" style="height: 70vh">
                            <div style="display: none" id="channellist">
                                <c:forEach items="${channelList}" var="channel">
                                    <div class="container">
                                        <div class="row border m-1 p-1">
                                            <div class="col-md-6 text-start d-flex flex-column">
                                                <div class="mt-2">
                                                    <strong>${channel.getChannelID()}</strong>
                                                </div>
                                                <h4 class="my-2">Users: ${channel.getUsersInChannel()}</h4>
                                                <div class="mb-2">
                                                    <span class="me-2">Messages in the last ${hours} hours: ${channel.getMessages()}</span>
                                                </div>
                                            </div>
                                            <div class="col-md-6 text-md-end text-sm-start d-flex justify-content-md-end align-items-md-center">
                                                <div class="mb-2">
                                                    <c:choose>
                                                        <c:when test="${channel.isDeleted() eq false}">
                                                            <form action="${appURL}/admin" method="post">
                                                                <input type="hidden" name="formtype" value="delete">
                                                                <input type="hidden" name="hours" value="${hours}">
                                                                <button class="btn btn-danger" name="channelid" type="submit" value="${channel.getChannelID()}">Delete</button>
                                                            </form>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="btn btn-info" disabled name="channelid" type="button">Deleted</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <div style="display: none" id="userlist">
                                <c:forEach items="${userList}" var="user">
                                    <div class="container">
                                        <div class="row border m-1 p-1">
                                            <div class="col-md-6 text-start d-flex flex-column">
                                                <div class="mt-2">
                                                    <strong>${user.getUserID()}</strong>
                                                </div>
                                                <h4 class="my-2">Last logged in: ${user.getLastLoggedIn()}</h4>
                                                <div class="mb-2">
                                                    <span class="me-2">Messages in the last ${hours} hours: ${user.getMessages()}</span>
                                                </div>
                                            </div>
                                            <div class="col-md-6 text-md-end text-sm-start d-flex justify-content-md-end align-items-md-center">
                                                <div class="mb-2">
                                                    <c:if test="${user.getUserID() != userid}">
                                                        <form action="${appURL}/admin" method="post">
                                                            <input type="hidden" name="formtype" value="deleteuser">
                                                            <input type="hidden" name="hours" value="${hours}">
                                                            <button class="btn btn-danger" name="deleteuserid" type="submit" value="${user.getUserID()}">Delete</button>
                                                        </form>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>


                    </div>
                </div>
                <!-- Main content END -->
                <!-- Right sidebar START -->
                <div class="col-lg-3 col-xxl-4">
                    <!-- Responsive offcanvas body START -->
                    <div class="offcanvas-xl offcanvas-end" tabindex="-1" id="offcanvasSidebar">
                        <div class="text-bg-dark p-3 p-xl-0">
                            <form action="${appURL}/admin" method="post">
                                <div class="card card-body text-bg-dark p-4 mb-4">
                                    <h4 class="mb-3">Hours</h4>
                                    <ul class="list-inline mb-0">
                                        <c:if test="${not empty FilterError}">
                                            <div class="text-danger">
                                                    ${FilterError}
                                            </div>
                                        </c:if>
                                        <li class="list-inline-item mb-2">
                                            <input type="text" class="text-bg-dark" name="hours" value="${Hours}"/>
                                            <input type="hidden" name="formtype" value="filter"/>
                                            <input type="hidden" name="selection" value="${selection}"/>
                                        </li>
                                    </ul>
                                    <div class="d-grid p-2 p-xl-0 bg-body text-center">
                                        <button type="submit" class="btn btn-primary mb-0">Filter</button>
                                    </div>
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
    <div>
        <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
    </div>
</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
