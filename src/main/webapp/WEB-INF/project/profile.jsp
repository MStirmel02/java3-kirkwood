<%@include file="header.jsp" %>

<main>
    <%@include file="/WEB-INF/project/profilesideheader.jsp"%>
    <div class="container">
        <div class="row">
            <%@include file="/WEB-INF/project/profilesidebar.jsp"%>
            <div class="col-xl-9">
                <div class="card border rounded-3">
                    <div class="card-body">

                        <form action="${appURL}/profile" method="POST">
                            <div class="row g-4">
                                <!-- First Name -->
                                <div class="col-md-6">
                                    <label for="inputEmail" class="form-label">Email Address</label>
                                    <input type="text" class="form-control <c:if test="${not empty EmailError}">is-invalid</c:if>" id="inputEmail" name="inputEmail" value="${fn:escapeXml(activeUser.getEmail())}">
                                    <c:if test="${not empty EmailError}">
                                        <div class="invalid-feedback">
                                                ${EmailError}
                                        </div>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <label for="inputLangauge" class="form-label">Language</label>
                                    <select name="inputLanguage" id="inputLangauge" class="form-select <c:if test="${not empty LanguageError}">is-invalid</c:if>">
                                        <option value="en-US" ${activeUser.language eq 'en-US' ? 'selected' : ''}>English</option>
                                        <option value="ru-RU" ${activeUser.language eq 'ru-RU' ? 'selected' : ''}>Russian</option>
                                    </select>
                                    <c:if test="${not empty LanguageError}">
                                        <div class="invalid-feedback">
                                                ${LanguageError}
                                        </div>
                                    </c:if>
                                </div>

                                <!-- Save button -->
                                <div class="d-sm-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary mb-0">Save changes</button>
                                </div>

                            </div>
                        </form>
                    </div>
                </div>
            </div>



        </div>
    </div>
</main>














<%@include file="footer.jsp" %>
