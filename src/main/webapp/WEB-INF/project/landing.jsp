<body class="text-bg-dark">
<%@include file="/WEB-INF/project/header.jsp"%>
<main class="container text-bg-dark" style="height: 75vh">
    <link rel="stylesheet" type="text/css" href="${appURL}/css/landinggifs.css">
    <div>
        This is the landing page, it is the start page for the website and will contain information regarding the application itself.
        More information will be added as I think of things to add here.
    </div>

    <div class="text-align-center">
        Here's what to expect from joining ChatApp:
    </div>
        <div class="row align-items-md-stretch">
            <div class="col-md-6">
                <div class="h-100 p-5 text-bg-dark rounded-3 chattinggif">
                    <h2>Chat With Friends!</h2>
                </div>
            </div>
            <div class="col-md-6">
                <div class="h-100 p-5 text-bg-dark rounded-3 joininggif">
                    <h2>Join Channels!</h2>
                </div>
            </div>
        </div>

    <div class="row align-items-md-stretch d-flex justify-content-center">
        <div class="col-md-6">
            <div class="h-100 p-5 text-bg-dark rounded-3 d-flex justify-content-center">
                <a class="btn btn-warning" href="${appURL}/signingup">Sign Up Here!</a>
            </div>
        </div>
    </div>

</main>
<%@include file="/WEB-INF/project/footer.jsp"%>
</body>