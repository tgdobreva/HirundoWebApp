<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>New Message</title>

    <jsp:include page="css.jsp" />
</head>
<body>
	<div id="wrapper">

        <jsp:include page="navbar.jsp" />

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">New Message</h1>
					<div class="panel panel-primary">
                    	<div class="panel-heading"> Write new message </div>
                    	<div class="panel-body">
		                    <form id="messageForm" role="form" method=post>
								<div class="form-group">
									<label>Message: </label>
									<input id="message" class="form-control" placeholder="Enter message" required
										data-bind='value: message' maxlength="140">
								</div>
								
								<div class="form-group">
									<label>Place: </label>
									<input id="place" class="form-control" placeholder="Enter place"
										data-bind='value: place'>
								</div>
	                        	<button class="btn btn-primary">Submit</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

    <jsp:include page="js.jsp" />
</body>

<script>
$(document).ready(function()  {
	var ViewModel = function(message, place) {
		this.message = ko.observable(message);
	    this.place = ko.observable(place);
	}
	
	var model = new ViewModel("", "");
	ko.applyBindings(model);
	
	$('#messageForm').submit(function(event) {

		event.preventDefault();
		event.stopPropagation();

		var message = model.message().trim();
		if (!message) {
			alert("Cannot submit empty message!")
			return false;
		}
		if (message.length > 140) {
			alert("Message must be maximum 140 symbols!");
			return false;
		}
		saveMessage();
   	}); 
	
	var saveMessage = function() {
		var message = model.message().trim();
		var place = model.place().trim();
		place = place ? place : undefined;
		$.ajax({
			type: "POST",
			url: "/HirundoWebApp/rest/messages/insert",
			data: {
				message: message,
				place: place,
			},
			dataType: "json",
			success: function() {
				clearMessage();
				alert("Message added successfully!");
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText);
			}
			});
	}
	
	var clearMessage = function() {
		model.message("");
		model.place("");
	}
});
</script>
</html>