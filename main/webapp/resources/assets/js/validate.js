$(document).ready(function() {
	
	
	$("#img_error").hide();
	$("#fname_error").hide();
	$("#lname_error").hide();
	$("#email_error").hide();
	$("#number_error").hide();
	$("#password_error").hide();
	$("#Cpassword_error").hide();
	$("#gender_error").hide();
	$("#hobby_error").hide();
	$("#aLine_error").hide();
	$("#city_error").hide();
	$("#state_error").hide();
	$("#pin_error").hide();
	
	
	var fname_error = false;
	var lname_error = false;
	var email_error = false;
	var number_error = false;
	var password_error = false;
	var Cpassword_error = false;
	var gender_error = false;
	var hobby_error = false;
	var aLine_error = false;
	var city_error = false;
	var state_error = false;
	var pin_error = false;
	

	//profile image validation
	$("#image").change(function() {
		checkImage();
	});
	$("#img").focusout(function() {
		$("#img_error").hide();
	});


	//first name validation
	$("#exampleFirstName").keyup(function() {
		check_Firstname();
	});
	$("#exampleFirstName").focus(function() {
		check_Firstname();
	});
	$("#exampleFirstName").focusout(function() {
		$("#fname_error").hide();
	});

	//last name validation
	$("#exampleLastName").keyup(function() {
		check_Lastname();
	});
	$("#exampleLastName").focus(function() {
		check_Lastname();
	});
	$("#exampleLastName").focusout(function() {
		$("#lname_error").hide();
	});

	//email validation
	$("#exampleInputEmail").keyup(function() {
		check_email();
	});
	$("#exampleInputEmail").focusout(function() {
		$("#email_error").hide();
	});
	$("#exampleInputEmail").focus(function() {
		check_email();
	});

	//password validation
	$("#exampleInputPassword").keyup(function() {
		check_pass();
	});
	$("#exampleInputPassword").focusout(function() {
		$("#password_error").hide();

	});

	$("#exampleInputPassword").focus(function() {
		check_pass();
	});

	//confirm password validation
	$("#exampleRepeatPassword").keyup(function() {
		check_Cpass();
	});
	$("#exampleRepeatPassword").focusout(function() {
		$("#Cpassword_error").hide();
	});

	$("#exampleRepeatPassword").focus(function() {
		check_Cpass();
	});

	//phone number validation

	$("#mNo").keyup(function() {
		check_phoneNumber();
	});
	$("#mNo").focus(function() {
		check_phoneNumber();
	});
	$("#mNo").focusout(function() {
		$("#number_error").hide();
	});

	//gender error
	$('input[name="userGender"]').change(function() {
		check_gender();
	});
	$('input[name="userGender"]').focusout(function() {
		$("#gender_error").hide();
	});


	//language validation
	$('input[name="userHobby"]').change(function() {
		check_hobby();
	});
	$('input[name="userHobby"]').focusout(function() {
		$("#hobby_error").hide();
	});

	
	//street line validation
	$("#address").keyup(function() {
		checkAllAddress();
	});
	$("#address").focusout(function() {
		$("#aLine_error").hide();

	});
	$("#address").focus(function() {
		checkAllAddress();
	});

	//city validation
	$("#city").change(function() {
		checkAllAddress();
	});

	$("#city").focusout(function() {
		$("#city_error").hide();
	});

	//state validation
	$("#state").change(function() {
		checkAllAddress();
	});

	$("#state").focusout(function() {
		$("#state_error").hide();
	});


	//pin validation
	$("#pin").keyup(function() {
		checkAllAddress();
	});
	$("#pin").focusout(function() {
		$("#pin_error").hide();
	});
	$("#pin").focus(function() {
		checkAllAddress();
	});

	$('a#add-more').cloneData({
    mainContainerId: 'main-container', // Main container Should be ID
    cloneContainer: 'container-item', // Which you want to clone
    removeButtonClass: 'remove-item', // Remove button for remove cloned HTML
    removeConfirm: true, // default true confirm before delete clone item
    removeConfirmMessage: 'Are you sure want to delete?', // confirm delete message
    minLimit: 1, // Default 1 set minimum clone HTML required
   	maxLimit:3,
    defaultRender: 1,
    afterRender: function() {

      for (var i = 0; i < $(".container-item").length; i++) {
        var selector = `.container-item[data-index="${i}"]`;

        setAllCallbacks(selector);

      }
    },
  });
  
  
  
  
  var defaultSelector = '.container-item[data-index="0"]';
   setAllCallbacks(defaultSelector);
	
	 function setAllCallbacks(selector) {
		 $(selector + " #address").focusout(function() {
			 $(selector + " #aLine_error").hide();
			 check_address(selector);
		 });
		 $(selector + " #aLine_error").keyup(function() {
			 check_address(selector);
		 });
		 $(selector + " #aLine_error").focus(function() {
			 check_address(selector);
		 });

		
		 $(selector + " #pin").focusout(function() {
			 $(selector + " #pin_error").hide();
			 check_pin(selector);

		 });
		 $(selector + " #pin").keyup(function() {
			 check_pin(selector);
		 });
		 $(selector + " #pin").focus(function() {
			 check_pin(selector);
		 });

		
		 //city
		 $(selector + " #city").change(function() {
			 check_city(selector);
		 });
		 $(selector + " #city").focusout(function() {
			 $(selector + " #city_error").hide();
			 

		 });

		 
		 $(selector + " #state").change(function() {
			 check_state(selector);
		 });
		 $(selector + " #state").focusout(function() {
			 $(selector + " #state_error").hide();

		 });

	 }

	function checkImage() {
		var image = $("#image").val();
		var fileType = image.split('.').pop().toLowerCase();
		if (image === '') {
			$("#img_error").html("Please select image!!").css("color", "red");;
			$("#img_error").show();
			img_error = true;
		} else if ($.inArray(fileType, ['png', 'jpg', 'jpeg']) == -1) {
			$("#img_error").html("Please select PNG/JPG/JPEG file only !!").css("color", "red");;
			$("#img_error").show();
			img_error = true;
		} else {
			$("#img_error").hide();

		}
	}
	
	function check_Firstname() {
		var namePattern = /^[a-zA-Z\s]+$/;
		var fname = $("#exampleFirstName").val();
		if (fname.length == 0) {
			$("#fname_error").html("Please enter email!!").css("color", "red");;
			$("#fname_error").show();
			fname_error = true;

		} else if (!namePattern.test(fname)) {
			$("#fname_error").html("First name contains only alphabets!!").css("color", "red");;
			$("#fname_error").show();

			fname_error = true;
		} else {
			$("#fname_error").hide();

		}
	}

	function check_Lastname() {
		var namePattern = /^[a-zA-Z\s]+$/;
		var lname = $("#exampleLastName").val();
		if (lname.length == 0) {
			$("#lname_error").html("Please enter last name!!").css("color", "red");;
			$("#lname_error").show();
			lname_error = true;
		} else if (!namePattern.test(lname)) {
			$("#lname_error").html("Last name contains only alphabets!!").css("color", "red");;
			$("#lname_error").show();

			lname_error = true;
		} else {
			$("#lname_error").hide();

		}
	}

	function check_email() {
		var emailPattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		var email = $("#exampleInputEmail").val();
		if (email.length == 0) {
			$("#email_error").html("Please enter email!!").css("color", "red");;
			$("#email_error").show();

			email_error = true;
		} else if (!emailPattern.test(email)) {
			$("#email_error").html("Please enter valid email!!").css("color", "red");;
			$("#email_error").show();
			email_error = true;
		} else {
			$("#email_error").hide();

		}

	}

	function check_phoneNumber() {
		var Phonepattern = /([0-9]{10})|(\([0-9]{3}\)\s+[0-9]{3}\-[0-9]{4})/;
		var contact = $("#mNo").val();
		var number_length = $("#mNo").val().length;
		if (contact === '') {
			$("#number_error").html("Please enter contact number!!").css("color", "red");
			$("#number_error").show();
			number_error = true;
		}
		else if (number_length < 10 || number_length > 10) {
			$("#number_error").html("Phone number contains only 10 digit").css("color", "red");;
			$("#number_error").show();
			number_error = true;

		} else if (!Phonepattern.test(contact)) {
			$("#number_error").html("Please enter only digits").css("color", "red");
			$("#number_error").show();
			number_error = true;
		}
		else {
			$("#number_error").hide();
		}

	}

	function check_pass() {
		var pass_length = $("#exampleInputPassword").val().length;
		if (pass_length == 0) {
			$("#password_error").html("Please enter password!!").css("color", "red");;
			$("#password_error").show();
			password_error = true;
		}
		else {
			$("#password_error").hide();
		}
	}
	function check_Cpass() {
		var pass = $("#exampleInputPassword").val();
		var cpass = $("#exampleRepeatPassword").val();

		if (cpass.length == 0) {
			$("#Cpassword_error").html("Please enter confirm passwordl!!").css("color", "red");
			$("#Cpassword_error").show();
			Cpassword_error = true;
		}

		else if (pass !== cpass) {
			$("#Cpassword_error").html("Password and confirm password should be same").css("color", "red");
			$("#Cpassword_error").show();

			Cpassword_error = true;
		} else {
			$("#Cpassword_error").hide();

		}
	}

	function check_gender() {
		if ($('input[name="userGender"]:checked').length == 0) {
			$("#gender_error").html("Please select gender!!").css("color", "red");
			$("#gender_error").show();
			gender_error = true;
		}
		else {
			$("#gender_error").hide();
		}
	}


	
	function check_hobby() {
		if ($("input:checkbox").filter(":checked").length == 0) {
			$("#hobby_error").html("Please select hobbies !!").css("color", "red");
			$("#hobby_error").show();
			hobby_error = true;
		} else {
			$("#hobby_error").hide();
		}
	}

	function checkAllAddress() {
		for (var i = 0; i < $(".container-item").length; i++) {
			var selector = `.container-item[data-index="${i}"]`;

			check_address(selector);
			check_state(selector);
			check_city(selector);
			check_pin(selector);



		}
	}
	function check_address(selector) {
		var address = $(selector + " #address").val();
		if (address.length == 0) {
			$(selector + " #aLine_error").html("Please enter street line!!").css("color", "red");;
			$(selector + " #aLine_error").show();
			aLine_error = true;
		} else {
			$(selector + " #aLine_error").hide();

		}
	}

	function check_state(selector) {
		var state = $(selector + " #state").val();
		if (state == "") {
			$(selector + " #state_error").html("Please select state!!").css("color", "red");
			$(selector + " #state_error").show();
			state_error = true;
		} else {
			$(selector + " #state_error").hide();
		}
	}

	function check_city(selector) {
		var city = $(selector + " #city").val();
		if (city == "") {
			$(selector + " #city_error").html("Please select city!!").css("color", "red");
			$(selector + " #city_error").show();
			city_error = true;
		} else {
			$(selector + " #city_error").hide();
		}
	}

	function check_pin(selector) {

		var pattern = /^[0-9]+$/;
		var pin = $(selector + " #pin").val();

		if (pin.length == 0) {
			$(selector + " #pin_error").html("Please enter pincode!!").css("color", "red");;
			$(selector + " #pin_error").show();
			pin_error = true;

		} else if (!pattern.test(pin)) {
			$(selector + " #pin_error").html("Pin contain only numbers").css("color", "red");;
			$(selector + " #pin_error").show();
			pin_error = true;

		} else if (pin.length < 6 || pin.length > 6) {
			$(selector + " #pin_error").html("Pin contain only 6 digits").css("color", "red");;
			$(selector + " #pin_error").show();

			pin_error = true;

		}
		else {
			$(selector + " #pin_error").hide();

		}
	}
	
	

	$("#register").submit(function() {
		
		fname_error = false;
		lname_error = false;
		email_error = false;
		number_error = false;
		password_error = false;
		Cpassword_error = false;
		gender_error = false;
		hobby_error = false;
		aLine_error = false;
		city_error = false;
		state_error = false;
		pin_error = false;
		
		check_Firstname();
		check_Lastname();
		check_email();
		check_phoneNumber();
		checkImage();
		check_pass();
		check_Cpass();
		check_gender();
		check_hobby();
		checkAllAddress();



		if (fname_error === false && lname_error === false && email_error === false && number_error === false && password_error === false &&
			Cpassword_error === false && hobby_error === false && gender_error === false &&  aLine_error === false &&
			 city_error === false  && state_error === false && pin_error === false) {
			alert("Registration Successful");
			return true;
		} else {
			return false;
			
		}
	});

	

	
	


});