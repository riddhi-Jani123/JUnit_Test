<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Registration</title>
<link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/4.3.1/minty/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.css">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet" />

	
	<link href="./resources/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->

<link href="./resources/assets/css/sb-admin-2.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css"
	integrity="sha512-wnea99uKIC3TJF7v4eKk4Y+lMz2Mklv18+r4na2Gn1abDRPPOeef95xTzdwGD9e6zXJBteMIhZ1+68QC5byJZw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
	
<style>
#usr-err{

	color:red;

}


label.error {
	color: red;
	font-size: 1rem;
	width: 100%;
}

.error{
font-size: 1rem;
	width: 100%;
}


</style>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
</script>
 


<script>



function previewImage(input) {

		var file = $("input[type=file]").get(0).files[0];

		if (file) {
			var reader = new FileReader();

			reader.onload = function() {
				$("#previewImg").attr("src", reader.result).height(200).width(
						200);
				;
			}

			reader.readAsDataURL(file);
		}
	}
$(document).ready(function() {	

$(document).on("click", "#add-more", function() {
 
   $("select option").attr("disabled", false);


  $(".addressType option:selected").each(function() {
    var value = $(this).val()
    
    $(".addressType option:not(:selected)").each(function() {
      if ($(this).val() == value) {
        $(this).attr("disabled", true);
      }
    })
  })
})

	<#if addressList??>
	<#list addressList as addressList>
	
	$("#city${addressList?index} option[value=${addressList.city}]").attr('selected', 'selected');
	$("#state${addressList?index} option[value=${addressList.state}]").attr('selected', 'selected');
		
	$("#addressType${addressList?index} option[value=${addressList.addressType}]").attr('selected', 'selected');
<#assign address="${addressList.address}">
							<#assign city="${addressList.city}">
							<#assign state="${addressList.state}">
							<#assign pin="${addressList.pin}">
							<#assign addressType="${addressList.addressType}">
			
	</#list>


		
	</#if>
	<#if userList??>
	<#list userList.userHobby?split(",") as hobby>
	<#if hobby=="reading">
	$("input[name=userHobby][value=${hobby}]").prop("checked",true);
	
	</#if>
	<#if hobby=="singing">
	$("input[name=userHobby][value=${hobby}]").prop("checked",true);
	
	</#if>
	</#list>
	
								
						  	

							<#assign id = "${userList.userId}">
							<#assign rId = "${userList.relativeId}">
			
							<#assign fname="${userList.userFName}">
							<#assign lname="${userList.userLName}">
							<#assign password="${userList.userPass}">
							<#assign email="${userList.userEmail}">
							<#assign mobile="${userList.userMobile}">
							 <#assign image="${userList.base64Image }"> 
						
							
							
						
	</#if>

 
<#if adduser??>
							<#assign action="registerForm?adduser=add">
								<#assign header="Add">
								<#assign submit="Add">
							<#elseif user??>
							<#if user=="user">
							<#assign action="editForm?userName=user">
							<#assign header="Update">
								<#assign submit="Update">
								</#if>
							<#if user=="admin">
							<#assign action="editForm?userName=admin">
							
								<#assign header="Update">
								
								<#assign submit="Update">
								</#if>
									
							<#else>
								
								<#assign action="registerForm">
								<#assign header="Register">
								<#assign submit="Register">
							
						
							
							</#if>
	var parsed = new URL(location);
	var userId = parsed.searchParams.get("userId");
	
	if(userId!=null)
	{
	
	$("#exampleInputPassword").attr('readonly', true);
		$("#exampleRepeatPassword").attr('readonly', true);
		$("#exampleInputEmail").attr('readonly', true);
	}
	
		
			
			
			
$("#exampleInputEmail").keyup(function () {

				
			    var EmailAddress = $("#exampleInputEmail").val();
			     
			        $.ajax({
			            type: 'GET',
			             data:{
			                      email: EmailAddress
			             },
			             url: 'checkEmail',
			             success: function(result) {
			           
			             
			            console.log(result);
			             if (result=="true") {
			             
			                 $("#email-check").html("Email already exist. Please try other!!").css("color","red");
			                        $("#registerButton").prop("disabled",true);
			              } else {
			              
			              console.log("in else");
			                  $("#email-check").html("");
			                   $("#registerButton") .prop("disabled", false);
			               }
			            }
			        });
			
	});
	
	 $("#user").change(function(){
  		var  id =  $('#user').val();
  		
  		<#if adduser??>
  		window.location.href = 'addUser?adduser=add&id=' + id;
  		
  		<#else>
  		window.location.href = 'register?id=' + id;
  		
  		</#if>
		

		
  });	
	
	$("#register").validate({

											rules : {
												userFName : {
													required : true,
													minlength : 3
												},
												userLName : {
													required : true,
													minlength : 3
												},
												userEmail : {
													required : true,
													email : true
												},
												userMobile : {

													required : true,
													number : true,
													minlength : 10,
													maxlength : 10

												},
												userPass : {
													required : true,
												

												},

												userGender : {

													required : true
												},

												userHobby : {

													required : true
												}

											},
											messages : {
												userFName : {
													required : "First Name is required",

													minlength : "First Name should be at least 3 characters"
												},
												userLName : {
													required : "Last Name is required",
													minlength : "Last Name should be at least 3 characters"
												},

												userEmail : {
													required : "Email  is required",
													email : "The email should be in the format: abc@domain.tld"

												},
												userMobile : {
													required : "Mobile Number is required",
													number : "Please enter valid mobile number"

												},
												userPass : {
													required : "Password is required",
												
												}

												
											}

										});
								
						

		
	});		
