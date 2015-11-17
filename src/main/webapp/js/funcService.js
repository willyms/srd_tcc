angular.module('mySRD').factory('funcService',  function($resource) {
	 
		return $resource($SERVICES_CONTEXT +'funcionario/todos/:params',{},{			
			tipos:{
				method: 'GET',
				params: {params: 'todos'},
				isArray :true
			}
		});
	
});