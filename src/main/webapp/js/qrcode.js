angular.module('mySRD').controller('qrCrtl',
		[ '$scope', '$http', 'qrcodeService', '$timeout', '$interval', function($scope, $http, qrcodeService, $timeout, $interval) {
			$scope.acessoLiberado = false;
			$scope.acessonaoLiberado=false;
			
	
		console.log("inicializando ...........")
			
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
			$http.get('/srd/acesso/verificarqrcode/'+valores, {cache: true})
	        .then(function(res){
	            console.log(res.data.boolean);
	            
	            if(res.data.boolean){
	            	$scope.acessoLiberado = res.data.boolean;
	            }else{
	            	$scope.acessonaoLiberado = !res.data.boolean;
	            }
	        })
		}
		
			    
	    $scope.callAtInterval = function() {
	    	$scope.acessoLiberado = false;
			$scope.acessonaoLiberado=false;
	        console.log("$scope.callAtInterval - Interval occurred");
	    }

	    $interval( function(){ $scope.callAtInterval(); }, 10000, false);
}]);