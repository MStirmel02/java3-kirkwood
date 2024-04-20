<%@include file="/WEB-INF/learnx/top.jsp"%>
<main>
    <div class="container">
        <h1>All Users</h1>
        <p>There ${users.size() == 1 ? "is" : "are"}&nbsp;${users.size()} user${users.size() != 1 ? "s" : ""}</p>
        <div class="row">
            <div class="col-lg-8">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.firstName}&nbsp;${user.lastName}</td>
                                <td>${user.email}</td>
                                <td><a href="${appURL}/edit-user?id=${user.id}" class="btn btn-primary mb-sm-2">Edit</a>
                                    <a href="${appURL}/delete-user?id=${user.id}" class="btn btn-danger mb-sm-2">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>
