<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<c:import url="/WEB-INF/layouts/heade.jsp" />
	<title>S.R.D. - Tela LOGIN</title>
	<style type="text/css">
		html {
			  -webkit-font-smoothing: antialiased;
			  -moz-osx-font-smoothing: grayscale;
			  text-rendering: optimizelegibility;
			}
			
			html,
			body {
			  height: 100%;
			}
			
			body {
			  font-family: 'Open Sans', sans-serif;
			}
			
			.text {
			  position: fixed;
			  bottom: 0;
			  left: 0;
			  width: 100%;
			  height: 250px;
			  z-index: 10;
			}
			
			svg {
			  width: 100%;
			  height: inherit;
			}
			svg text {
			  text-anchor: middle;
			}
			svg #alpha {
			  fill: #666666;
			}
			svg #title {
			  letter-spacing: -2px;
			  font-size: 6em;
			  font-weight: 800;
			}
			svg #subtitle {
			  letter-spacing: 8px;
			  font-size: 1.2em;
			  font-weight: 300;
			  text-transform: uppercase;
			}
			svg #base {
			  fill: black;
			  -webkit-mask: url(#mask);
			          mask: url(#mask);
			}
			
			section.intro {
			  background: url(https://s3-us-west-2.amazonaws.com/s.cdpn.io/953/mision.jpg) no-repeat top center;
			  background-size: cover;
			  position: relative;
			  width: 100%;
			  min-height: 100%;
			}
			
			/*@keyframes alpha {
			  50%{fill: #000;}
			}*/
					
	
	 body{
		    background: rgba(73,155,234,1);
		    background: -moz-linear-gradient(left, rgba(73,155,234,1) 0%, rgba(32,124,229,1) 100%);
			background: -webkit-gradient(left top, right top, color-stop(0%, rgba(73,155,234,1)), color-stop(100%, rgba(32,124,229,1)));
			background: -webkit-linear-gradient(left, rgba(73,155,234,1) 0%, rgba(32,124,229,1) 100%);
			background: -o-linear-gradient(left, rgba(73,155,234,1) 0%, rgba(32,124,229,1) 100%);
			background: -ms-linear-gradient(left, rgba(73,155,234,1) 0%, rgba(32,124,229,1) 100%);
			background: linear-gradient(to right, rgba(73,155,234,1) 0%, rgba(32,124,229,1) 100%);
			filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#499bea', endColorstr='#207ce5', GradientType=1 );
			background-color: #444; 
			
		}
	.form-signin input[type="text"] {
	        margin-bottom: 5px;
	        border-bottom-left-radius: 0;
	        border-bottom-right-radius: 0;
	    }
	.form-signin input[type="password"] {
	        margin-bottom: 10px;
	        border-top-left-radius: 0;
	        border-top-right-radius: 0;
	    }
	.form-signin .form-control {
	        position: relative;
	        font-size: 16px;
	        font-family: 'Open Sans', Arial, Helvetica, sans-serif;
	        height: auto;
	        padding: 10px;
	        -webkit-box-sizing: border-box;
	        -moz-box-sizing: border-box;
	        box-sizing: border-box;
	    }
    .vertical-offset-100 {
	        padding-top: 100px;
	    }
	.img-responsive {
		    display: block;
		    max-width: 100%;
		    height: auto;
		    margin: auto;
	    }
	 .panel {
		    margin-bottom: 20px;
		    background-color: rgba(255, 255, 255, 0.75);
		    border: 1px solid transparent;
		    border-radius: 4px;
		    -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
		    box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
	    }
		
	</style>
</head>
<body>
     <div class="container">     
		 <div class="row vertical-offset-100">
             <div class="col-md-4 col-md-offset-4">
                 <div class="panel panel-default">
                     <div class="panel-heading">                                
                         <div class="row-fluid user-row">
                           	<label class="panel-login">                                    
                                 <div class="col-xs-offset-2 col-xs-12">
									<c:if test="${not empty errors}">
						  				  <ul class="error-messages">
						     			   <c:forEach var="error" items="${errors}">
						      			      <li class="${error.category}">${error.message}</li>
						     			   </c:forEach>
						   				 </ul>
									</c:if>
							 	 </div> 
                              </label>
                         </div>
                     </div>
                     <div class="panel-body">
                         <form accept-charset="UTF-8" role="form" class="form-signin" action="${linkTo[LoginController].login}" method="post">
                             <fieldset>
                                 <input class="form-control" placeholder="Username" id="username" type="text" name="v_login" autofocus/>
                                 <input class="form-control" placeholder="Password" id="password" type="password" name="v_senha" />
                                 <br></br>
                                 <input class="btn btn-lg btn-success btn-block" type="submit" id="login" value="Login »">
                             </fieldset>
                         </form>
                     </div>
                 </div>
             </div>
         </div>
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
						       <text id="title" x="50%" y="0" dy="1.58em">S. R. D.</text>
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
     </div>
	<c:import url="/WEB-INF/layouts/footer.jsp" />
	
</body>
</html>     