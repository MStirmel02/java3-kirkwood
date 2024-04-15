<%@include file="header.jsp" %>

<body>
<div class="sectioncontainer">
    <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark leftColumn">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4 text-center">Channels</span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item justify-content-center mb-3">
                <div class="dropdown dropend text-bg-dark">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                        Join a channel
                    </button>
                    <form class="dropdown-menu p-4" action="${appURL}/home" method="post">
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
            <li class="nav-item text-center mb-3">
                or
            </li>
            <li class="nav-item mb-3">
                <div class="dropdown dropend text-bg-dark">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                        Create a channel
                    </button>
                    <form class="dropdown-menu p-4" action="${appURL}/home" method="post">
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
            <c:forEach items="${channelList}" var="channel">
                <li class="nav-item mb-5">
                    <div class="dropdown dropend">
                        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${channel.getChannelID()}
                        </button>
                        <ul class="dropdown-menu dropdown-menu-dark">
                            <li>
                                <form action="${appURL}/home" method="post">
                                    <button class="dropdown-item text-secondary" name="channel" value="${channel.getChannelID()}">View Channel</button>
                                    <input type="hidden" name="formtype" value="view">
                                </form>
                                <form  action="${appURL}/home" method="post">
                                    <button class="dropdown-item text-danger" name="channel" value="${channel.getChannelID()}">Leave Channel</button>
                                    <input type="hidden" name="formtype" value="leave">
                                </form>
                        </ul>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="column" id="rightColumn">
        <!-- Content for the right column goes here -->
        <h2>Text box</h2>
        <p>Will contain the text from a channel</p>
    </div>
</div>
</body>

<%@include file="footer.jsp" %>
</html>