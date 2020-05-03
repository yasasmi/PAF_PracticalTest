$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {

	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateItemForm();

	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "DoctorAPI",
		type : type,
		data : $("#formItem").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});
});

function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}

// UPDATE==========================================
$(document)
		.on(
				"click",
				".btnUpdate",
				function(event) {

					$("#hidItemIDSave").val(
							$(this).closest("tr").find('#hidItemIDUpdate')
									.val());
					$("#docNic").val(
							$(this).closest("tr").find('td:eq(0)').text());
					$("#docName").val(
							$(this).closest("tr").find('td:eq(1)').text());
					$("#docEmail").val(
							$(this).closest("tr").find('td:eq(2)').text());
					$("#docContact").val(
							$(this).closest("tr").find('td:eq(3)').text());
					$("#docGender").val(
							$(this).closest("tr").find('td:eq(4)').text());
					$("#docFee").val(
							$(this).closest("tr").find('td:eq(5)').text());
					$("#docSpec").val(
							$(this).closest("tr").find('td:eq(6)').text());
					$("#docHospital").val(
							$(this).closest("tr").find('td:eq(7)').text());
					$("#docNumAppointments").val(
							$(this).closest("tr").find('td:eq(8)').text());
					$("#docPassword").val(
							$(this).closest("tr").find('td:eq(9)').text());

				});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "DoctorAPI",
		type : "DELETE",
		data : "docNic=" + $(this).data("itemid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}
//regex for validations================================================================================================================================
var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
var phoneno = /^\d{10}$/;
var passw=  /^[A-Za-z]\w{7,14}$/;

//CLIENTMODEL=============================================================================================================================================
function validateItemForm() {

	// CODE
	if ($("#docNic").val().trim() == "") {
		return "Insert NIC.";
	}
	if ($("#docName").val().trim() == "") {
		return "Insert Name.";
	}

	if ($("#docEmail").val().trim() == "" || !re.test($("#docEmail").val())){
	
		return "Insert Valide Email";
	}
	
	
	if ($("#docContact").val().trim() == "" || !phoneno.test($("#docContact").val())) {
		return "Insert Valied Contact Number.";
	}
	// GENDER
	if ($("#docGender").val() == "0") {
		return "Select Gender.";
	}

	// is numerical value
	var tmpPrice = $("#docFee").val().trim();

	if (!$.isNumeric(tmpPrice)) {
		return "Insert a numerical value for Appointment Fee.";
	}

	// convert to decimal price
	$("#docFee").val(parseFloat(tmpPrice).toFixed(2));

	if ($("#docSpec").val() == "0") {
		return "Select Specification.";
	}

	if ($("#docHospital").val() == "0") {
		return "Select Hospital.";
	}

	if ($("#docNumAppointments").val() == "0") {
		return "Select appointments.";
	}

	if ($("#docPassword").val().trim() == "" || !passw.test($("#docPassword").val())) {
		return "Insert Password between 7 to 16 Characters start with Letter.";
	}

	return true;
}


	
	

