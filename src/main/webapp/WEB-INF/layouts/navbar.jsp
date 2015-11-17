<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="navbar navbar-default" role="navigation">
      <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">
                <span><fmt:message key="srd.label.abreviacao" /></span>
        	</a>
			<div class="btn-group pull-center">
				<span class="hidden-sm hidden-xs text-logo"> <fmt:message key="srd.label.completo" /></span>
			</div>
        <!-- user dropdown starts -->
			<div class="btn-group pull-right">
			    <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			        <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs">${userInfo.usuario.username}</span>
			   		 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">    
				    <li><a href="${linkTo[LoginController].logout}">Sair</a></li>
	            </ul>
       	 </div>
     </div>
</div>
<!-- topbar ends -->
