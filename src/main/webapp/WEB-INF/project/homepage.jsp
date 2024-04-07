<%@include file="header.jsp" %>

<body>
<div class="sectioncontainer">
    <!--<div class="column" id="leftColumn">
         Content for the left column goes here
        <h2>Channel info</h2>
        <p>Will contain list of channels</p>
    </div>-->
    <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark leftColumn">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4">Channels</span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <c:forEach items="${channelList}" var="channel">
                <li class="nav-item">
                    <a href="homepage.jsp" class="nav-link active">
                        ${channel.getChannelID()}
                    </a>
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