<%@include file="header.jsp" %>

<body>
<div class="sectioncontainer">
    <!-- d-flex flex-column flex-shrink-0 p-3 text-bg-dark align-items-start leftColumn -->
    <div class="overflow-auto px-2 w-25 test text-bg-dark">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4 text-center">Channels</span>
        </a>
        <hr>
        <c:if test="${not empty results.ChannelError}">
            <div class="text-danger">
                    ${results.ChannelError}
            </div>
        </c:if>
        <ul class="nav nav-pills flex-column mb-auto w-15 h-100 align-items-start">
            <li class="nav-item justify-content-end mb-3">
                <div class="dropdown dropend text-bg-dark">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                        <fmt:message key="HomePage.JoinChannel"></fmt:message>
                    </button>
                    <form class="dropdown-menu p-4 text-bg-dark" action="${appURL}/home" method="post">
                        <div class="mb-3">
                            <label for="joinchannelid" class="form-label">Channel ID</label>
                            <input class="form-control" id="joinchannelid" name="joinchannelid" placeholder="Channel ID">
                        </div>
                        <div class="mb-3">
                            <label for="joinchannelpassword" class="form-label">Password</label>
                            <input type="password" class="form-control" id="joinchannelpassword" name="joinchannelpassword" placeholder="Password">
                        </div>
                        <input type="hidden" name="formtype" value="join">
                        <button type="submit" class="btn btn-primary">Join</button>
                    </form>
                </div>
            </li>
            <li class="nav-item text-start mb-3">
                or
            </li>
            <li class="nav-item justify-content-end mb-3">
                <div class="dropdown dropend text-bg-dark">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                        <fmt:message key="HomePage.CreateChannel"></fmt:message>
                    </button>
                    <form class="dropdown-menu text-bg-dark p-4" action="${appURL}/home" method="post">
                        <div class="mb-3">
                            <label for="createchannelid" class="form-label">Channel ID</label>
                            <input class="form-control" id="createchannelid" name="createchannelid" placeholder="Channel ID">
                        </div>
                        <div class="mb-3">
                            <label for="createchannelpassword" class="form-label">Password</label>
                            <input type="password" class="form-control" id="createchannelpassword" name="createchannelpassword" placeholder="Password">
                        </div>
                        <input type="hidden" name="formtype" value="create">
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form>
                </div>
            </li>
            <hr>
            <c:forEach items="${channelList}" var="channel">
                <li class="nav-item justify-content-start mb-5">
                    <div class="dropdown dropend">
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${channel.getChannelID()}
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark">
                            <li>
                                <form action="${appURL}/home" method="post">
                                    <button class="dropdown-item text-secondary" name="channel" value="${channel.getChannelID()}"><fmt:message key="HomePage.ViewChannel"></fmt:message></button>
                                    <input type="hidden" name="formtype" value="view">
                                </form>
                                <c:set var="creator" value="Creator"></c:set>
                                <c:choose>
                                    <c:when test="${channel.getUserRole() eq creator}">
                                        <form  action="${appURL}/home" method="post">
                                            <button class="dropdown-item text-danger" name="channel" value="${channel.getChannelID()}"><fmt:message key="HomePage.DeleteChannel"></fmt:message></button>
                                            <input type="hidden" name="formtype" value="delete">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form  action="${appURL}/home" method="post">
                                            <button class="dropdown-item text-danger" name="channel" value="${channel.getChannelID()}"><fmt:message key="HomePage.LeaveChannel"></fmt:message></button>
                                            <input type="hidden" name="formtype" value="leave">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="column text-bg-dark overflow-auto" id="rightColumn">
        <!--Messages-->
        <div class="border-white border-1" style="height: 70vh">
            <div class="container border border-bottom-0">
                <c:if test="${not empty messageList}">
                    <c:forEach items="${messageList}" var="message">
                        <div class="row justify-content-md-start mt-1 mb-1 border border-top-0">
                            <div class="col col-1">
                                ${message.getTimeSent()}
                            </div>
                            <div class="col col-1">
                                ${message.getUserID()}
                            </div>
                            <div class="col-md-auto">
                                ${message.getContent()}
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <!--Text box-->
        <div class="msg-bottom mt-auto align-bottom">
            <div class="input-group">
                <form action="${appURL}/home" method="post">
                    <input
                            type="text"
                            class="form-control text-bg-dark"
                            name="messagecontent"
                            placeholder="Write message..."
                    />
                    <input type="hidden" name="formtype" value="send">
                    <span class="input-group-text send-icon">
                        <button class="btn btn-primary" type="submit">
                            Send
                        </button>
              </span>
                </form>
                <form action="${appURL}/home" method="post">
                    <input type="hidden" name="formtype" value="refresh">
                    <span class="input-group-text send-icon">
                        <button class="btn btn-primary" type="submit">
                            Refresh
                        </button>
              </span>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="footer.jsp" %>
</html>