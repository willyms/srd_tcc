angular.module('mySRD').controller('ctrFuncionario',
		function($scope, funcService) {
			$scope.lista_funcionarios = [];

			function buscasTodos() {
				funcService.query(function(lista_funcionarios) {
					console.log(lista_funcionarios);
					$scope.lista_funcionarios = list
				})

				setorService.query(function(lista_setores) {
					console.log(lista_setores);
					$scope.lista_setores = lista_setores;
				}), function(erro) {
					console.log(erro);
				}
			}
		});

angular.module('mySRD').directive(
		'checkFileSize',
		function() {
			return {
				link : function(scope, elem, attr, ctrl) {
					function bindEvent(element, type, handler) {
						if (element.addEventListener) {
							element.addEventListener(type, handler, false);
						} else {
							element.attachEvent('on' + type, handler);
						}
					}

					bindEvent(elem[0], 'change', function() {
						alert('File size:' + this.files[0].size + "tipo :"
								+ this.files[0].type);
						scope.messagemInputFile = "testestes";
						scope.$apply();
					});
				}
			}
		});

angular.module('mySRD').directive('file', function() {
	return {
		scope : {
			file : '='
		},
		link : function(scope, el, attrs) {
			el.bind('change', function(event) {
				var files = event.target.files;
				var file = files[0];
				console.log(file);
				scope.file = file ? file.name : undefined;

				scope.$apply();
			});
		}
	};
});

angular.module('mySRD').controller(
		'ctrSetor',
		function($scope, setorService, $http) {

			$scope.formSetor = false;
			$scope.lista_setores = [];
			$scope.listaTmp = [];
			$scope.updateFile = true;
			$scope.param = {};
			$scope.clear = function() {
				$scope.file = null;
			}

			$scope.adicionaSetor = function() {
				if ($scope.setor.nome == null || $scope.setor.nome == "") {
					return null;
				}
				if (validar($scope.setor.nome)) {
					$scope.lista_setores.push({
						nome : $scope.setor.nome,
						ativo : true,
						check : false
					});
				} else {
					return null;
				}

				$scope.setor.nome = '';

			}

			function validar(nome) {
				for (var i = 0; i < $scope.lista_setores.length; i++) {
					if ($scope.lista_setores[i].nome == nome) {
						return false;
					}
				}
				return true;
			}
			
            ///Listar todos do banco de dados
			function buscarTodos() {
				/*setorService.query(function(lista_setores) {
					for (var i = 0; i < lista_setores.length; i++) {
						$scope.lista_setores.push({
							nome : lista_setores[i].nome,
							ativo : lista_setores[i].ativo,
							check : false
						});
					}
				}), function(erro) {
					console.log(erro);
				}*/
				var url = "/srd/setor/";
				$http.get(url, {cache: false})				
				.success(function(result){		
					for (var i = 0; i < result.length; i++) {
						$scope.lista_setores.push({
							nome : result[i].nome,
							ativo : result[i].ativo,
							check : false
						});
					}
		        })
		        .error(function(error){
		        	console.log(error);
		        });
			}
			/// listar por id do funcionario 
			if ($scope.funcionarioId != null) {
				var url = "/srd/setor/";
				$http.get(url, {cache: false})				
				.success(function(result){		
					for (var i = 0; i < result.length; i++) {
						$scope.lista_setores.push({
							nome : result[i].nome,
							ativo : result[i].ativo,
							check : false
						});
					}
		        })
		        .error(function(error){
		        	console.log(error);
		        });
				/*setorService.query({
					params : $scope.funcionarioId
				}, function(setores) {
					for (var i = 0; i < $scope.lista_setores.length; i++) {		
						console.log($scope.lista_setores[i].nome +"->"+ VerficicarSeSetorEstaVinculoComFuncionario($scope.lista_setores[i].nome, setores));
							$scope.listaTmp.push({
								nome : $scope.lista_setores[i].nome,
								ativo : $scope.lista_setores[i].ativo,
								check :  VerficicarSeSetorEstaVinculoComFuncionario($scope.lista_setores[i].nome, setores)
							});
					
					}
					$scope.lista_setores = $scope.listaTmp;
				}, function(erro) {
					console.log("erro" + erro);
				})*/
				var urlid = "/srd/setor/"+$scope.funcionarioId;
				$http.get(urlid, {cache: false})				
				.success(function(result){		
					console.log("validando dados....");
					for (var i = 0; i < result.length; i++) {	
						console.log(result[i].nome +"->"+ VerficicarSeSetorEstaVinculoComFuncionario(result[i].nome, $scope.lista_setores));
						$scope.listaTmp.push({
							nome : result[i].nome,
							ativo :result[i].ativo,
							check :  VerficicarSeSetorEstaVinculoComFuncionario(result[i].nome, result)
						});
					}
					$scope.lista_setores = $scope.listaTmp;
		        })
		        .error(function(error){
		        	console.log(error);
		        });				
			}
			
			function VerficicarSeSetorEstaVinculoComFuncionario(param, setorDofuncionario){
				for (var y = 0; y < setorDofuncionario.length; y++) {
					if(angular.equals(param,setorDofuncionario[y].nome)){
						return true;
					}
				}
				return false;
			}
			
			buscarTodos();

		});
