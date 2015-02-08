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
                <div class="col-lg-12">
                    <h1 class="page-header">Timeline</h1>
					
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
								<span data-bind="text: timelineMessage.content" > </span>
							</span>
						</div>
						<hr/>
					</div>
					
                </div>
                <!-- /.col-lg-12 -->
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
		    this.timelineMessages = ko.observableArray([]);
		    this.hashtags = ko.observableArray();
		};
		
		var model = new ViewModel();
		ko.applyBindings(model);
		
		loadTimeline();
		setInterval(loadTimeline, 30000);
		
		function loadTimeline() {
			var hashtags = model.hashtags();
			$.ajax({
				type: "POST",
				url: "/HirundoWebApp/rest/message/get",
				data: {
					hashtags: hashtags,
				},
				dataType: "json",
				success: function(data) {
					model.timelineMessages(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText);
				}
				});
		}
   });
</script>

</html>
