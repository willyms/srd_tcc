<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tt:template>
	<jsp:attribute name="title">
		<fmt:message key="home.setor" /> <fmt:message key="home.formulario" />
	</jsp:attribute>
	<jsp:attribute name="header">
		<ul class="breadcrumb">
        	<li><a href="${linkTo[HomeController].index }"><fmt:message key="home"/></a><span class="divider"></span></li>
        	<li><fmt:message key="home.setor"/><span class="divider"></span></li>
			<li class="active"><fmt:message key="home.formulario"/></li>
		</ul>	
	</jsp:attribute>
	<jsp:attribute name="style">
		<style type="text/css">
				@import('https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.0/css/bootstrap.min.css') 

				.funkyradio div {
				  clear: both;
				  overflow: hidden;
				}
				
				.funkyradio label {
				  width: 100%;
				  border-radius: 3px;
				  border: 1px solid #D1D3D4;
				  font-weight: normal;
				}
				
				.funkyradio input[type="radio"]:empty,
				.funkyradio input[type="checkbox"]:empty {
				  display: none;
				}
				
				.funkyradio input[type="radio"]:empty ~ label,
				.funkyradio input[type="checkbox"]:empty ~ label {
				  position: relative;
				  line-height: 2.5em;
				  text-indent: 3.25em;
				  margin-top: 2em;
				  cursor: pointer;
				  -webkit-user-select: none;
				     -moz-user-select: none;
				      -ms-user-select: none;
				          user-select: none;
				}
				
				.funkyradio input[type="radio"]:empty ~ label:before,
				.funkyradio input[type="checkbox"]:empty ~ label:before {
				  position: absolute;
				  display: block;
				  top: 0;
				  bottom: 0;
				  left: 0;
				  content: '';
				  width: 2.5em;
				  background: #D1D3D4;
				  border-radius: 3px 0 0 3px;
				}
				
				.funkyradio input[type="radio"]:hover:not(:checked) ~ label,
				.funkyradio input[type="checkbox"]:hover:not(:checked) ~ label {
				  color: #888;
				}
				
				.funkyradio input[type="radio"]:hover:not(:checked) ~ label:before,
				.funkyradio input[type="checkbox"]:hover:not(:checked) ~ label:before {
				  content: '\2714';
				  text-indent: .9em;
				  color: #C2C2C2;
				}
				
				.funkyradio input[type="radio"]:checked ~ label,
				.funkyradio input[type="checkbox"]:checked ~ label {
				  color: #777;
				}
				
				.funkyradio input[type="radio"]:checked ~ label:before,
				.funkyradio input[type="checkbox"]:checked ~ label:before {
				  content: '\2714';
				  text-indent: .9em;
				  color: #333;
				  background-color: #ccc;
				}
				
				.funkyradio input[type="radio"]:focus ~ label:before,
				.funkyradio input[type="checkbox"]:focus ~ label:before {
				  box-shadow: 0 0 0 3px #999;
				}
				
				.funkyradio-default input[type="radio"]:checked ~ label:before,
				.funkyradio-default input[type="checkbox"]:checked ~ label:before {
				  color: #333;
				  background-color: #ccc;
				}
				
				.funkyradio-primary input[type="radio"]:checked ~ label:before,
				.funkyradio-primary input[type="checkbox"]:checked ~ label:before {
				  color: #fff;
				  background-color: #337ab7;
				}
				
				.funkyradio-success input[type="radio"]:checked ~ label:before,
				.funkyradio-success input[type="checkbox"]:checked ~ label:before {
				  color: #fff;
				  background-color: #5cb85c;
				}
				
				.funkyradio-danger input[type="radio"]:checked ~ label:before,
				.funkyradio-danger input[type="checkbox"]:checked ~ label:before {
				  color: #fff;
				  background-color: #d9534f;
				}
				
				.funkyradio-warning input[type="radio"]:checked ~ label:before,
				.funkyradio-warning input[type="checkbox"]:checked ~ label:before {
				  color: #fff;
				  background-color: #f0ad4e;
				}
				
				.funkyradio-info input[type="radio"]:checked ~ label:before,
				.funkyradio-info input[type="checkbox"]:checked ~ label:before {
				  color: #fff;
				  background-color: #5bc0de;
				}		
		</style>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script type="text/javascript">

		</script>
	</jsp:attribute>
	<jsp:body>
		<form action="${linkTo[SetorController].alterar }" method="post">
			<input type="hidden" name="setor.id" value="${setor.id}" />
			<fieldset class="form-group">
   				 <label for="formGroupExampleInput2">CPF</label>
   					 <input value="${setor.nome}" ng-required="true" type="text" maxlength="50" max="50" min="3" name="setor.nome" class="form-control" id="formGroupExampleInput2"  
							required="required" />
  				<span class="error">${errors.from('setor.nome').join(' - ')}</span>
  				<span class="error">${errors.from('setor.duplicado').join(' - ')}</span>
  			</fieldset>
  			<fieldset>  				
  				<div class="col-md-5">
  					<label for="formGroupExampleInput2">Status</label>  				
				<div class="funkyradio">
				 
			        <div class="funkyradio-primary">
			            <input type="radio" name="setor.ativo" id="radio1" ${setor.ativo = true ? 'checked' : '' } value="true"/>
			            <label for="radio1">Ativo</label>
			        </div>
			        <div class="funkyradio-danger">
			            <input type="radio" name="setor.ativo" id="radio2" ${setor.ativo = false ? 'checked' : '' } value="false"/>
			            <label for="radio2">Desativo</label>
					</div>
		        </div>
		        </div>
  			</fieldset>
  			<br />
  			<div class="col-md-offset-5 col-xs-offset-1">
	  			<button type="submit" class="btn btn-primary"><fmt:message key="srd.botao.submit"/></button>
	  			<a href="${linkTo[SetorController].lista(1)}" type="button" class="btn btn-warning" ><fmt:message key="srd.botao.cancelar"/></a>
  			</div>
		</form>
	</jsp:body>	
</tt:template>