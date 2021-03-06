<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tt:template>
	<jsp:attribute name="title">
		<fmt:message key="home.setor" /> <fmt:message key="home.listagem" />
	</jsp:attribute>
	<jsp:attribute name="style">
		<style type="text/css">
			.filterable {
				margin-top: 15px;
			}
			
			.filterable .panel-heading .pull-right {
				margin-top: -20px;
			}
			
			.filterable .filters input[disabled] {
				background-color: transparent;
				border: none;
				cursor: auto;
				box-shadow: none;
				padding: 0;
				height: auto;
			}
			
			.filterable .filters input[disabled]::-webkit-input-placeholder {
				color: #333;
			}
			
			.filterable .filters input[disabled]::-moz-placeholder {
				color: #333;
			}
			
			.filterable .filters input[disabled]:-ms-input-placeholder {
				color: #333;
			}
	  </style>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script src="${pageContext.request.contextPath}/js/controllerSetor.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.twbsPagination.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.twbsPagination.min.js"></script>		
		<script>
		$('#pagination-demo').twbsPagination({
			 href: "${pageContext.request.contextPath}/setor/lista/{{number}}",
			 totalPages: "${totalpagina}",
		     visiblePages: 4,
		     startPage: 1,
		     first:'',
		     next:'Pr�ximo',
		     prev:'Anterior',  
		     last:'',		    		     
	        onPageClick: function (event, page) {
	            $('#page-content').text('Page ' + page);
	        }
	    });
		</script>
	</jsp:attribute>
	<jsp:attribute name="header">
		 <ul class="breadcrumb">
        	<li><a href="${linkTo[HomeController].index }"><fmt:message key="home"/></a><span class="divider"></span></li>
        	<li><fmt:message key="home.setor"/><span class="divider"></span></li>
			<li class="active"><fmt:message key="home.listagem"/></li>
		</ul>		
	</jsp:attribute>
	<jsp:body>	
		<div ng-controller="setorController">	
		 <div class="panel panel-primary filterable">
            <div class="panel-heading">
               
               
            </div>
            <br />	      
			<div class="row">
					<div class="col-md-offset-2 col-md-7 col-xs-12 col-sm-12">
				            <form action="${linkTo[SetorController].filterLista}" method="post">
				                <div class="input-group">
				                    <!-- USE TWITTER TYPEAHEAD JSON WITH API TO SEARCH -->
				                    <input class="form-control" id="system-search"
													name="nome" value="${filter}" placeholder="Digiter o nome do setor ..." required>
				                    <span class="input-group-btn">
				                   
				                        <button type="submit" class="btn btn-default">
														<i class="glyphicon glyphicon-search"></i> Buscar</button>
				                     <c:if test="${empty filter }">	
				                        <button type="reset" class="btn btn-warning">
														<i class="glyphicon glyphicon-retweet"></i> Limpar
										</button>
									</c:if>
									<c:if test="${not empty filter }">					
										<a href="${linkTo[SetorController].lista(1)}" class="btn btn-primary">
											<i class="glyphicon glyphicon-retweet"></i> Voltar
										</a>
									</c:if>
				                    </span>
				                </div>				                
				            </form>
		            </div>
		        </div>		
		      <c:choose>
		      	<c:when test="${not empty lista_setores}">		      	
					  <table class="table table-striped col-md-12 col-xs-10 col-sm-10">
					    <thead>
					    <tr>
					    	<th class="col-sm-0 col-xs-0 col-md-1 text-center">#</th>
					    	<th>Nome</th>
					    	<th class="col-sm-0 col-xs-0 col-md-1 text-center">Status</th>
					    	<th class="col-sm-0 col-xs-0 col-md-1 text-center">Editar</th> 	
					    </tr>
					    </thead>				   
					    <tbody>				     
					      <c:forEach items="${lista_setores}" var="s" varStatus="index">
					      		<tr>
					        		<td class="col-sm-0 col-xs-0 col-md-1 text-center">${index.count}</td>
					        		<td>${s.nome }</td>
					        		<td class="col-sm-0 col-xs-0 col-md-1 text-center">${s.ativo ? 'Ativo' : 'Desativado' }</td>
					        		<td class="col-sm-0 col-xs-0 col-md-1 text-center">
						        		<p data-placement="top" data-toggle="tooltip" title="Edit">
											<a href="${ linkTo[SetorController].form(s.id)}" class="btn btn-primary btn-xs" data-title="Edit">
													<span class="glyphicon glyphicon-pencil"></span>
											</a>
										</p>
									</td>
					      		</tr>
					      </c:forEach>
					    </tbody>
					  </table>
					  	<ul id="pagination-demo" class="pagination"></ul>
		      	</c:when>
		      	<c:otherwise>
		      		<h2>Alerta !</h2>
		  			<div class="well">
						<h2>${mensagem_resultado}, cadastre no formul�rio de funcionario <a
						href="${linkTo[FuncionarioController].form}"><abbr>aqui</abbr> .</a>
					</h2>
					</div>		      	
		      	</c:otherwise>
		      </c:choose>  
			</div>	
		</div>	  
    <div class="modal fade" id="delete" tabindex="-1" role="dialog"
			aria-labelledby="edit" aria-hidden="true">
      <div class="modal-dialog">
    <div class="modal-content">
          <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</button>
        <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
      </div>
          <div class="modal-body">
       
       <div class="alert alert-danger">
							<span class="glyphicon glyphicon-warning-sign"></span> Tem certeza de que deseja excluir este registro?</div>
       
      </div>
        <div class="modal-footer ">
        <button type="button" class="btn btn-success">
							<span class="glyphicon glyphicon-ok-sign"></span>�SIM</button>
        <button type="button" class="btn btn-default"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove"></span>�N�O</button>
      </div>
        </div>    
  </div>      
    </div> 
	</jsp:body>
</tt:template>