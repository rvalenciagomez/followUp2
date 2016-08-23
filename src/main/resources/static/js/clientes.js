
$(function () {
	
        $("#create-cliente-dialog").dialog({
          autoOpen: false,
          title: "Create cliente",
          buttons: {
        	"Create Course": function () {
        	  	$("#createClienteForm").submit();
        	},
        	Cancel: function() {
        		$("#create-cliente-dialog").dialog("close");
        	}
          }
        });
        
        $("#createCliente").click(function() {
        	$("#create-cliente-dialog").dialog("open");
        });
        
        
	  });