<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<tt:template>
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
				<style type="text/css">
						.agenda {
							
						} /* Dates */
						.agenda .agenda-date {
							width: 170px;
						}
						
						.agenda .agenda-date .dayofmonth {
							width: 40px;
							font-size: 36px;
							line-height: 36px;
							float: left;
							text-align: right;
							margin-right: 10px;
						}
						
						.agenda .agenda-date .shortdate {
							font-size: 0.75em;
						} /* Times */
						.agenda .agenda-time {
							width: 140px;
						} /* Events */
						.agenda .agenda-events {
							
						}
						
						.agenda .agenda-events .agenda-event {
							
						}
						
						@media ( max-width : 767px) {
						}
				</style>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script type="text/javascript">
		<script src="${pageContext.request.contextPath}/js/jquery.twbsPagination.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.twbsPagination.min.js"></script>		
		<script type="text/javascript">
		$('#pagination-demo').twbsPagination({
			 href: "${pageContext.request.contextPath}/dashboard/historico/{{number}}/${id_funcionario}/${data}",
			 totalPages: "${total_pagina}",
		     visiblePages: 4,
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
		    });
		</script>
	</jsp:attribute>
	<jsp:attribute name="title">
		<fmt:message key="home" />
	</jsp:attribute>
	<jsp:attribute name="header">
		<ul class="breadcrumb">
        	<li class="active"><fmt:message key="home" /></li>
		</ul>	
	</jsp:attribute>
	<jsp:body> 	
		<div class="col-md-12 col-xs-12"> 
				<div class="col-md-5">
				<c:if test="${not empty  historico}">
					<h4>Historico de Acesso - <tt:localDate
								date="${historico.get(0).dataentrada}" pattern="MMMMMMM" /> - <tt:localDate
								date="${historico.get(0).dataentrada}" pattern="YYYY" />
						</h4> 				
				</c:if>		
				</div>
				<div class="col-md-6">				
					<ul id="pagination-demo" class="pagination"></ul>
				</div>
				<hr class="col-md-10" />
				<div class="row">
					<div class="col-md-offset-0 col-md-12 col-xs-12 col-sm-12">
				            <form action="${linkTo[HomeController].formFilter}" method="post">
				               <div class="col-md-5">
				                    <label class="control-label">Funcionario :</label>
				                    <input class="form-control" id="system-search"
													name="nome" value="${filter}" placeholder="Digiter o nome do funcionario ..." autofocus="autofocus">
				               </div>
				               <div class="col-md-5">
				                    <div class="row" id="exDateTime">      						
			          					<label class="control-label">Data Acesso :</label>
			          					<div class="input-group date" id="datepicker">			          					
			            					<input  class="form-control" name="dataentrada"  pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}$"/>			            				
			            				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>   
			      						</div><!--/col-->
			      					</div>
				                </div>
				                <div class="col-md-12 col-xs-12 col-sm-12"></div>
				                <div class="col-md-offset-4 col-md-5"> 
					                   <div class="input-group">
					                     <span class="input-group-btn">				                   
					                        <button type="submit" class="btn btn-default">
															<i class="glyphicon glyphicon-search"></i> Buscar</button>
					                    
					                        <button type="reset" class="btn btn-warning">
															<i class="glyphicon glyphicon-retweet"></i> Limpar
											</button>									
					                    </span>
					               	 </div>	
				               	</div>                
				            </form>
		            </div>
		        </div>	
		        <br />
		        <div class="row agenda"> 
					<div class="table-responsive"> 
						<table class="table table-condensed table-bordered "> 
							<thead> 
								<tr> 
									<th>Data</th> 
									<th>Hora</th> 
									<th class="text-center">Evento</th> 
								</tr> 
							</thead> 
							<tbody> 
							<!-- Single event in a single day --> 								
								<c:if test="${not empty  historico}">
									<c:forEach items="${historico}" var="h" begin="0" end="30"
									step="1">									
									<tr> 
										<td class="agenda-date" class="active"
											rowspan="${formatter.retornaQuantidadeFuncionario(h.dataentrada, pagina, id_funcionario)}"> 
											<div class="dayofmonth">
												<tt:localDate date="${h.dataentrada}" pattern="dd" />
											</div> 
											<div class="dayofweek">
												<tt:localDate date="${h.dataentrada}" pattern="E" />
											</div> 
											<div class="shortdate text-muted">
												<tt:localDate date="${h.dataentrada}" pattern="MMMMMMM" />, <tt:localDate
													date="${h.dataentrada}" pattern="YYYY" />
											</div> 
										</td>
									</tr>											
										<c:forEach items="${historico2}" var="h2">
											<c:if test="${h.dataentrada == h2.dataentrada}">
											<tr> 
												<td class="agenda-time">${h2.horaentrada} - ${h2.horasaida} </td>  
													<td class="agenda-events  ${h2.liberado ? '' :'danger'}"> 
													<div class="agenda-event"> 
														<fmt:message
															key="src.mensagem.acesso.${h2.liberado ? 'liberado' : 'naoliberado'}">
															<fmt:param value="${h2.funcionario.nome }" />
															<fmt:param value="${h2.setor.nome}" />
														</fmt:message>
													</div>
												</td>  
											</tr>
											</c:if>
										</c:forEach>									 										 										
									</c:forEach>
								</c:if>
							</tbody> 
						</table> 
					</div> 
				</div> 
			</div>		
	</jsp:body>
</tt:template>