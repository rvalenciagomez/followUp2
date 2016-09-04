$(function () {
		  $("#saveEditCliente").click(function(){
			  $("#frmEditCliente").submit();
		  });
		  
		  $("#deleteCliente").click(function(e){
			  var result = confirm("Estas seguro que deseas eliminar el clientes?");
			  
			  if(result == true)
			  {
			    $("#frmDeleteCliente").submit();		  
			  } 
			  else
			  {
			    e.preventDefault();
			  }  
		  });
		  
		  
		 
	  });

$(document).ready(function(){
	
	if($("#esEmpresa").is(":checked")) 
    {
    	$('#empresaLabel').addClass('hidden');
        $('#empresaDiv').addClass('hidden');
        
    }
	
	 $("body").on("change","#esEmpresa", function() {
	        if($(this).is(":checked")) 
	        {
	        	$('#empresaLabel').addClass('hidden');
	            $('#empresaDiv').addClass('hidden');
	            
	        }
	        if(!$(this).is(":checked")) 
	        {
	        	$('#empresaLabel').removeClass('hidden');
	            $('#empresaDiv').removeClass('hidden');
	            
	        }
	    });
	 
	 
});

