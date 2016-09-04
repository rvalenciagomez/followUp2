 	 
 	  $(document).ready(function() {
 		  // En Edit Oportunidad - poner una fecha y con el formato correcto
//   	    $("#fechaAccion").datepicker({dateFormat:"dd/mm/yy", timeFormat: "hh:mm tt"});
   	    $("#fechaCierre").datepicker({dateFormat:"dd/mm/yy"});
   	    });

 	

	  $(function () {
		  
		  $("#fechaAccion").datetimepicker({
			  dateFormat:"dd/mm/yy",
			  timeFormat: "HH:mm",
			  oneLine: true,
			  controlType: 'select',
			  hourMin: 6,
			  hourMax: 22
		  });
		    
		 
		  		
		        
//		       DE PAGINA EDIT OPORTUNIDADES
		  $("#saveEditOportunidad").click(function(e){
			  validateForm(e);
			  $("#frmEditOportunidad").submit();
		  });
		  
		  $("#deleteOportunidad").click(function(e){
			  var result = confirm("Estas seguro que deseas eliminar la oportunidad?");
			  
			  if(result == true)
			  {
			    $("#frmDeleteOportunidad").submit();		  
			  } 
			  else
			  {
			    e.preventDefault();
			  }  
		  });
		  
		  
		  
});  // -----------------finaliza funicion GENEARAL ---------------------------------
	  
	  
	  
//	   - - VALIDACIONES - - - 
	  function validateForm(e) {
		    var x = document.forms["#frmEditOportunidad"]["#telefono"].value;
		    if (x == null || x == "") {
		        alert("Name must be filled out");
		        e.preventDefault();
		        return false;
		    }
		}
		  
		
	  function saveOportunity(oportIdVal, etapaIdVal)
	  {
		  $.ajax({
       		  url:"oportunidades/"+oportIdVal,
       		 method:"post",
			 type:"json",
       		  data:{"oportId": oportIdVal, "etapaId": etapaIdVal},
       	      success: function (oportunidad) {
       	    	  console.log(oportunidad);
       	      },
       	      error: function (oportunidad) {
       	    	  console.log("there was an error");
       	      }
       	   });
	  }
	  
	 
	