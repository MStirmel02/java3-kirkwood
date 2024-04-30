<body class="text-bg-dark">
<%@include file="/WEB-INF/project/header.jsp"%>
<main style="height: 75vh">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10 col-md-12">
                <h1>${pageTitle}</h1>
                <hr class="py-2">
                <h3>Who we are</h3>
                <p>${initParam['finalProjName']} is a simple chatting application developed for the purposes of clean and simple communication.</p>

                <h3>Comments</h3>
                <p>I had quite a bit of fun creating ChatApp. While I am primarily a backend developer who focuses on APIs and databasing, making
                a website with an at least usable UI to use my backend code. It for sure does not look the best it could, and there are a few things
                I would do differently in hindsight, but as it is, I feel relatively happy with the end result. The main thing I would redo if time
                permitted would be switching the chat system over to a web socket based system with rooms.</p>
                <hr class="py-2">
            </div>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
</body>
