<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tt:template>
	<jsp:attribute name="title">
		<fmt:message key="home.funcionario"/> <fmt:message key="home.formulario"/>
	</jsp:attribute>
	<jsp:attribute name="header">
		<ul class="breadcrumb">
        	<li><a href="${linkTo[HomeController].index }"><fmt:message key="home"/></a><span class="divider"></span></li>
        	<li><fmt:message key="home.funcionario"/><span class="divider"></span></li>
			<li class="active"><fmt:message key="home.formulario"/></li>
		</ul>	
	</jsp:attribute>
	<jsp:attribute name="style">	
	<link href="${pageContext.request.contextPath}/js/datetimepicker/css/datepicker.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/js/datetimepicker/css/jasny-bootstrap.min.css" rel="stylesheet">
		<style type="text/css">
			@import url('http://cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2.css');
			@import url('http://cdnjs.cloudflare.com/ajax/libs/select2/3.4.8/select2-bootstrap.css');			                                   
			.timepicker-picker .table-condensed td{text-align:center} 
			.callout-light {
						    padding: 30px;
							color: #000;
							background-color: #ccc;
						}
						
						.callout-light h1,
						h2,
						h3,
						h4 {
							font-weight: 300;
							line-height: 1.4;
						}
		</style>		
	</jsp:attribute>
	<jsp:attribute name="script">
		<%-- <script src="${pageContext.request.contextPath}/js/file.dir.js" ></script> --%>
		
		
		<script src="${pageContext.request.contextPath}/js/setorService.js"></script>
		<script src="${pageContext.request.contextPath}/js/controllerFun.js"></script>
		
		<%-- <script src="${pageContext.request.contextPath}/js/datetimepicker/select2.min.js"></script> --%>
		<script src="${pageContext.request.contextPath}/js/datetimepicker/bootstrap-datepicker.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/datetimepicker/moment.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/datetimepicker/jasny-bootstrap.min.js"></script>
		
		<script type="text/javascript">
		// init jquery functions and plugins
		$(document).ready(function(){
		    $('#datepicker').datepicker({
		      autoclose:true,
		      format: 'dd/mm/yyyy',
		      language: 'pt-BR'
		    }).on("changeDate", function(e){
		      
		    });
		    $('#datepicker2').datepicker({
			      autoclose:true,
			      format: 'dd/mm/yyyy',
			      language: 'pt-BR'
			    }).on("changeDate", function(e){
			     
			    });
		    
		    $('.input-daterange').datepicker({
		      autoclose:true,
		      language: 'pt-BR'
		    }).on("changeDate", function(e){
		    
		    });
		    
		    $('#timepicker').datetimepicker({
		    	format: 'HH:mm',
		        pickDate: false,
		        pickSeconds: false,
		        pick12HourFormat: false,
		        language: 'pt-BR'
		    });
		    $('#timepicker2').datetimepicker({
		    	format: 'HH:mm',
		        pickDate: false,
		        pickSeconds: false,
		        pick12HourFormat: false,
		        language: 'pt-BR'
		    });
		    
		    $("input").bind('keyup change',function() {
				var $t = $(this);
		        var $par = $t.parent();
		        var min = $t.attr("data-valid-min");
		        var match = $t.attr("data-valid-match");
		      	var pattern = $t.attr("pattern");
		               
		        if (typeof match!="undefined"){
		            if ($t.val()!=$('#'+match).val()) {
		                $par.removeClass('has-success').addClass('has-error');
		            }
		            else {
		                $par.removeClass('has-error').addClass('has-success');
		            }
		        }
		      	else if (!this.checkValidity()) {
		      		$par.removeClass('has-success').addClass('has-error');
		        }
		        else {
		            $par.removeClass('has-error').addClass('has-success');
		        }
		      	
		      	if ($par.hasClass("has-success")) {
		        	$par.find('.form-control-feedback').removeClass('fade');
		      	}
		 		else {
		        	$par.find('.form-control-feedback').addClass('fade');
		      	}
		      
			});});//script

			function readURL(input) {
		        if (input.files && input.files[0]) {
		            var reader = new FileReader();
		            
		            reader.onload = function (e) {
		                $('#blah').attr('src', e.target.result);
		            }
		            
		            reader.readAsDataURL(input.files[0]);
		        }
		    }
		    
		    $("#fileImage").change(function(){
		        readURL(this);
		    });
		</script>		
	</jsp:attribute>
	<jsp:body>	
	<div ng-init="funcionarioId=${not empty f.id? f.id : 'null'};required=${not empty f.id? false : true}">	
	      <div class="row">
			<c:if test="${not empty errors}">
  				   <div class="alert alert-warning">
  				   	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
     			   <c:forEach var="error" items="${errors}">
  						<strong>Aviso :</strong>  ${error.message} <br />.
     			   </c:forEach>
   				 </div>
			</c:if>
	  </div> 	
	  		<c:if test="${empty f.id}">
				<form method="POST" action="${linkTo[FuncionarioController].novo }" enctype="multipart/form-data">
				<div class="col-sm-offset-4 col-xs-offset-4 col-md-offset-4">
    				     <img id="blah" src="${pageContext.request.contextPath}/charisma-master/img/user-group.png" style="width: 30%" class="img-thumbnail " />
    			</div> 
			</c:if>
			<c:if test="${not empty f.id}">
				<div class="row ">
		   			<div class="col-sm-4 col-sm-offset-0">							
						<a data-toggle="tooltip" title="Funcionário ${f.ativo ? 'Ativado' : 'Desativado'}, para ${f.ativo ? 'Desativar' : 'Ativar'} clicar aqui." href="${f.ativo ? linkTo[FuncionarioController].desativar(f.id) : linkTo[FuncionarioController].ativar(f.id)}" class="btn ${f.ativo ? 'btn-success' : 'btn-danger' } btn-sm">
							<span class="glyphicon ${f.ativo ? 'glyphicon-ok' : 'glyphicon-remove'}"></span>
							&nbsp; ${f.ativo ? 'Ativado':'Desativado' }
						</a>
					</div>		   		
				</div>
				<form name="demoForm" method="POST" action="${linkTo[FuncionarioController].editar}" enctype="multipart/form-data">
					<input type="hidden" name="_method" value="put" />
					<input type="hidden" name="funcionario.id" value="${f.id}" />
					<input type="hidden" name="funcionario.arquivo.id" value="${f.arquivo.id}" />
					<%-- <input type="hidden" name="funcionario.img" value="${f.img}" /> --%>
					<div class="col-sm-offset-4 col-xs-offset-4"">
    				     <img id="blah" src="${linkTo[FuncionarioController].image(f.arquivo.id)}" style="width: 30%" class="img-thumbnail col-xs-offset-1" />
    			</div> 
					
			</c:if>
			
  				<fieldset class="form-group">    				
    				    <label for="formGroupExampleInput"><fmt:message key="srd.label.foto"/></label>
    				    <input id="fileImage" type="file" name="Filedata" class="form-control " accept="image/*"  ng-required="{{ required }}"  data-file="param.file"  />   						
  						<span class="error">${errors.from('arquivo.contentType').join(' - ')}</span>
  						<!--  <div>param.file: {{param.file}}</div>  -->      				
  				</fieldset>
			
  				<fieldset class="form-group">
    				<label for="formGroupExampleInput"><fmt:message key="srd.label.nome"/></label>
    					<input value="${f.nome}" ng-required="true" type="text" name="funcionario.nome" class="form-control" id="formGroupExampleInput" pattern="[a-zA-Z\s]+$"  required x-moz-errormessage="Ops. Não esqueça de preencher este campo."  />
  						<span class="error">${errors.from('funcionario.nome').join(' - ')}</span>  						
  				</fieldset>
  				
  				<fieldset class="form-group">
   					 <label for="formGroupExampleInput2"><fmt:message key="srd.label.cpf"/></label>
   						 <input value="${f.cpf}" ng-required="true" type="text" maxlength="15" name="funcionario.cpf" class="form-control" id="formGroupExampleInput2" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" 
							title="Digite um CPF no formato: xxx.xxx.xxx-xx." required x-moz-errormessage="Digite um CPF no formato: xxx.xxx.xxx-xx." />
  						<span class="error">${errors.from('funcionario.cpf').join(' - ')}</span>  	
  				</fieldset>  				
  				
  				<div  ng-controller="ctrSetor" class="card card-block">
  					<div class="row">
  						<h4 class="card-title"><fmt:message key="srd.label.setor"/></h4>
  						<div class="form-group col-sm-8">    
    						<input maxlength="30" pattern="[a-zA-Z0-9]+" type="text" ng-model="setor.nome" class="form-control"  />    					
  							<span class="error">${errors.from('funcionario.acesso.setor.nome').join(' - ')}</span>  
  						</div>
  						<button type="button" ng-click="adicionaSetor()" class="btn btn-primary col-sm-offset-0 col-xs-offset-3"><fmt:message key="srd.botao.adicionarSetor"/></button>
  					</div>  					  		  					
  					<div class="row">
  						<h5>Lista de Setores</h5>
  						<div class="well well-sm text-center">
							<div ng-repeat="setor in lista_setores" class="btn-group" data-toggle="buttons">			
								<label class="btn btn-default {{setor.check ? 'active' :'' }} ">								
									<input type="checkbox" autocomplete="off" name="funcionario.acesso[{{ lista_setores.indexOf(setor) }}].setor.nome" value="{{ setor.nome }}" ng-checked="{{setor.check}}">
									<span class="glyphicon glyphicon-ok"></span> {{ setor.nome }}
								</label>
							</div>
						</div>	
					</div>
				</div>
				<br />
				<fieldset class="form-group col-md-6 col-sm-6 col-xs-12">
						<div id="exDateTime">      						
          					<label class="control-label"><fmt:message key="srd.label.data.inicio"/></label>
          					
          					<div class="input-group date" id="datepicker">
	          					<c:choose>
	          					<c:when test="${ not empty f.dataentrada }">
	            					<input value="<tt:localDate date="${f.dataentrada}" />" class="form-control " name="funcionario.dataentrada" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" />
	          					</c:when>
	          					<c:otherwise>
	            					<input  class="form-control" name="funcionario.dataentrada"  pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$"/>          					
	          					</c:otherwise>
	          					</c:choose>
            				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>   
      						</div><!--/col-->
      					</div>
      					
      		  </fieldset>
    		  <fieldset class="form-group col-md-6 col-sm-6 col-xs-12"> 
         			 <label class="control-label"><fmt:message key="srd.label.hora.inicio"/></label>
       				 <div class="input-group" id="timepicker">
      				      <input value="${f.horaentrada }" type="text" class="form-control " name="funcionario.horaentrada" />
      				      <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span> 
     				</div><!--/col-->
    		  </fieldset>
    		  <fieldset class="form-group col-md-6 col-sm-6 col-xs-12">
    		  	<span class="error">${errors.from('funcionario.dataentrada').join(' - ')}</span><br />
				<div id="exDateTime">      						
	       			<label class="control-label"><fmt:message key="srd.label.data.termino"/></label>
	       				<div class="input-group date" id="datepicker2">
	        				<c:choose>
	        					<c:when test="${ not empty f.datasaida }">
	          						<input  value="<tt:localDate date="${f.datasaida}" />" class="form-control" name="funcionario.datasaida" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$" />          					
	        					</c:when>
	        					<c:otherwise>
	          						<input class="form-control" name="funcionario.datasaida"  pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$"/>          					
	        					</c:otherwise>
	        				</c:choose>
	           				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>   
	   					</div><!--/col-->
	   			</div>
	   			<span class="error">${errors.from('funcionario.datasaida').join(' - ')}</span><br />
      		  </fieldset>
    		  <fieldset class="form-group col-md-6 col-sm-6 col-xs-12"> 
         			 <label class="control-label"><fmt:message key="srd.label.hora.termino"/></label>
       				 <div class="input-group" id="timepicker2">
      				      <input value="${f.horasaida}" type="text" class="form-control" name="funcionario.horasaida" />
      				      <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span> 
     				</div><!--/col-->
     				<span class="error">${errors.from('funcionario.horasaida').join(' - ')}</span><br />
    		  </fieldset>		
    		  		<div class="clearfix"></div>
  				 <div class="col-md-offset-5 col-xs-offset-1 clearfix">
		  			<button type="submit" class="btn btn-primary"><fmt:message key="srd.botao.submit"/></button>
		  			<a href="${linkTo[HomeController].index}" type="button" class="btn btn-warning" ><fmt:message key="srd.botao.cancelar"/></a>
  				</div> 	

			</form>	
			</div>
	</jsp:body>
</tt:template>