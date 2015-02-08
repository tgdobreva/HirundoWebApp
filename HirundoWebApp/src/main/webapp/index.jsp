<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hirundo</title>

    <jsp:include page="css.jsp" />

</head>

<body>

    <div id="wrapper">

        <jsp:include page="navbar.jsp" />

        <div id="page-wrapper">
            <div class="row">
                <h1 class="page-header">Timeline</h1>
                <div class="col-lg-8">
					<div class="form-group" data-bind="foreach: {data: timelineMessages, as: 'timelineMessage'}">
						<div class="form-group">
							<span>
								<span><strong>Author: </strong></span>
								<span data-bind="text: timelineMessage.author" > </span>
							</span>
						</div>
						<div class="form-group">
							<span>
								<span><strong>Message: </strong></span>
								<span data-bind="html: timelineMessage.content" > </span>
							</span>
						</div>
						<hr/>
					</div>
					
                </div>
                
                <div class="col-lg-4">
                	<div class="panel panel-primary">
						<div class="panel-heading"> Not Followed </div>
						<div class="panel-body" style="overflow-y:auto; height:300px;">
		                	<div class="form-group" data-bind="foreach: {data: usersNotFollowed, as: 'user'}">
								<div class="form-group">
									<span>
										<span><strong> User: </strong></span>
										<span data-bind="text: user.username" > </span>
									</span>
								</div>
								<div class="form-group">
									<span>
										<span><strong> Verified: </strong></span>
										<i class="fa fa-check-circle"  data-bind="visible: user.verified"> </i>
										<i class="fa fa-times-circle"  data-bind="visible: !user.verified"> </i>
									</span>
								</div>
								<button class="btn btn-primary"> Follow </button>
								<hr/>
							</div>
						</div>
					</div>
                </div>
                
                <div class="col-lg-4">
                	<div class="panel panel-green">
						<div class="panel-heading"> Followed </div>
						<div class="panel-body" style="overflow-y:auto; height:300px;">
		                	<div class="form-group" data-bind="foreach: {data: usersFollowed, as: 'user'}">
								<div class="form-group">
									<span>
										<span><strong> User: </strong></span>
										<span data-bind="text: user.username" > </span>
									</span>
								</div>
								<div class="form-group">
									<span>
										<span><strong> Verified: </strong></span>
										<i class="fa fa-check-circle"  data-bind="visible: user.verified"> </i>
										<i class="fa fa-times-circle"  data-bind="visible: !user.verified"> </i>
									</span>
								</div>
								<button class="btn btn-success"> Unfollow </button>
								<hr/>
							</div>
						</div>
					</div>
                </div>
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <jsp:include page="js.jsp" />

</body>

<script>
   $(document).ready(function()  {
	   	var ViewModel = function() {
			this.usersNotFollowed = ko.observableArray();
			this.usersFollowed = ko.observableArray();
			this.timelineMessages = ko.observableArray();
		    this.hashtags = ko.observableArray();
		};
		
		var model = new ViewModel();
		ko.applyBindings(model);
		
		loadTimeline();
		setInterval(loadTimeline, 30000);
		getUsersFollowed();
		getUsersNotFollowed();
		
		function loadTimeline() {
			var hashtags = model.hashtags();
			$.ajax({
				type: "POST",
				url: "/HirundoWebApp/rest/messages/get",
				data: {
					hashtags: hashtags,
				},
				dataType: "json",
				success: function(data) {
					var regex = /#(\w+)/g;
			    	for (var index in data) {
			    		data[index].content = data[index].content.replace(regex, "<a>#$1</a>");
			    	}
					model.timelineMessages(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Cannot get timeline!");
				}
				});
		}
		
		function getUsersFollowed() {
			$.ajax({
				type: "GET",
				url: "/HirundoWebApp/rest/users/get/followed",
				dataType: "json",
				success: function(data) {
					model.usersFollowed(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Cannot get followed users!");
				}
				});
		}
		
		function getUsersNotFollowed() {
			$.ajax({
				type: "GET",
				url: "/HirundoWebApp/rest/users/get/notFollowed",
				dataType: "json",
				success: function(data) {
					model.usersNotFollowed(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("Cannot get users not followed!");
				}
				});
		}
   });
</script>

</html>
