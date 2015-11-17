angular.module('mySRD').factory('setorService',  function($resource) {
	 
		return $resource($SERVICES_CONTEXT +'setor/:params',{},{			
			id:{
				method: 'GET',
				params: {params: 'id'},
				isArray :true
			}
		});
	
});