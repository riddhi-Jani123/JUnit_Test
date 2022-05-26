
<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Forgot Password</title>

<style>

label.error {
	color: red;
	font-size: 1rem;
	width: 100%;
}

.email_err{

color:red;
}
.pass_err{

color:red;
}

.cpass_err{

color:red;
}
</style>

<!-- Custom fonts for this template-->

<link href="./resources/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->

<link href="./resources/
assets/css/sb-admin-2.min.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"> </script>
<script>



$(document).ready(function(){

	
.err{

	color:red;
}
	
	var email_err;
	var pass_err;
	var cpass_err;
	
	 $('#exampleInputEmail').keyup(function () {
	        checkEmail();
	    });

	 $('#exampleInputPassword').keyup(function () {
		 checkPass();
	    });
	 $('#exampleRepeatPassword').keyup(function () {
		 checkConfirmPass();
	    });
	 
	$('#submit').click(function () {
		
		
		console.log("submit");
		email_err = false;
		pass_err =  false;
		cpass_err = false;
		
		checkEmail();
		checkPass();
		checkConfirmPass();
		
		if(email_err === true && pass_err === true && cpass_err === true){
			console.log(true);
			
			return true;
		}
		else{
			return false;
		}
	});
	
	function checkEmail(){
		

		var id = $('#exampleInputEmail').val();
		console.log(id);
		console.log(id.length);
		
		if(id.length == 0){
			
			$(".email_err").html("email is required");
			$(".email_err").show();
			
			
			
		}
		else if(!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(id)))
		  {
		   
			$(".email_err").html("Please provide valid email address");
			$(".email_err").show();
		
		  }
		else{
			
			$('.email_err').hide();
			email_err=true;
		} 
		
	}

		 
		function checkPass(){
			
			var pass = $('#exampleInputPassword').val();
			console.log(pass);
			console.log(pass.length);
			
			
			if(pass.length== 0 ){
				
				$(".pass_err").html("password is required");
				$('.pass_err').show();
				
			}
			
			else{
				
				$('.pass_err').hide();
				pass_err = true;
			} 
			
			
		}
		 

	 

function checkConfirmPass(){
	
	
	
	var id = $('#exampleRepeatPassword').val();
	
	console.log(id);
	console.log(id.length);
	var pId = $('#exampleInputPassword').val();
	
	if(id.length == 0){
		
		$(".cpass_err").html("confirm password must be required");
		$(".cpass_err").show();
		
	}
	
	
	else if(id !== pId){
		
		$(".cpass_err").html("password and confirm password must be same");
		$(".cpass_err").show();
		
	}
	
	else {
		$(".cpass_err").hide();
		cpass_err = true;

	}
	
}
	
			 
	

})

	

</script>

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                          
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
                                       
                                    </div>
                                    <form class="user" method="post" action="forgotPass" id="form-validate" modelAttribute="forgotPass">
                                        <div class="form-group">
                                            <input type="text" name="userEmail" class="form-control form-control-user"
                                                id="exampleInputEmail" aria-describedby="emailHelp"
                                                placeholder="Email Address...">
                                                <span class="email_err"> </span>
                                          </div>
                                            <div class="form-group">     
                                                  <input type="password" class="form-control form-control-user" name="userPass"
                                                id="exampleInputPassword"
                                                placeholder="New Password...">
                                                 <span class="pass_err"> </span>
                                                </div>
                                                 <div class="form-group">
                                                  <input type="password" class="form-control form-control-user" name="userCPass"
                                                id="exampleRepeatPassword" 
                                                placeholder="Confirm Password...">
                                                <span class="cpass_err"></span>
                                                </div>
                                        <span class="err"> ${err!''}</span>
                                        <input type="submit" id="submit" class="btn btn-primary btn-user btn-block" value="Reset Password">
                                          
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="register">Create an Account!</a>
                                    </div>
                                    <div class="text-center">
                                        <a class="small" href="index">Already have an account? Login!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
<script src="./resources/assets/vendor/jquery/jquery.min.js"> </script>
<script src="./resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="./resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="./resources/assets/js/sb-admin-2.min.js"></script>
</body>

</html>