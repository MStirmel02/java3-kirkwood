<div class="container">
    <div class="row mb-4">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <h1 class="card-title">${fn:escapeXml(activeUser.firstName)}&nbsp;${fn:escapeXml(activeUser.lastName)}</h1>
                    <ul class="list-inline">
                        <li class="list-item"><fmt:message key="SideBarHead.CreatedAt"></fmt:message> <fmt:formatDate value="${activeUser.created_at_toDate}" type="date" dateStyle="long" /></li>
                        <li class="list-item"><fmt:message key="SideBarHead.UpdatedAt"></fmt:message> <fmt:formatDate value="${activeUser.updated_at_toDate}" type="both" dateStyle="full" timeStyle="long" /></li>
                    </ul>
                </div>
            </div><%--   Close card--%>
        </div><%-- Close col --%>
        <!-- Advanced filter responsive toggler START -->
        <!-- Divider -->
        <hr class="d-xl-none">
        <div class="col-12 col-xl-3 d-flex justify-content-end align-items-center">
            <button class="btn btn-primary d-xl-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
                <i class="fas fa-bars"></i>
            </button>
        </div>
        <!-- Advanced filter responsive toggler END -->
    </div>
</div>