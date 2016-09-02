 	 
 	  $(document).ready(function() {
 		 
   	    });

	  $(function () {
		  
		  /*$("#mesActualRef").on('click',function(){
	        	event.preventDefault();
	        	
	        	$.ajax({
	         		  url:"etapaoportunidades/mesactual",
	         		  method:"post",
	  			      type:"json",
//	         		  data:{"oportId": oportIdVal},
	         	      success: function (etapasList) {
//	         	    	 var oportunidades = objectsList.Array[0];
//	         	    	 var etapasList = objectsList.Array[1];
	         	    	  
	         	    	 $.ajax({
	   	         		  url:"oportunidades/mesactual",
	   	         		  method:"post",
	   	  			      type:"json",	   	         		 
	   	         	      success: function (oportunidades) {
	   	         	    	 replaceDivOportunidades (oportunidades, etapasList);
//	   	         	    	  alert(oportunidades);
	   	         	      },
	   	         	      error: function (oportunidades) {
	   	         	    	  console.log("there was an error");
	   	         	      }
	   	         	   }); 
	         	    	  
	         	      },
	         	      error: function (etapasList) {
	         	    	  console.log("there was an error");
	         	      }
	         	   });
	        	
	        	
	        	
	        });*/
		 
		  
//	 	  de Pagina listado de OPORTUNIDADES, poner el formato correcto
		  $('.date').each(function() { 
		        var dateFormat = $(this).text()
		        var dateFormat = $.datepicker.formatDate('dd/mm/yy', new Date(dateFormat));
		        var today = new Date();
		        today = $.datepicker.formatDate('dd/mm/yy', new Date(today));
		        if(dateFormat < today)
		        	{
		        	clickedClassHandler('date',function(index){
		        	    // do something with the index
		        	    alert(index);

		        	    // 'this' refers to the element 
		        	    // so you could do something with the element itself
		        	    this.style.color = 'red';
//		        	    document.getElementsByClassName('date')[dateFormat].style.color = "red";
		        	});
		        	
		        	}
		        //alert(dateFormat);
		        $(this).html(dateFormat + "<br>");
		    });
		  
		 	 $("#create-oportunidad-dialog").dialog({
		         autoOpen: false,
		         title: "Create oportunidad",
		         buttons: {
		       	"Create Course": function () {
		       	  	$("#createOportunidadForm").submit();
		       	},
		       	Cancel: function() {
		       		$("#create-oportunidad-dialog").dialog("close");
		       	}
		         }
		       });
		       
		       $("#createOportunidad").click(function() {
		       	$("#create-oportunidad-dialog").dialog("open");
		       });
		       
		       
		        
		  
		   // ----------SORTABLE MAS FUNCIONAL HASTA AHORA ------------------------------------
		  $( ".connectedSortable" ).sortable({
		      connectWith: ".connectedSortable",
		      receive: function( event, ui ) {
//		          alert("dragged item: " + $(ui.item).attr('id') + $(event.target).attr('id')); 
//		        		  ui.item.context.id);
		          var oportIdVal = $(ui.item).attr('id');
		          var opotrtArray = oportIdVal.split("-");
		          oportIdVal = opotrtArray[1];
		          var etapaIdVal = $(event.target).attr('id');
		          var etapaArray = etapaIdVal.split("-");
		          etapaIdVal = etapaArray[1];
		          
		          saveOportunity(oportIdVal, etapaIdVal);
		             
		          } 
		    }).disableSelection();
		  
		  
});  // -----------------finaliza funicion GENEARAL ---------------------------------
	  
	  function findOport(oportunidad, callback) {
		  if(oportunidad ==0) {
			  var oportunidadId = oportunidad.id;
		  } else {
			  var oportunidadId = oportunidad;
		  }
		  
		  
		  $.ajax({
			      async: false,
        		  url:"findOportunidad/"+oportunidadId,
        		  method:"post",
 			      type:"json",	
 			      data:{"oportId": oportunidadId},
        	      success: callback
//        	      error: function (oport) {
//        	    	  console.log("there was an error");
//        	      }
        	   }); 
	  }
	  


	  function clickedClassHandler(name,callback) {

		    // apply click handler to all elements with matching className
		    var allElements = document.body.getElementsByTagName("*");

		    for(var x = 0, len = allElements.length; x < len; x++) {
		        if(allElements[x].className == name) {
		            allElements[x].onclick = handleClick;
		        }
		    }

		    function handleClick() {
		        var elmParent = this.parentNode;
		        var parentChilds = elmParent.childNodes;
		        var index = 0;

		        for(var x = 0; x < parentChilds.length; x++) {
		            if(parentChilds[x] == this) {
		                break;
		            }

		            if(parentChilds[x].className == name) {
		                index++;
		            }
		        }

		        callback.call(this,index);
		    }
		}
	  
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
       		  url:"/oportunidades/"+oportIdVal,
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
	  
	  
	  
	  //  - - - FUNICION PARA filtrar con Javascript , pero ya NO ESTA EN USO
	  
  function replaceDivOportunidades (oportunidades, etapasList) {
		  
//		  findOport (oportunidades[1], function (oport) {
//			  var oportBuscada = oport;
//			  alert(oportBuscada.asunto);
//	      });
		  
		  $("#seccOports").replaceWith(
      			"<div id=\"seccOports\"></div>"
		  );
		  
		  for(var i=0; i < etapasList.length; i++) 
		  {
			  if(etapasList[i] != null)
		      {
				  $("#seccOports").append(
						  "<h3 style=\" margin-top:0em \">"+etapasList[i].nombre+"</h3>"+
						  "       <div id= \"sortable-"+etapasList[i].id+"\" class=\"connectedSortable listOports col-xs-12 col-sm-12 col-md-12 col-lg-12\">"

				  );				  
				  for(var j=0; j < oportunidades.length; j++)
					  {
					  if(oportunidades[j] != null)
				      {
						  findOport (oportunidades[j], function (oportunidad) {
							  if(etapasList[i].id == oportunidad.etapaOportunidad.id) 
						      {
								  $("#seccOports").append(
//							    "     <div th:id= \"${'oport-'+{oportunidad.id}}\" th:each=\"oportunidad : ${oportunidades}\" th:object=\"${oportunidad}\">"+
//									  "   <div th:switch=\"${etapa.id}\" th:case=\"${oportunidad.etapaOportunidad.id}\" >"+
									  "   <div  class= \"ui-state-default oportLine col-xs-12 col-sm-12 col-md-12 col-lg-12\">"+
									  "     <div class=\"col-xs-2 col-sm-2 col-md-2 col-lg-2\">"+
									  "     <a href= \"editOportunidad/" + oportunidad.id+"\">"+oportunidad.asunto+"</a>"+
									  "     </div>"+
									  "     <div class=\"col-xs-2 col-sm-2 col-md-2 col-lg-2\">"+oportunidad.cliente.nombre+"</div>"+
									  "     <div class =\"col-xs-2 col-sm-2 col-md-2 col-lg-2\">"+oportunidad.ingreso+"</div>"+
									  "     <div class=\"col-xs-2 col-sm-2 col-md-2 col-lg-2\" >"+oportunidad.etapaOportunidad.nombre+"</div>"+
									  "     <div class =\"col-xs-2 col-sm-2 col-md-2 col-lg-2\">"+oportunidad.accionSiguiente+"</div>"+
									  "     <div 	class =\"col-xs-2 col-sm-2 col-md-2 col-lg-2 date\">"+oportunidad.fechaAccion+"</div>"+
									  "   </div>"
								  );
						      }
					      });
				      }
					  }
				  $("#seccOports").append(
						  "</div>"
				  );	
				  
		      }
		  }
//      			"  <div th:each= \"etapa : ${etapas}\" th:object =\"${etapa}\">"+
      				
      					
      			
      			
      			
      		    
      		    
      		    /*"                  <div th:if= \"${ #lists.isEmpty(oportunidad) }\" >"+
      		    "                         No hay Oportunidades para desplegar"+
      		    "                   </div>"+
      		    "            </div>"+
      		    " </div>"
      		);*/
	  }
	 
	