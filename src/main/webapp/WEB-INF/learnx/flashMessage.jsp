<c:if test="${not empty flashMessageSuccess}">
    <div class="alert alert-success">
            ${flashMessageSuccess}
    </div>
    <c:remove var="flashMessageSuccess"></c:remove>
</c:if>
<c:if test="${not empty flashMessageWarning}">
    <div class="alert alert-warning">
            ${flashMessageWarning}
    </div>
    <c:remove var="flashMessageWarning"></c:remove>
</c:if>
