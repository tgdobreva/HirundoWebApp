<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <form id="registerForm" role="form" method=post 
                        	action="/HirundoWebApp/rest/registration/register">
                            <fieldset>
                            	<div class="form-group">
	                            	<span>${param.error}</span>
                                </div>
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
		   var self = this;
		   self.email = ko.observable(email);
		   self.username = ko.observable(username);
		   self.password = ko.observable(password);
		   self.repeatedPassword = ko.observable(repeatedPassword);
		    
		   self.passwordsEqual = function() {
	    		return self.password() == self.repeatedPassword();
		   }
		};
		
		var model = new ViewModel("", "", "", "");
		ko.applyBindings(model);
		
		$('#registerForm').submit(function(event) {
			if (!model.passwordsEqual()) {
				alert("Passwords are not equal!");
				return false;
			}
	   	}); 
   });
	  
   
</script>

</html>