<%@include file="/WEB-INF/project/header.jsp"%>
<main class="container">
    <div class="card-group">
        <c:forEach items="${channelList}" var="channel">
            <div class="card">
                <img class="card-img-top" src="" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${channel.getChannelID()}</h5>
                    <p class="card-test">Users: ${channel.getUsersInChannel()}</p>
                    <p class="card-text">Messages in the last 24 hours: </p>
                </div>
            </div>
        </c:forEach>

    </div>

</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
