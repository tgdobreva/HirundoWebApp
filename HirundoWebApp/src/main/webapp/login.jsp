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

    <title>Login</title>

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
                        <form role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input type="email" autofocus="" name="email" placeholder="E-mail" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="password" value="" name="password" placeholder="Password" class="form-control">
                                </div>
                                <!-- TODO -->
                                <div class="form-group">
                                	<button class="btn btn-default">Register</button>
                                	<button class="btn btn-success">Login</button>
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
</html>