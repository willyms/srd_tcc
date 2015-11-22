<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
<div class="col-md-offset-0 col-md-9">
<c:if test="${not empty mensagem_success}">	
	<div class="my-notify-success">${mensagem_success}</div>
</c:if>
<c:if test="${not empty mensagem_info}">	
	<div class="my-notify-info">${mensagem_info}</div>
</c:if>
<c:if test="${not empty mensagem_warning}">	
	<div class="my-notify-warning">${mensagem_warning}</div>
</c:if>
<c:if test="${not empty mensagem_error }">	
	<div class="my-notify-error">${mensagem_error}</div>
</c:if>
</div>
</div>