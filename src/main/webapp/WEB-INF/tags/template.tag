<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="style" fragment="true"%>
<%@attribute name="title" fragment="true"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@attribute name="script" fragment="true"%>
<!DOCTYPE html>
<html>
<head>
<title><jsp:invoke fragment="title" /></title>
<jsp:include page="/WEB-INF/layouts/heade.jsp" />
<jsp:invoke fragment="style" />
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<style type="text/css">
.full {
	width: 100%;
}

.gap {
	height: 30px;
	width: 100%;
	clear: both;
	display: block;
}

.footer {
	background: #EDEFF1;
	height: auto;
	padding-bottom: 30px;
	position: relative;
	width: 100%;
	border-bottom: 1px solid #CCCCCC;
	border-top: 1px solid #DDDDDD;
}

.footer p {
	margin: 0;
}

.footer img {
	max-width: 100%;
}

.footer h3 {
	border-bottom: 1px solid #BAC1C8;
	color: #54697E;
	font-size: 18px;
	font-weight: 600;
	line-height: 27px;
	padding: 40px 0 10px;
	text-transform: uppercase;
}

.footer ul {
	font-size: 13px;
	list-style-type: none;
	margin-left: 0;
	padding-left: 0;
	margin-top: 15px;
	color: #7F8C8D;
}

.footer ul li a {
	padding: 0 0 5px 0;
	display: block;
}

.footer a {
	color: #78828D
}

.supportLi h4 {
	font-size: 20px;
	font-weight: lighter;
	line-height: normal;
	margin-bottom: 0 !important;
	padding-bottom: 0;
}

.newsletter-box input#appendedInputButton {
	background: #FFFFFF;
	display: inline-block;
	float: left;
	height: 30px;
	clear: both;
	width: 100%;
}

.newsletter-box .btn {
	border: medium none;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	-o-border-radius: 3px;
	-ms-border-radius: 3px;
	border-radius: 3px;
	display: inline-block;
	height: 40px;
	padding: 0;
	width: 100%;
	color: #fff;
}

.newsletter-box {
	overflow: hidden;
}

.bg-gray {
	background-image: -moz-linear-gradient(center bottom, #BBBBBB 0%, #F0F0F0 100%);
	box-shadow: 0 1px 0 #B4B3B3;
}

.social li {
	background: none repeat scroll 0 0 #B5B5B5;
	border: 2px solid #B5B5B5;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	-o-border-radius: 50%;
	-ms-border-radius: 50%;
	border-radius: 50%;
	float: left;
	height: 36px;
	line-height: 36px;
	margin: 0 8px 0 0;
	padding: 0;
	text-align: center;
	width: 36px;
	transition: all 0.5s ease 0s;
	-moz-transition: all 0.5s ease 0s;
	-webkit-transition: all 0.5s ease 0s;
	-ms-transition: all 0.5s ease 0s;
	-o-transition: all 0.5s ease 0s;
}

.social li:hover {
	transform: scale(1.15) rotate(360deg);
	-webkit-transform: scale(1.1) rotate(360deg);
	-moz-transform: scale(1.1) rotate(360deg);
	-ms-transform: scale(1.1) rotate(360deg);
	-o-transform: scale(1.1) rotate(360deg);
}

.social li a {
	color: #EDEFF1;
}

.social li:hover {
	border: 2px solid #2c3e50;
	background: #2c3e50;
}

.social li a i {
	font-size: 16px;
	margin: 0 0 0 5px;
	color: #EDEFF1 !important;
}

.footer-bottom {
	background: #E3E3E3;
	border-top: 1px solid #DDDDDD;
	padding-top: 10px;
	padding-bottom: 10px;
}

.footer-bottom p.pull-left {
	padding-top: 6px;
}

.payments {
	font-size: 1.5em;
}
</style>
</head>
<body ng-app="mySRD">
	<jsp:include page="/WEB-INF/layouts/navbar.jsp" />
	<div class="ch-container">
		<div class="row">
			<c:if test="${not empty userInfo.logado}">
				<c:if test="${userInfo.isAdmin()}">
						<!-- left menu starts -->
						<div class="col-sm-2 col-lg-2">
						<div class="sidebar-nav">
							<div class="nav-canvas">
								<div class="nav-sm nav nav-stacked"></div>
								<ul class="nav nav-pills nav-stacked main-menu">
									<li class="nav-header"><fmt:message key="srd.label.menu" /></li>
									<li><a class="ajax-link"
										href="${linkTo[HomeController].index }"><i
											class="glyphicon glyphicon-home"></i><span> <fmt:message
													key="home" /></span></a></li>
									<li class="accordion"><a href="#"> 
									<i class="glyphicon glyphicon-plus"></i> <span><fmt:message key="home.funcionario" /></span>
									</a>
										<ul class="nav nav-pills nav-stacked">
											<li><a href="${linkTo[FuncionarioController].form}"><fmt:message
														key="srd.label.menu.novo" /></a></li>
											<li><a href="${linkTo[FuncionarioController].lista(1)}"><fmt:message
														key="srd.label.menu.lista" /></a></li>
										</ul></li>
									<li class="accordion"><a href="#"> <i
											class="glyphicon glyphicon-plus"></i> <span> <fmt:message
													key="home.usuario" /></span>
									</a>
										<ul class="nav nav-pills nav-stacked">
											<li><a href="${linkTo[UsuarioController].form}"><fmt:message
														key="srd.label.menu.novo" /></a></li>
											<li><a href="${linkTo[UsuarioController].lista(1, null)}"><fmt:message
														key="srd.label.menu.lista" /></a></li>
										</ul></li>
									<li><a class="ajax-link"
										href="${linkTo[SetorController].lista(1)}"> <i
											class="glyphicon glyphicon-th-list"></i> <span><fmt:message
													key="home.setor" /> <fmt:message key="home.listagem" /></span>
									</a></li>
									<li><a class="ajax-link"
										href="${linkTo[HomeController].historico(1,'todos', formatter.dataAtual())}">
											<i class="glyphicon glyphicon-calendar"></i> <span><fmt:message
													key="home.relatorio" /></span>
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</c:if>
			</c:if>
			<!--/span-->
			<!-- left menu ends -->
			<noscript>
				<div class="alert alert-block col-md-12">
					<h4 class="alert-heading">Warning!</h4>
					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div></div>
				<div class="row">
					<div class="col-md-11 col-md-push-1  col-xs-12">
						<jsp:invoke fragment="header" />
						<c:import url="/WEB-INF/layouts/mensagens.jsp" />
						<jsp:doBody />
					</div>
					<div id="pagefooter">
						<jsp:invoke fragment="footer" />
						<jsp:include page="/WEB-INF/layouts/footer.jsp" />
					</div>
					<div id="pagescript">
						<jsp:invoke fragment="script" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<section id="footer" style="margin-top: 10%;">
		<jsp:include page="/WEB-INF/layouts/navbar_footer.jsp" />
	</section>
</body>
</html>