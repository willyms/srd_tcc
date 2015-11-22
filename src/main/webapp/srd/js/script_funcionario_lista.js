/**
 * 
 */
$('#modalDetalhes').on(
		'show.bs.modal',
		function(event) {
			var button = $(event.relatedTarget) // Button that triggered the
												// modal
			var recipient = button.data('whatever') // Extract info from data-*
													// attributes
			// If necessary, you could initiate an AJAX request here (and then
			// do the updating in a callback).
			// Update the modal's content. We'll use jQuery here, but you could
			// use a data binding library or other methods instead.
			var dados = [ button.data('lista-setores') ];
			$("#result").html(dados);
			var modal = $(this)
			modal.find('.modal-title').text(
					'Ficha detalhado do Funcionário ' + recipient);
			modal.find('.modal-body input').val(recipient);
			validarCampocheck();
		});

function validarCampocheck() {
	$('.list-group.checked-list-box .list-group-item')
			.each(
					function() {

						// Settings
						var $widget = $(this), $checkbox = $('<input type="checkbox" class="hidden" />'), color = ($widget
								.data('color') ? $widget.data('color')
								: "primary"), style = ($widget.data('style') == "button" ? "btn-"
								: "list-group-item-"), settings = {
							on : {
								icon : 'glyphicon glyphicon-check'
							},
							off : {
								icon : 'glyphicon glyphicon-unchecked'
							}
						};

						$widget.css('cursor', 'pointer')
						$widget.append($checkbox);

						// Event Handlers
						$widget.on('click',
								function() {
									$checkbox.prop('checked', !$checkbox
											.is(':checked'));
									$checkbox.triggerHandler('change');
									updateDisplay();
								});
						$checkbox.on('change', function() {
							updateDisplay();
						});

						// Actions
						function updateDisplay() {
							var isChecked = $checkbox.is(':checked');

							// Set the button's state
							$widget.data('state', (isChecked) ? "on" : "off");

							// Set the button's icon
							$widget
									.find('.state-icon')
									.removeClass()
									.addClass(
											'state-icon '
													+ settings[$widget
															.data('state')].icon);

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
								$checkbox.prop('checked', !$checkbox
										.is(':checked'));
							}

							updateDisplay();

							// Inject the icon if applicable
							if ($widget.find('.state-icon').length == 0) {
								$widget.prepend('<span class="state-icon '
										+ settings[$widget.data('state')].icon
										+ '"></span>');
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

$(document).ready(function() {
	$('#pinBoot').pinterest_grid({
		no_columns : 4,
		padding_x : 10,
		padding_y : 10,
		margin_bottom : 50,
		single_column_breakpoint : 700
	});
});

/*
 * Ref: Thanks to:
 * http://www.jqueryscript.net/layout/Simple-jQuery-Plugin-To-Create-Pinterest-Style-Grid-Layout-Pinterest-Grid.html
 */

/*
 * Pinterest Grid Plugin Copyright 2014 Mediademons @author smm 16/04/2014
 * 
 * usage:
 * 
 * $(document).ready(function() {
 * 
 * $('#blog-landing').pinterest_grid({ no_columns: 4 });
 * 
 * });
 * 
 * 
 */
;
(function($, window, document, undefined) {
	var pluginName = 'pinterest_grid', defaults = {
		padding_x : 10,
		padding_y : 10,
		no_columns : 3,
		margin_bottom : 50,
		single_column_breakpoint : 700
	}, columns, $article, article_width;

	function Plugin(element, options) {
		this.element = element;
		this.options = $.extend({}, defaults, options);
		this._defaults = defaults;
		this._name = pluginName;
		this.init();
	}

	Plugin.prototype.init = function() {
		var self = this, resize_finish;

		$(window).resize(function() {
			clearTimeout(resize_finish);
			resize_finish = setTimeout(function() {
				self.make_layout_change(self);
			}, 11);
		});

		self.make_layout_change(self);

		setTimeout(function() {
			$(window).resize();
		}, 500);
	};

	Plugin.prototype.calculate = function(single_column_mode) {
		var self = this, tallest = 0, row = 0, $container = $(this.element), container_width = $container
				.width();
		$article = $(this.element).children();

		if (single_column_mode === true) {
			article_width = $container.width() - self.options.padding_x;
		} else {
			article_width = ($container.width() - self.options.padding_x
					* self.options.no_columns)
					/ self.options.no_columns;
		}

		$article.each(function() {
			$(this).css('width', article_width);
		});

		columns = self.options.no_columns;

		$article
				.each(function(index) {
					var current_column, left_out = 0, top = 0, $this = $(this), prevAll = $this
							.prevAll(), tallest = 0;

					if (single_column_mode === false) {
						current_column = (index % columns);
					} else {
						current_column = 0;
					}

					for (var t = 0; t < columns; t++) {
						$this.removeClass('c' + t);
					}

					if (index % columns === 0) {
						row++;
					}

					$this.addClass('c' + current_column);
					$this.addClass('r' + row);

					prevAll.each(function(index) {
						if ($(this).hasClass('c' + current_column)) {
							top += $(this).outerHeight()
									+ self.options.padding_y;
						}
					});

					if (single_column_mode === true) {
						left_out = 0;
					} else {
						left_out = (index % columns)
								* (article_width + self.options.padding_x);
					}

					$this.css({
						'left' : left_out,
						'top' : top
					});
				});

		this.tallest($container);
		$(window).resize();
	};

	Plugin.prototype.tallest = function(_container) {
		var column_heights = [], largest = 0;

		for (var z = 0; z < columns; z++) {
			var temp_height = 0;
			_container.find('.c' + z).each(function() {
				temp_height += $(this).outerHeight();
			});
			column_heights[z] = temp_height;
		}

		largest = Math.max.apply(Math, column_heights);
		_container.css('height', largest
				+ (this.options.padding_y + this.options.margin_bottom));
	};

	Plugin.prototype.make_layout_change = function(_self) {
		if ($(window).width() < _self.options.single_column_breakpoint) {
			_self.calculate(true);
		} else {
			_self.calculate(false);
		}
	};

	$.fn[pluginName] = function(options) {
		return this
				.each(function() {
					if (!$.data(this, 'plugin_' + pluginName)) {
						$.data(this, 'plugin_' + pluginName, new Plugin(this,
								options));
					}
				});
	}

})(jQuery, window, document);
