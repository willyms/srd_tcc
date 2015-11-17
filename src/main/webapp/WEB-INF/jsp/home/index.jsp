<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<tt:template>
	<jsp:attribute name="style">
		<style type="text/css">
			.agenda { } /* Dates */ .agenda .agenda-date { width: 170px; } .agenda .agenda-date .dayofmonth { width: 40px; font-size: 36px; line-height: 36px; float: left; text-align: right; margin-right: 10px; } .agenda .agenda-date .shortdate { font-size: 0.75em; } /* Times */ .agenda .agenda-time { width: 140px; } /* Events */ .agenda .agenda-events { } .agenda .agenda-events .agenda-event { } @media (max-width: 767px) { }
		</style>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script type="text/javascript">
			
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
	  <div class="col-md-12">		  
	      <div class="row">
	          <div class="col-md-12 column">              
	              <div class="row flix">	                  
				      <c:if test="${not empty lista_setores}">
				      		<c:forEach items="${lista_setores}" var="s">
				      		<div class="col-md-4 column">
                    		  <div class="list-group">
				      			 <div class="list-group-item">
		                              <h4 class="list-group-item-heading">
		                                 ${s.nome}
		                                  <a href="#"  data-toggle="tooltip" data-placement="right" title="<fmt:message key="src.label.detalher" ><fmt:param value="${s.ativo ? 'ativo':'desativo'}" /><fmt:param value="${formatter.localDateTime(s.dataCriacao)}" /></fmt:message>">
		                                    <small><i class="glyphicon glyphicon-${s.ativo ? 'ok' : 'remove' }"></i></small>
		                                  </a>
		                              </h4>
		                              <!-- label-success -->
		                              <p class="list-group-item-text">
		                                  <span class="label label-${s.ativo ? 'success' :  'danger' }"><fmt:message key="src.label.${s.ativo ? 'ativo' : 'desativo' }" /></span>
		                              </p>
		                          </div>
		                          </div>
				     			</div>
				      		</c:forEach>
				      	</c:if>
					</div>	
				</div>		
	      </div>	      
	    </div> 			
	</jsp:body>
</tt:template>