</script>


</head>

<body class="bg-gradient-primary">
<#if Request.fname?exists>
											${Request.fname}
											</#if>
											<#if Request.lname?exists>
											${Request.lname}
											</#if>
											<#if Request.mobile?exists>
											${Request.mobile}
											</#if>
											<#if Request.email?exists>
											${Request.email}
											</#if>
											<#if Request.password?exists>
											${Request.password}
											</#if>
											<#if Request.password?exists>
											${Request.password}
											</#if>

<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-12">
						<div class="p-5">
						
							
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">${header} </h1>
							</div>
							
						    
							

							<form  method="POST" action=${action}
								id="register"  modelAttribute="user" enctype="multipart/form-data">			
											<#if userId??>
											
											<#else>
											<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">User
												<select id="user" name="relative"
												class="form-control form-control-user">
												<option value=""> Select User </option>
											<#if userLists??>
												<#list userLists as user> 
													
													<option id="userId" name="relativeId" class="userName"
															value="${user.userId}" <#if id??>
															<#if "${user.userId}"=="${id}">
														selected="selected"
															</#if></#if>>${user.userFName }</option>
													</#list>
														</#if>	
											</select>
											</label>
											<span id="usr-err" > ${error!""} </span>
										</div>
										
											</#if>
										
												<#if userList??>
												
												<input type="hidden" class="form-control form-control-user"
													name="userId" value="${id!''}"
													placeholder="First Name" > 
												</#if>
													<input type="hidden"
															class="address form-control form-control-user"
															name="relativeIds" 
															value="${rId!''}">
												
								<div class="form-group">
											<label>Photo:</label> <input type="file" name="image" id="image"
												onchange="previewImage()"> <br> <img
												id="previewImg"
												src="data:image/png;base64,${image!''}"
												 /> <br>

										</div>
										<div class="form-group row">
											<div class="col-sm-6 mb-3 mb-sm-0">

											
												
											
												<input type="text" class="form-control form-control-user"
													id="exampleFirstName" name="userFName" value="${fname!''}"
													placeholder="First Name" > 

												

											</div>
											<div class="col-sm-6">
												<input type="text" class="form-control form-control-user"
													id="exampleLastName" name="userLName" value="${lname!''}"
													placeholder="Last Name"> 
											
										

											</div>
										</div>
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="exampleInputEmail" name="userEmail" value="${email!''}" 
												placeholder="Email Address" >
												<span id="email-check"> </span>
										

										</div>
										<div class="form-group">

											<input type="tel" class="form-control form-control-user"
												id="mNo" name="userMobile" value="${mobile!''}" placeholder="mobile no">
									

										</div>
										<div class="form-group row">
											<div class="col-sm-6 mb-3 mb-sm-0">

												<input type="password"
													class="form-control form-control-user"
													id="exampleInputPassword" name="userPass" value="${password!''}"
													placeholder="Password" >
											



											</div>
											<div class="col-sm-6">
												<input type="password"
													class="form-control form-control-user"
													id="exampleRepeatPassword" name="userCPass" value="${password!''}"
													placeholder="Confirm Password">

												
										</div>
										</div>
										
							<label class="block mt-2 text-md font-semibold"><span
										class="text-gray-700 dark:text-gray-400">Gender</span></label>
										
										<label> Female</label>
										<input type="radio" value="female" name="userGender"  <#if userId??>
								
									<#if userList.userGender =="female">
										value="true" checked="checked"
									</#if>
							</#if>  >
										<br>
										<label> Male</label>
										<input type="radio" value="male" name="userGender" <#if userId??>
								
									<#if userList.userGender =="male">
										value="true" checked="checked"
									</#if>
							</#if>>
							

							
										<br>
											<label class="block mt-2 text-md font-semibold"><span
										class="text-gray-700 dark:text-gray-400">Hobby</span></label>
										<br>
										<label> singing</label>
										<input type="checkbox" id="sing" value="singing"
											name="userHobby"  >
										<br>
										<label> reading</label>
										<input type="checkbox" id="read" value="reading"
											name="userHobby"  >
								
											
											<#if addressList??>
												
						<div id="main-container">
					<span
						class="text-gray-700 dark:text-gray-400 text-lg font-semibold">Address</span>
						
						<#list addressList as addressList>
													
												
												<div class="panel card container-item mb-2">
							<div class="panel-body">
								<div class="panel-body pl-2 pr-2 pb-2">
									<div class="flex flex-row gap-8">
										<div class="w-full">
										<label class="block mt-2 text-md font-semibold"><span
										class="text-gray-700 dark:text-gray-400">Street Address</span>
										<input type="hidden"
															class="address form-control form-control-user"
															name="addressId[]" id="addressId[]"
															placeholder="Enter street address"
															value="${addressList.addressId }">
										<input type="text" name="Address[]" id="address" 
										value="${addressList.address!""}"
										class="form-control form-control-user" />
										<span id="aLine_error"></span> </label>
										</div>
										
										<div class="w-full">
										<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">Address Type
												<select id="addressType${addressList?index}" name="addressType[]"
												class="form-control form-control-user addressType" value="addressList.addressType">

												<option value="">Select Address type </option>
											
													<option value="Home">Home</option>
													<option value="Office">Office</option>
													<option value="Temporary">Temporary</option>
									
													
											</select> <span id="addType_error"></span>
											</label>
										</div>

									</div>	
									<div class="flex flex-row gap-8">
										<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">City
												<select id="city${addressList?index}" name="City[]"
												class="form-control form-control-user">
													<option value="">Select your city</option>
													<option value="Ahmedabad">Ahmedabad</option>
													<option value="Rajkot">Rajkot</option>
													<option value="Surat">Surat</option>
													<option value="Gandhinagar">Gandhinagar</option>
											</select> <span id="city_error"></span>
											</label>
										</div>

										<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">State
												<select id="state${addressList?index}" name="State[]"
												class="form-control form-control-user">
													<option value="">Select your state</option>
													<option value="Gujarat">Gujarat</option>
													<option value="Rajsthan">Rajsthan</option>
													<option value="Bihar">Bihar</option>
													<option value="Punjab">Punjab</option>
											</select> <span id="state_error"></span>
											</label>
										</div>
									</div>

									<div class="flex flex-row gap-8">
										<div class="w-full">
											<label class="block mt-2 mt-4 text-md font-semibold">
												<span class="text-gray-700 dark:text-gray-400">Postal
													Code</span> <input type="number" name="Pin[]" id="pin"
												value="${addressList.pin!""}"
												class="form-control form-control-user"
												placeholder="xxxxxx" /> <span id="pin_error"></span>
											</label>
										</div>
										<div class="w-full grid content-center justify-center">
											<div class="mt-8">
												<a href="javascript:void(0)"
													class="remove-item bg-red-500 text-white px-4 py-2 rounded-md">Remove</a>
											</div>
										</div>

									</div>
								</div>

							</div>

						</div>						</#list>
					
				</div>

					
				
				<div class="grid w-1/2 justify-start">
					<a class="px-4 py-2 bg-green-400 text-white rounded " id="add-more"
						href="javascript:;" role="button"><i class="fa fa-plus"></i>
						Add more address</a>


				</div>
				
				<#else>						
							
				<div id="main-container">
					<span
						class="text-gray-700 dark:text-gray-400 text-lg font-semibold">Address</span>
						<div class="panel card container-item mb-2">
							<div class="panel-body">
								<div class="panel-body pl-2 pr-2 pb-2">
									<div class="flex flex-row gap-8">
										<div class="w-full">
										<label class="block mt-2 text-md font-semibold"><span
										class="text-gray-700 dark:text-gray-400">Street Address</span>
										<input type="text"  id="address" 
										value="${address!""}" name="Address[]" 
										class="form-control form-control-user" />
										<span id="aLine_error"></span> </label>
										</div>
										
										<div class="w-full">
										<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">Address Type
												<select id="addressType"
												class="form-control form-control-user addressType"  name="addressType[]">

												<option value="">Select Address type </option>
											
													<option value="Home">Home</option>
													<option value="Office">Office</option>
													<option value="Temporary">Temporary</option>
									
													
											</select> <span id="addType_error"></span>
											</label>
										</div>
										
										

									</div>	
									<div class="flex flex-row gap-8">
										<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">City
												<select id="city" 
												class="form-control form-control-user"  name="City[]">
													<option value="">Select your city</option>
													<option value="Ahmedabad">Ahmedabad</option>
													<option value="Rajkot">Rajkot</option>
													<option value="Surat">Surat</option>
													<option value="Gandhinagar">Gandhinagar</option>
											</select> <span id="city_error"></span>
											</label>
										</div>

										<div class="w-full">
											<label
												class="block mt-2 text-gray-700 dark:text-gray-400 font-semibold">State
												<select id="state" 
												class="form-control form-control-user"  name="State[]">
													<option value="">Select your state</option>
													<option value="Gujarat">Gujarat</option>
													<option value="Rajsthan">Rajsthan</option>
													<option value="Bihar">Bihar</option>
													<option value="Punjab">Punjab</option>
											</select> <span id="state_error"></span>
											</label>
										</div>
									</div>

									<div class="flex flex-row gap-8">
										<div class="w-full">
											<label class="block mt-2 mt-4 text-md font-semibold">
												<span class="text-gray-700 dark:text-gray-400">Postal
													Code</span> <input type="number" id="pin" name="Pin[]"
												value="${pin!""}"
												class="form-control form-control-user"
												placeholder="xxxxxx" /> <span id="pin_error"></span>
											</label>
										</div>
										<div class="w-full grid content-center justify-center">
											<div class="mt-8">
												<a href="javascript:void(0)"
													class="remove-item bg-red-500 text-white px-4 py-2 rounded-md">Remove</a>
											</div>
										</div>

									</div>
								</div>

							</div>

						</div>
					
				</div>


			

				<div class="grid w-1/2 justify-start">
					<a class="px-4 py-2 bg-green-400 text-white rounded " id="add-more"
						href="javascript:;" role="button"><i class="fa fa-plus"></i>
						Add more address</a>


				</div>
</#if>

	<hr class="my-8" />
		
				<input type="submit" value=${submit} id="registerButton"
			class="btn btn-primary btn-user btn-block">


		
		<hr class="my-8" />

	<div class="text-center">
											<a href="index">Already have an
												account? Login!</a>
										</div>


	</form>
	
	</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	

	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/3.3.2/select2.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/js/bootstrap-select.js"></script>
	<script src="https://cdn.ckeditor.com/4.5.1/standard/ckeditor.js"></script>
	
	<script type="text/javascript" src="./resources/assets/js/cloneData.js"></script>
	
	<script type="text/javascript" src="./resources/assets/js/validate.js"></script>

	
</body>
</html>