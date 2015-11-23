angular.module('mySRD').factory('setorService',  function($resource) {
	 
		return $resource('/srd/setor/:params',{},{			
			id:{
				method: 'GET',
				params: {params: 'id'},
				isArray :true
			}
		});
	
});