<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<tt:template>
	<jsp:attribute name="title">
		<fmt:message key="home" />
	</jsp:attribute>
	<jsp:attribute name="style">
		<style type="text/css">
			
		</style>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script src="${pageContext.request.contextPath}/js/app.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/qrcodeService.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/qrcode.js" type="text/javascript"></script>
		
	</jsp:attribute>
	<jsp:attribute name="header">
		<ul class="breadcrumb">
        	<li><a href="${linkTo[HomeController].index }"><fmt:message
						key="home" /></a><span class="divider"></span></li>       
			<li class="active"><fmt:message key="home.acesso" /></li>
		</ul>	
		
	</jsp:attribute>
	<jsp:body>	
		<div class="row">			
			<div ng-controller="qrCrtl">
			
				
				<div ng-if="acessoLiberado" class="alert alert-success" id="success-alert">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Success! </strong> Acesso permitido!
				</div>	
				
				<div ng-if="acessonaoLiberado" class="alert alert-danger" id="success-alert">
					    <button type="button" class="close" data-dismiss="alert">x</button>
					    <strong>Success! </strong> Não Acesso permitido!
				</div>				
				
				<div align="center" class="embed-responsive embed-responsive-16by9">
					<qr-scanner  width="630" height="354" class="embed-responsive-item" ng-success="onSuccess(data)" ng-error="onError(error)" />				
				</div>
				<div class="clearflix"></div>
				<div class="col-md-10 col-md-offset-2">
					
				</div>	
			</div>
		</div>
	</jsp:body>
</tt:template>