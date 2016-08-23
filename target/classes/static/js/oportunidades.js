 	 
 	  $(document).ready(function() {
 		 
   	    });

 	

	  $(function () {
		  
		
		 
		  
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
	  
	 
	