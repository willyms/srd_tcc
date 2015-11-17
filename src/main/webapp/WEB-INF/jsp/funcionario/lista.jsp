<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="tt" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="myfn" uri="myfn"%>
<tt:template>
	<jsp:attribute name="style">
		<style type="text/css">
				/* Profile container */
				.profile {
				  margin: 20px 0;
				}
				
				/* Profile sidebar */
				.profile-sidebar {
				  padding: 20px 0 10px 0;
				  background: #0077B2;
				}
				
				.profile-userpic img {
				  float: none;
				  margin: 0 auto;
				  width: 50%;
				  height: 50%;
				  -webkit-border-radius: 50% !important;
				  -moz-border-radius: 50% !important;
				  border-radius: 50% !important;
				}
				
				.profile-usertitle {
				  text-align: center;
				  margin-top: 20px;
				}
				
				.profile-usertitle-name {
				  color: black;
				  font-size: 16px;
				  font-weight: 600;
				  margin-bottom: 7px;
				}
				
				.profile-usertitle-job {
				  text-transform: uppercase;
				  color: #5b9bd1;
				  font-size: 12px;
				  font-weight: 600;
				  margin-bottom: 15px;
				}
				
				.profile-userbuttons {
				  text-align: center;
				  margin-top: 10px;
				}
				
				.profile-userbuttons .btn {
				  text-transform: uppercase;
				  font-size: 11px;
				  font-weight: 600;
				  padding: 6px 15px;
				  margin-right: 5px;
				}
				
				.profile-userbuttons .btn:last-child {
				  margin-right: 0px;
				}
				    
				.profile-usermenu {
				  margin-top: 30px;
				}
				
				.profile-usermenu ul li {
				  border-bottom: 1px solid #f0f4f7;
				}
				
				.profile-usermenu ul li:last-child {
				  border-bottom: none;
				}
				
				.profile-usermenu ul li a {
				  color: #93a3b5;
				  font-size: 14px;
				  font-weight: 400;
				}
				
				.profile-usermenu ul li a i {
				  margin-right: 8px;
				  font-size: 14px;
				}
				
				.profile-usermenu ul li a:hover {
				  background-color: #fafcfd;
				  color: #5b9bd1;
				}
				
				.profile-usermenu ul li.active {
				  border-bottom: none;
				}
				
				.profile-usermenu ul li.active a {
				  color: #5b9bd1;
				  background-color: #f6f9fb;
				  border-left: 2px solid #5b9bd1;
				  margin-left: -2px;
				}
				
				/* Profile Content */
				.profile-content {
				  padding: 20px;
				  background: #fff;
				  min-height: 460px;
				}
		</style>
		
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
		<script type="text/javascript">
		$('#modalDetalhes').on('show.bs.modal', function (event) {
			  var button = $(event.relatedTarget) // Button that triggered the modal
			  var recipient = button.data('whatever') // Extract info from data-* attributes
			  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
			  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.			  
			  var dados = [button.data('lista-setores')];
			  $("#result").html(dados);
			  var modal = $(this)
			  modal.find('.modal-title').text('Ficha detalhado do Funcionário ' + recipient);
			  modal.find('.modal-body input').val(recipient);
			  validarCampocheck ();
			});

		function validarCampocheck () {
		    $('.list-group.checked-list-box .list-group-item').each(function () {
		        
		        // Settings
		        var $widget = $(this),
		            $checkbox = $('<input type="checkbox" class="hidden" />'),
		            color = ($widget.data('color') ? $widget.data('color') : "primary"),
		            style = ($widget.data('style') == "button" ? "btn-" : "list-group-item-"),
		            settings = {
		                on: {
		                    icon: 'glyphicon glyphicon-check'
		                },
		                off: {
		                    icon: 'glyphicon glyphicon-unchecked'
		                }
		            };
		            
		        $widget.css('cursor', 'pointer')
		        $widget.append($checkbox);

		        // Event Handlers
		        $widget.on('click', function () {
		            $checkbox.prop('checked', !$checkbox.is(':checked'));
		            $checkbox.triggerHandler('change');
		            updateDisplay();
		        });
		        $checkbox.on('change', function () {
		            updateDisplay();
		        });
		          

		        // Actions
		        function updateDisplay() {
		            var isChecked = $checkbox.is(':checked');

		            // Set the button's state
		            $widget.data('state', (isChecked) ? "on" : "off");

		            // Set the button's icon
		            $widget.find('.state-icon')
		                .removeClass()
		                .addClass('state-icon ' + settings[$widget.data('state')].icon);

		            // Update the button's color
		            if (isChecked) {
		                $widget.addClass(style + color + ' active');
		            } else {
		                $widget.removeClass(style + color + ' active');
		            }
		        }

		        // Initialization
		        function init() {
		            
		            if ($widget.data('checked') == true) {
		                $checkbox.prop('checked', !$checkbox.is(':checked'));
		            }
		            
		            updateDisplay();

		            // Inject the icon if applicable
		            if ($widget.find('.state-icon').length == 0) {
		                $widget.prepend('<span class="state-icon ' + settings[$widget.data('state')].icon + '"></span>');
		            }
		        }
		        init();
		    });
		    
		    $('#get-checked-data').on('click', function(event) {
		        event.preventDefault(); 
		        var checkedItems = {}, counter = 0;
		        $("#check-list-box li.active").each(function(idx, li) {
		            checkedItems[counter] = $(li).text();
		            counter++;
		        });
		        $('#display-json').html(JSON.stringify(checkedItems, null, '\t'));
		    });
		};
		
		</script>
		<!-- input type check lista de setores janela modal -->
		<script type="text/javascript">
				</script>
	</jsp:attribute>
	<jsp:attribute name="header">
		<ul class="breadcrumb">
        	<li><a href="${linkTo[HomeController].index }"><fmt:message key="home"/></a><span class="divider"></span></li>
        	<li><fmt:message key="home.funcionario"/><span class="divider"></span></li>
			<li class="active"><fmt:message key="home.listagem"/></li>
		</ul>	
	</jsp:attribute>
	<jsp:body>	
		<%-- <c:forEach items="${lista_funcionario}" var="funcionario">
			<li>${funcionario.id}|${funcionario.nome}</li>
		</c:forEach>	
		 --%>
		 <div class="col-lg-12 col-lg-offset-4">
		 	<ul id="pagination-demo" class="pagination"></ul>
		 </div>
	<div class="row">
	<c:forEach items="${lista_funcionario}" var="f">			
		<div class="col-md-3" style="margin-top: 2%;">
			<div class="profile-sidebar">
				<!-- SIDEBAR USERPIC -->
				<div class="profile-userpic">
					<img src="${linkTo[FuncionarioController].image(f.arquivo.id)}" class="img-responsive" alt="">
				</div>
				<!-- END SIDEBAR USERPIC -->
				<!-- SIDEBAR USER TITLE -->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name">
						${f.nome}
					</div>
					<div class="profile-usertitle-job">
						
					</div>
				</div>
				<!-- END SIDEBAR USER TITLE -->
				<!-- SIDEBAR BUTTONS -->
				<div class="profile-userbuttons">
					<a href="${linkTo[FuncionarioController].formedit(f.id)}" type="button" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-edit"></span>
					&nbsp;<fmt:message key="srd.botao.editar"/></a>
					<button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#modalDetalhes" 
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
						<span class="glyphicon glyphicon-list-alt"></span>&nbsp;<fmt:message key="srd.botao.detalher"/></button>
				</div>
				<!-- END SIDEBAR BUTTONS -->
				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
					<ul class="nav">
						<li class="active">
							<a href="${linkTo[PDFController].pdfReport(f.id)}">
							<i class="glyphicon glyphicon-file"></i>
								<fmt:message key="srd.label.botao.gerarcracha" />
							</a>
						</li>
						<li class="fixclear"></li>
						<c:if test="${formatter.Eadministrator(f.id)}">
						<li class="active">
							<a href="${linkTo[UsuarioController].tornarAdmin(f.id)}">
							<i class="glyphicon glyphicon-file"></i>
								<fmt:message key="srd.label.botao.tornaadmin" />
							</a>
						</li>	
						</c:if>						
					</ul>
				</div>
				<!-- END MENU -->
			</div>
		</div>
		</c:forEach>
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