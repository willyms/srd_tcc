angular.module('mySRD').factory('qrcodeService',  function($resource) {
	 
		return $resource($SERVICES_CONTEXT +'acesso/verificarqrcode/:params',{},{			
			tipos:{
				method: 'GET',
				params: {params: 'todos'},
				isArray :true
			}
		});
	
});