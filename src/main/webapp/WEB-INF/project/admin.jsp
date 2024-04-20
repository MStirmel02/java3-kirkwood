<%@include file="/WEB-INF/project/header.jsp"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script> <!-- chart.js -->
<script src="../../js/project/charting.js"></script>
<main class="container">
    <div class="card-group">
        <c:forEach items="${channelList}" var="channel">
            <div class="card">
                <img class="card-img-top" src="" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${channel.getChannelID()}</h5>
                    <p class="card-test">Users: ${channel.getUsersInChannel()}</p>
                    <p class="card-text">Messages in the last ${hours} hours: ${channel.getMessages()}</p>
                </div>
            </div>
        </c:forEach>
        <div>
            <canvas id="myChart" style="width:100%;max-width:700px"></canvas>
        </div>


    </div>

</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
