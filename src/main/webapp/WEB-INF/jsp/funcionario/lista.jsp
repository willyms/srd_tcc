<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="myfn" uri="myfn"%>
<tt:template>
	<jsp:attribute name="style">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/srd/css/style_funcionario_lista.css">
	</jsp:attribute>	
	<jsp:attribute name="title">
		<fmt:message key="home.funcionario" /> <fmt:message key="home.listagem" />
	</jsp:attribute>
	<jsp:attribute name="script">	
		<script src="${pageContext.request.contextPath}/js/jquery.twbsPagination.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.twbsPagination.min.js"></script>		
		<script>
		 $('#pagination-demo').twbsPagination({
			    href :  "${pageContext.request.contextPath}/funcionario/lista/{{number}}",
		        totalPages:"${totalpagina}",
		        visiblePages: 7,
		        startPage: 1,
			     first:'',
			     next:'Próximo',
			     prev:'Anterior',  
			     last:'',		    		     
		        onPageClick: function (event, page) {
		            $('#page-content').text('Page ' + page);
		        }
		    });
		</script>
		<script type="text/javascript"  src="${pageContext.request.contextPath}/srd/js/script_funcionario_lista.js"></script>
	</jsp:attribute>
	<jsp:attribute name="header">
		<ul class="breadcrumb">
        	<li><a href="${linkTo[HomeController].index }"><fmt:message key="home"/></a><span class="divider"></span></li>
        	<li><fmt:message key="home.funcionario"/><span class="divider"></span></li>
			<li class="active"><fmt:message key="home.listagem"/></li>
		</ul>	
	</jsp:attribute>
	<jsp:body>	
		<div class="row">	
			 <div class="col-md-12 col-md-offset-4 col-xs-12 col-xs-offset-2">
			 	<ul id="pagination-demo" class="pagination"></ul>
			 </div>
		 </div>
		<div class="row">
			  <section id="pinBoot">
				<c:forEach items="${lista_funcionario}" var="f" varStatus="var">			
					<article class="white-panel">
						<img src="${linkTo[FuncionarioController].image(f.arquivo.id)}" alt="">
				        <h5><a href="#">${fn:toUpperCase(f.nome)}</a></h5>
				        <div class="profile-usermenu">
							<ul class="nav">
								<li id="item_${var.count}_1">
									<a href="${linkTo[FuncionarioController].formedit(f.id)}" onmouseover="javascript:ativa_mouse(${var.count}+'_1');" onmouseout="desativa_mouse(${var.count}+'_1');">
										<i class="glyphicon glyphicon-edit"></i>
											&nbsp;<fmt:message key="srd.botao.editar"/>
									</a>
								</li>
								<li id="item_${var.count}_2">
									<a href="#" onmouseover="javascript:ativa_mouse(${var.count}+'_2');" onmouseout="desativa_mouse(${var.count}+'_2');"
										data-toggle="modal" data-target="#modalDetalhes" 
											data-whatever="${f.nome }"
											data-lista-setores='							
					  					  <div class="row">
					  					  	<div class="col-sm-12">
					  					  		<div class="col-sm-8"><strong>Nome</strong> : ${f.nome }</div>
					  					  		<div class="col-sm-4"><strong>CPF</strong> : ${f.cpf }</div>
					  					  	</div>
					  					  	<div class="col-sm-12">
					  					  		<div class="col-sm-3"><strong>Data Início</strong>: <tt:localDate date="${f.dataentrada }"></tt:localDate></div>
					  					  		<div class="col-sm-3"><strong>Data de Término</strong>: <tt:localDate date="${f.datasaida }"></tt:localDate></div>
					  					  		<div class="col-sm-3"><strong>Horário Início</strong>: ${f.horaentrada }</div>
					  					  		<div class="col-sm-3"><strong>Horário de Término</strong>: ${f.horasaida } </div>
					  					  	</div>
					  					  	<div class="col-sm-12">
												 <div class="col-xs-10 col-xs-offset-1">
					           						 <h3 class="text-center">Setores</h3>
					            				<div class="well" style="max-height: 300px;overflow: auto;">
					        						<ul class="list-group checked-list-box">
					        							<c:forEach items="${s}" var="s"> 
					        							 <li class="list-group-item" data-style="button"  data-checked="${myfn:contains(f.acesso, s.nome)}">${s.nome}</li>        				  				
					        							</c:forEach>
					               					 </ul>
					           				   </div>
					                       </div>
											</div>  			
										'>
										<span class="glyphicon glyphicon-list-alt"></span>&nbsp;<fmt:message key="srd.botao.detalher"/>
									</a>
								</li>
								<li id="item_${var.count}_3">
									<a href="${linkTo[PDFController].pdfReport(f.id)}" onmouseover="javascript:ativa_mouse(${var.count}+'_3');" onmouseout="desativa_mouse(${var.count}+'_3');">
										<i class="glyphicon glyphicon-file"></i>
											<fmt:message key="srd.label.botao.gerarcracha" />
									</a>
								</li>
								<li class="fixclear"></li>
								<c:if test="${formatter.Eadministrator(f.id)}">
									<li id="item_${var.count}_4">
										<a href="${linkTo[UsuarioController].tornarAdmin(f.id)}" onmouseover="javascript:ativa_mouse(${var.count}+'_4');" onmouseout="desativa_mouse(${var.count}+'_4');">
										<i class="glyphicon glyphicon-file"></i>
											<fmt:message key="srd.label.botao.tornaadmin" />
										</a>
									</li>	
								</c:if>	
							</ul>
						</div>		
				        </article>
				</c:forEach>
			  </section>
			  <script type="text/javascript">
				function ativa_mouse(id) {					
					document.getElementById("item_"+id).className = "active";
				}
				function desativa_mouse(id) {					
					document.getElementById("item_"+id).className = "";	
				}
				</script>	
			</div>
			<div class="modal fade" id="modalDetalhes" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="exampleModalLabel"></h4>
			      </div>
			      <div class="modal-body">
			        <form>          
			          <div class="form-group">
			            	<div id="result"></div>
			          </div>
			        </form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="srd.botao.close"/></button>       
			      </div>
			    </div>
			  </div>
			</div>	
	</jsp:body>
</tt:template>