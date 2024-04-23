<div class="container">
    <div class="row mb-4">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <ul class="list-inline">
                        <li class="list-inline-item">Account Created: ${currentUser.getDateCreated()}</li>
                        <li class="list-inline-item">Last Logged In: ${currentUser.getLastLoggedIn()}</li>
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