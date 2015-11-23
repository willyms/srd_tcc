angular.module('mySRD').controller('qrCrtl',
		[ '$scope', '$http', 'qrcodeService', '$timeout', '$interval', '$cacheFactory', 
		  function($scope, $http, qrcodeService, $timeout, $interval, $cacheFactory ) {
			$scope.acessoLiberado = false;
			$scope.acessonaoLiberado=false;
			$scope.lerCodigo=true;
			
		$scope.onSuccess = function(data) {
			//console.log("Sucesso" + data);
			verificarServlet(data);
		};
		$scope.onError = function(error) {
			 //console.log("Error " + error);
		};
		$scope.onVideoError = function(error) {
			// console.log("Error video" + error);
		};
	
		function verificarServlet(valores) {
			if($scope.lerCodigo){
			var url = "/srd/acesso/verificarqrcode/"+valores;
			$http.get(url, {cache: false})
			.success(function(result){		
	            if(result.boolean){
	            	$scope.acessoLiberado = result.boolean;
	            }else{
	            	$scope.acessonaoLiberado = !result.boolean;
	            }
	            $scope.lerCodigo=false;
	        });
			}
		}
			    
	    $scope.callAtInterval = function() {
	    	$scope.acessoLiberado = false;
			$scope.acessonaoLiberado=false;
			$scope.lerCodigo=true;
	    }

	    $interval( function(){ $scope.callAtInterval(); }, 10000, false);
}]);