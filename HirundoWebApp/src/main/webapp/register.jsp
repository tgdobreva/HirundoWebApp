<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Register</title>

    <jsp:include page="css.jsp" />
<title>Login</title>
</head>
<body>
	<div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Sign In</h3>
                    </div>
                    <div class="panel-body">
                        <form id="registerForm" role="form" method=post>
                            <fieldset>
                                <div class="form-group">
                                    <input type="email" autofocus="" name="email" placeholder="E-mail" class="form-control" required
                                    	data-bind='value: email'>
                                </div>
                                <div class="form-group">
                                    <input type="text" value="" name="username" placeholder="Username" class="form-control" required
                                    	data-bind='value: username'>
                                </div>
                                <div class="form-group">
                                    <input type="password" value="" name="password" placeholder="Password" class="form-control" required
                                    	data-bind='value: password'>
                                </div>
                                <div class="form-group">
                                    <input type="password" value="" name="repeatedPassword" placeholder="Repeat password" 
                                    	class="form-control" required data-bind='value: repeatedPassword'>
                                </div>
                                <!-- TODO -->
                                <div class="form-group">
                                	<button class="btn btn-success">Register</button>
                               	</div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<jsp:include page="js.jsp" />
</body>

<script>
   $(document).ready(function()  {
	   var ViewModel = function(email, username, password, repeatedPassword) {
		    this.email = ko.observable(email);
		    this.username = ko.observable(username);
		    this.password = ko.observable(password);
		    this.repeatedPassword = ko.observable(repeatedPassword);
		    
		    this.passwordsEqual = function() {
		    	return password == repeatedPassword;
		    }
		};
		
		var model = new ViewModel("", "", "", "");
		ko.applyBindings(model);
		
		$('#registerForm').submit(function(event) {

			event.preventDefault();
			
			if (!model.passwordsEqual()) {
				alert("Passwords are not equal!");
				return false;
			}
			
			$.ajax({
				type: "POST",
				url: "/HirundoWebApp/rest/registration/register",
				data: {
					email: model.email(),
					username: model.username(),
					password: model.password(),
					repeatedPassword: model.repeatedPassword(),
				},
				success: function() {
					window.location.href = "index.jsp";
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert("fatal error");
				}
				});
			
	   	}); 
   });
	  
   
</script>

</html>