<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
body {
	background-image:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAAaCAYAAACpSkzOAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAxMC8yOS8xMiKqq3kAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAABHklEQVRIib2Vyw6EIAxFW5idr///Qx9sfG3pLEyJ3tAwi5EmBqRo7vHawiEEERHS6x7MTMxMVv6+z3tPMUYSkfTM/R0fEaG2bbMv+Gc4nZzn+dN4HAcREa3r+hi3bcuu68jLskhVIlW073tWaYlQ9+F9IpqmSfq+fwskhdO/AwmUTJXrOuaRQNeRkOd5lq7rXmS5InmERKoER/QMvUAPlZDHcZRhGN4CSeGY+aHMqgcks5RrHv/eeh455x5KrMq2yHQdibDO6ncG/KZWL7M8xDyS1/MIO0NJqdULLS81X6/X6aR0nqBSJcPeZnlZrzN477NKURn2Nus8sjzmEII0TfMiyxUuxphVWjpJkbx0btUnshRihVv70Bv8ItXq6Asoi/ZiCbU6YgAAAABJRU5ErkJggg==);
}

.error-template {
	padding: 40px 15px;
	text-align: center;
}

.error-actions {
	margin-top: 15px;
	margin-bottom: 15px;
}

.error-actions .btn {
	margin-right: 10px;
}
</style>
<c:import url="/WEB-INF/layouts/heade.jsp" />
<title>Error ${pageContext.errorData.statusCode}</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="error-template">
					<h1>Oops!</h1>
					<h2>
					<font face="Tahoma" color="red">Erro -
							${pageContext.errorData.statusCode}</font> 					
					<c:choose>
							<c:when test="${pageContext.errorData.statusCode eq 404}">
									Página não encontrado.
							</c:when>
							<c:when test="${pageContext.errorData.statusCode eq 500}">
									<br />Erro de servidor interno.
							</c:when>
							<c:otherwise>
								 Ocorre um errror !		
							</c:otherwise>
						</c:choose>
					</h2> 
					<div class="error-details">
						<p>
							<strong>URI : </strong> ${pageContext.errorData.requestURI}
						</p>
						<c:choose>
							<c:when test="${pageContext.errorData.statusCode eq 404}">
									 Desculpe, ocorreu um erro , a página solicitada não foi encontrado !
							</c:when>
							<c:when test="${pageContext.errorData.statusCode eq 500}">
									<p>
										<strong>Mensagem : </strong>
										<c:out value="${requestScope['javax.servlet.error.message']}" />
									</p>
			
									<p>
										<strong>Exception : </strong>										
										<c:out value="${requestScope['javax.servlet.error.exception.message']}" />
									</p>
							</c:when>
							<c:otherwise>
									<strong>Mensagem : </strong>
										<c:out value="${requestScope['javax.servlet.error.message']}" />
										${pageContext.exception}
										${pageContext.errorData.throwable.cause}
										<c:out value="${requestScope['javax.servlet.error.exception']}" />
										<c:out value="${requestScope['javax.servlet.error.exception.message']}" />
										<c:out value="${requestScope['javax.servlet.error.exception_type']}" />
										<c:forEach var="stackTraceElem" items="${exception.stackTrace}">
										    <c:out value="${stackTraceElem}"/><br/>
										  </c:forEach>
							</c:otherwise>
						</c:choose>						
					</div>
					<div class="error-actions">
						<c:choose>
							<c:when test="${pageContext.errorData.statusCode eq 401}">
								<a href="${linkTo[AcessoController].index}"
								class="btn btn-primary btn-lg"><span
								class="glyphicon glyphicon-home"></span> Me leve para Página Inicial </a>
							</c:when>
							<c:otherwise>
								<a href="${linkTo[HomeController].index}"
								class="btn btn-primary btn-lg"><span
								class="glyphicon glyphicon-home"></span> Me leve para Página Inicial </a>
							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

