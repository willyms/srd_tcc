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
				
				/* nova estilo de imagem */
				#pinBoot {
					  position: relative;
					  max-width: 100%;
					  width: 100%;
					}
					img {
					  width: 100%;
					  max-width: 100%;
					  height: auto;
					}
					.white-panel {
					  position: absolute;
					  background: white;
					  box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.3);
					  padding: 10px;
					}
					/*
					stylize any heading tags withing white-panel below
					*/
					
					.white-panel h1 {
					  font-size: 1em;
					}
					.white-panel h1 a {
					  color: #A92733;
					}
					.white-panel:hover {
					  box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.5);
					  margin-top: -5px;
					  -webkit-transition: all 0.3s ease-in-out;
					  -moz-transition: all 0.3s ease-in-out;
					  -o-transition: all 0.3s ease-in-out;
					  transition: all 0.3s ease-in-out;
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
		<script type="text/javascript">
		$(document).ready(function() {
			$('#pinBoot').pinterest_grid({
			no_columns: 4,
			padding_x: 10,
			padding_y: 10,
			margin_bottom: 50,
			single_column_breakpoint: 700
			});
			});

			/*
			Ref:
			Thanks to:
			http://www.jqueryscript.net/layout/Simple-jQuery-Plugin-To-Create-Pinterest-Style-Grid-Layout-Pinterest-Grid.html
			*/


			/*
			    Pinterest Grid Plugin
			    Copyright 2014 Mediademons
			    @author smm 16/04/2014

			    usage:

			     $(document).ready(function() {

			        $('#blog-landing').pinterest_grid({
			            no_columns: 4
			        });

			    });


			*/
			;(function ($, window, document, undefined) {
			    var pluginName = 'pinterest_grid',
			        defaults = {
			            padding_x: 10,
			            padding_y: 10,
			            no_columns: 3,
			            margin_bottom: 50,
			            single_column_breakpoint: 700
			        },
			        columns,
			        $article,
			        article_width;

			    function Plugin(element, options) {
			        this.element = element;
			        this.options = $.extend({}, defaults, options) ;
			        this._defaults = defaults;
			        this._name = pluginName;
			        this.init();
			    }

			    Plugin.prototype.init = function () {
			        var self = this,
			            resize_finish;

			        $(window).resize(function() {
			            clearTimeout(resize_finish);
			            resize_finish = setTimeout( function () {
			                self.make_layout_change(self);
			            }, 11);
			        });

			        self.make_layout_change(self);

			        setTimeout(function() {
			            $(window).resize();
			        }, 500);
			    };

			    Plugin.prototype.calculate = function (single_column_mode) {
			        var self = this,
			            tallest = 0,
			            row = 0,
			            $container = $(this.element),
			            container_width = $container.width();
			            $article = $(this.element).children();

			        if(single_column_mode === true) {
			            article_width = $container.width() - self.options.padding_x;
			        } else {
			            article_width = ($container.width() - self.options.padding_x * self.options.no_columns) / self.options.no_columns;
			        }

			        $article.each(function() {
			            $(this).css('width', article_width);
			        });

			        columns = self.options.no_columns;

			        $article.each(function(index) {
			            var current_column,
			                left_out = 0,
			                top = 0,
			                $this = $(this),
			                prevAll = $this.prevAll(),
			                tallest = 0;

			            if(single_column_mode === false) {
			                current_column = (index % columns);
			            } else {
			                current_column = 0;
			            }

			            for(var t = 0; t < columns; t++) {
			                $this.removeClass('c'+t);
			            }

			            if(index % columns === 0) {
			                row++;
			            }

			            $this.addClass('c' + current_column);
			            $this.addClass('r' + row);

			            prevAll.each(function(index) {
			                if($(this).hasClass('c' + current_column)) {
			                    top += $(this).outerHeight() + self.options.padding_y;
			                }
			            });

			            if(single_column_mode === true) {
			                left_out = 0;
			            } else {
			                left_out = (index % columns) * (article_width + self.options.padding_x);
			            }

			            $this.css({
			                'left': left_out,
			                'top' : top
			            });
			        });

			        this.tallest($container);
			        $(window).resize();
			    };

			    Plugin.prototype.tallest = function (_container) {
			        var column_heights = [],
			            largest = 0;

			        for(var z = 0; z < columns; z++) {
			            var temp_height = 0;
			            _container.find('.c'+z).each(function() {
			                temp_height += $(this).outerHeight();
			            });
			            column_heights[z] = temp_height;
			        }

			        largest = Math.max.apply(Math, column_heights);
			        _container.css('height', largest + (this.options.padding_y + this.options.margin_bottom));
			    };

			    Plugin.prototype.make_layout_change = function (_self) {
			        if($(window).width() < _self.options.single_column_breakpoint) {
			            _self.calculate(true);
			        } else {
			            _self.calculate(false);
			        }
			    };

			    $.fn[pluginName] = function (options) {
			        return this.each(function () {
			            if (!$.data(this, 'plugin_' + pluginName)) {
			                $.data(this, 'plugin_' + pluginName,
			                new Plugin(this, options));
			            }
			        });
			    }

			})(jQuery, window, document);

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
		<div class="row">	
			 <div class="col-lg-12 col-lg-offset-4">
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
			<div id="conteudo" style="display:none;">Conteudo da DIV</div>
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