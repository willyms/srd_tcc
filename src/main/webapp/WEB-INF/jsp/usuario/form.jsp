<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<tt:template>
	<jsp:attribute name="title">
		<fmt:message key="home.usuario" /> <fmt:message key="home.formulario" />
	</jsp:attribute>
	<jsp:attribute name="header">
		<ul class="breadcrumb">
        	<li><a href="${linkTo[HomeController].index }"><fmt:message key="home"/></a><span class="divider"></span></li>
        	<li><fmt:message key="home.usuario"/><span class="divider"></span></li>
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
				
				.popover.primary {
							    border-color:#337ab7;
							}
							.popover.primary>.arrow {
							    border-top-color:#337ab7;
							}
							.popover.primary>.popover-title {
							    color:#fff;
							    background-color:#337ab7;
							    border-color:#337ab7;
							}
							.popover.success {
							    border-color:#d6e9c6;
							}
							.popover.success>.arrow {
							    border-top-color:#d6e9c6;
							}
							.popover.success>.popover-title {
							    color:#3c763d;
							    background-color:#dff0d8;
							    border-color:#d6e9c6;
							}
							.popover.info {
							    border-color:#bce8f1;
							}
							.popover.info>.arrow {
							    border-top-color:#bce8f1;
							}
							.popover.info>.popover-title {
							    color:#31708f;
							    background-color:#d9edf7;
							    border-color:#bce8f1;
							}
							.popover.warning {
							    border-color:#faebcc;
							}
							.popover.warning>.arrow {
							    border-top-color:#faebcc;
							}
							.popover.warning>.popover-title {
							    color:#8a6d3b;
							    background-color:#fcf8e3;
							    border-color:#faebcc;
							}
							.popover.danger {
							    border-color:#ebccd1;
							}
							.popover.danger>.arrow {
							    border-top-color:#ebccd1;
							}
							.popover.danger>.popover-title {
							    color:#a94442;
							    background-color:#f2dede;
							    border-color:#ebccd1;
							}
							.error-text{
								background-color: #C0C0C0;
								color: #fff;
								font-weight: bold;
							}
							
		</style>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script type="text/javascript">		
			//minimum 8 characters
			var bad = /(?=.{8,}).*/;
			//Alpha Numeric plus minimum 8
			var good = /^(?=\S*?[a-z])(?=\S*?[0-9])\S{8,}$/;
			//Must contain at least one upper case letter, one lower case letter and (one number OR one special char).
			var better = /^(?=\S*?[A-Z])(?=\S*?[a-z])((?=\S*?[0-9])|(?=\S*?[^\w\*]))\S{8,}$/;
			//Must contain at least one upper case letter, one lower case letter and (one number AND one special char).
			var best = /^(?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9])(?=\S*?[^\w\*])\S{8,}$/;

			$('#passoriginal').on('keyup', function () {
			    var password = $(this);
			    var pass = password.val();
			    var passLabel = $('[for="passoriginal"]');
			    var stength = 'Fraco';
			    var pclass = 'danger';
			    if (best.test(pass) == true) {
			        stength = 'Muito forte';
			        pclass = 'success';
			    } else if (better.test(pass) == true) {
			        stength = 'Forte';
			        pclass = 'warning';
			    } else if (good.test(pass) == true) {
			        stength = 'Quase forte';
			        pclass = 'warning';
			    } else if (bad.test(pass) == true) {
			        stength = 'Fraco';
			    } else {
			        stength = 'Muito fraco';
			    }

			    var popover = password.attr('data-content', stength).data('bs.popover');
			    popover.setContent();
			    popover.$tip.addClass(popover.options.placement).removeClass('danger success info warning primary').addClass(pclass);

			});

			$('input[data-toggle="popover"]').popover({
			    placement: 'top',
			    trigger: 'focus'
			});

		</script>
		<script type="text/javascript">
				var password = document.getElementById("passoriginal")
				  , confirm_password = document.getElementById("passconfimar");
		
				function validatePassword(){
				  if(password.value != confirm_password.value) {					 
				    confirm_password.setCustomValidity("Confirmar Senha Diferente de Senha!");				    
				  } else {
				    confirm_password.setCustomValidity('');
				  }
				}
		
				password.onchange = validatePassword;
				confirm_password.onkeyup = validatePassword;
		</script>
		<script type="text/javascript">
			jQuery.fn.filterByText = function(textbox, selectSingleMatch) {
		        return this.each(function() {
		            var select = this;
		            var options = [];
		            $(select).find('option').each(function() {
		                options.push({value: $(this).val(), text: $(this).text()});
		            });
		            $(select).data('options', options);
		            $(textbox).bind('change keyup', function() {
		                var options = $(select).empty().data('options');
		                var search = $(this).val().trim();
		                var regex = new RegExp(search,"gi");
		              
		                $.each(options, function(i) {
		                    var option = options[i];
		                    if(option.text.match(regex) !== null) {
		                        $(select).append(
		                           $('<option>').text(option.text).val(option.value)
		                        );
		                    }
		                });
		                if (selectSingleMatch === true && $(select).children().length === 1) {
		                    $(select).children().get(0).selected = true;
		                }
		            });            
		        });
		    };
	
		    $(function() {
		        $('#select').filterByText($('#textbox'), false);
		      $("select option").click(function(){
		        alert(1);
		      });
		    });
		</script>
		<script src="${pageContext.request.contextPath}/js/controllerUsuario.js"></script>
	</jsp:attribute>
	<jsp:body>	
		<div ng-controller="ctrlUsuario">		
			<form name="demoForm" method="POST" action="${linkTo[UsuarioController].gravar}">
				<c:if test="${not empty f.id or not empty u.funcionario.id}">
					<input type="hidden" name="usuario.funcionario.id" value="${empty f.id ? u.funcionario.id : f.id }"  />
				</c:if>
				<input type="hidden" name="id" value="${empty u.id ? null : u.id }" />
				<fieldset class="form-group">
   					 <label for="usuario"><fmt:message key="srd.label.username"/></label>
   						 <input value="${u.username}" ng-required="true" type="text" maxlength="15" name="usuario.username" class="form-control" id="usuario"
							title="" required x-moz-errormessage=""  autofocus="autofocus" />
						<span class="error">${errors.from('funcionario.ativo').join(' - ')}</span>  	
  						<span class="error">${errors.from('usuario.username').join(' - ')}</span>  	
  				</fieldset> 
  				<fieldset class="form-group">
   					 <label for="passoriginal"><fmt:message key="srd.label.pass"/></label>
   						 <input value="${u.password}" ng-required="true" type="password" maxlength="15" name="usuario.password" class="form-control" id="passoriginal" 
							required data-toggle="popover" title="Nivel da Senha" data-content="Digiter sua senha..." x-moz-errormessage="" />
  						<span class="error">${errors.from('usuario.password').join(' - ')}</span>  	
  				</fieldset> 
  				
  				<fieldset class="form-group">
   					 <label for="passconfimar"><fmt:message key="srd.label.pass.verif"/></label>
   						 <input value="${u.passVerify}" ng-required="true" type="password" maxlength="15"  name="usuario.passVerify" class="form-control" id="passconfimar" 
							title="" required x-moz-errormessage="" />
  						<span class="error">${errors.from('usuario.passVerify').join(' - ')}</span>  	 	
  				</fieldset> 
  			
  				<fieldset class="form-group">
   					 <label for="perfil"><fmt:message key="srd.label.perfil"/></label>
   					  <div class="funkyradio">
	   					<div class="funkyradio-default">
	   						 <c:choose>
		   					  	<c:when test="${f.id != null}">
		   					  		<input type="radio" name="usuario.perfil" id="ADMIN" data-ng-model="my.perfil" value="ADMIN"  ng-required="true" ng-checked="true" />
									<label for="ADMIN"><fmt:message key="srd.label.perfil.admin"/></label>
		   					  	</c:when>		   					  
		   					  	 <c:otherwise>
							        <input type="radio" name="usuario.perfil" id="USER" data-ng-model="my.perfil" value="USER"  ng-required="true" ng-checked="true" />
									<label for="USER"><fmt:message key="srd.label.perfil.user"/></label>
							      </c:otherwise>
		   					 </c:choose>
						 </div>		
   					 </div>
  					<span class="error"></span>  	
  				</fieldset> 
  				
  				<fieldset class="form-group" >
   					 <label for="perfil"><fmt:message key="srd.label.status"/></label>
   					  <div class="funkyradio">	   					
   						 <div class="funkyradio-primary">
   							<input type="radio" value="TRUE" name="usuario.ativo" id="ativo" ng-checked="true"/>
   							<label for="ativo"><fmt:message key="srd.label.status.ativo"/></label>
   						 </div>
   						 <div class="funkyradio-primary">
   							<input type="radio" value="FALSE" name="usuario.ativo" id="desativo"/>
   							<label for="desativo"><fmt:message key="srd.label.status.desativo"/></label>
   						 </div>
   					 </div>
  						<span class="error"></span>  	
  				</fieldset> 
  				  				
  				<c:if test="${empty f.id and empty u.funcionario.id}">
	  				<fieldset class="form-group">
	  					<label for="textbox"><fmt:message key="srd.label.filtro"/></label>
	  					<input id="textbox" class="form-control" type="text" placeholder="digiter o nome para filtar na seleção..." />
						<label for="textbox"><fmt:message key="srd.label.setores"/></label>	
						<select id="select" name="usuario.setor.id" class="form-control" ng-required="true">
							<option class="1" value="${u.setor.ativo ? u.setor.id : null}">${u.setor.nome}</option>
							<c:forEach items="${lista_setor}" var="s">
								 <option class="${s.ativo ? '' : 'error-text'}" value="${s.id}">${s.nome}${s.ativo ? '' : '  desativado'}</option>	
							</c:forEach>									  
						  </select>
						  <span class="error">${errors.from('setor.nome').join(' - ')}</span> 
	  				</fieldset>
  				</c:if>
  				 <div class="col-md-offset-5 col-xs-offset-1">
		  			<button type="submit" class="btn btn-primary"><fmt:message key="srd.botao.submit"/></button>
		  			<a href="${linkTo[UsuarioController].lista(1,null)}" type="button" class="btn btn-warning" ><fmt:message key="srd.botao.cancelar"/></a>
  				</div> 
			</form>
		</div>
	</jsp:body>	
</tt:template>