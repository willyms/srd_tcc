<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<c:import url="/WEB-INF/layouts/heade.jsp" />
	<title>S. R. D.</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/srd/css/style_login.css">	
</head>
<body>
	<section>
     <div class="container">     
		 <div class="row vertical-offset-100">
             <div class="col-md-4 col-md-offset-4">
                 <div class="panel panel-default">
                     <div class="panel-heading">                                
                         <div class="row-fluid user-row">
							<c:if test="${not empty errors}">
			     			   <c:forEach var="error" items="${errors}">
			      			      <p class="my-notify-error">${error.message}</p>
			     			   </c:forEach>
							</c:if>
                         </div>
                     </div>
                     <div class="panel-body">
                         <form accept-charset="UTF-8" role="form" class="form-signin" action="${linkTo[LoginController].login}" method="post">
                             <fieldset>
                                 <input class="form-control" placeholder="Username" id="username" type="text" name="usuario.username" autofocus/>
                                 <input class="form-control" placeholder="Password" id="password" type="password" name="usuario.password" />
                                 <br></br>
                                 <input class="btn btn-lg btn-success btn-block" type="submit" id="login" value="Login »">
                             </fieldset>
                         </form>
                     </div>
                 </div>
             </div>
         </div>
         </div>
        </section>
         <footer>
         	<div class="footer">
		     	<div class="row">
						<div class="text">
						  <svg>
						    <defs>
						      <mask id="mask" x="0" y="0" width="100%" height="100%" >
						       <!-- alpha rectangle -->
						       <!-- rectángulo alfa -->
						       <rect id="alpha" x="0" y="0" width="100%" height="100%"/>
						       <!-- All text that you want -->
						       <!-- Coloca todo el texto que necesites -->
						       <text id="title" x="50%" y="0" dy="1.58em" class="ajuster">S. R. D.</text>
						       <text id="subtitle" x="50%" y="0" dy="9.8em">Sistema de Restrição por Departamento</text>
						     </mask>
						    </defs>
						    <!-- Apply color here! -->
						    <!-- Color aquí -->
						    <rect id="base" x="0" y="0" width="100%" height="100%"/>
						  </svg>
						</div>
					<section class="intro"></section>     	
		     	</div>
		     </div>
         </footer>
	<c:import url="/WEB-INF/layouts/footer.jsp" />
	<script type="text/javascript"  src="${pageContext.request.contextPath}/srd/js/script_login.js"></script>
</body>
</html>     