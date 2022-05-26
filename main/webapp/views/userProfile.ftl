
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="./resources/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="./resources/assets/https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="./resources/assets/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>

								      <#if list??>
								       <#assign userId="${list.userId}">
								     <#assign fname="${list.userFName}">
									<#assign lname="${list.userLName}">
									<#assign password="${list.userPass}">
									<#assign email="${list.userEmail}">
									<#assign mobile="${list.userMobile}">
							 		<#assign image="${list.base64Image }"> 
							 		<#assign gender="${list.userGender  }"> 
							 		<#assign hobby="${list.userHobby}">
									</#if>
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Nav Item - Charts -->
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				>
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">User</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="userDashboard"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<li class="nav-item"><a class="nav-link" href="userProfile">
					<i class="fas fa-fw fa-chart-area"></i> <span>View Profile</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">


			<li class="nav-item"><a class="nav-link" href="userEdit?userId=${userId!''}&user=user">
					<i class="fas fa-fw fa-chart-area"></i> <span>Edit Profile</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">

			<li class="nav-item"><a class="nav-link" href="logout">
					<i class="fas fa-fw fa-chart-area"></i> <span>Logout</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">
		</ul>

		<!-- search bar -->
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					User Management System
				</nav>
				<!-- End of Topbar -->


				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="container-fluid">

						<!-- Page Heading -->
						
					</div>
				</div>
						 <tr>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									
									 <tr>
											<td>Profile </td>
											<td>
											<img src="data:image/png;base64,${image!""}"/><br><br>
											</td>
											</tr>
											<tr>
											<td>First Name</td>
											<td>${fname!''}</td>
											</tr>
											<tr>
											<td>Last Name</td>
											<td>${lname!''}</td>
											</tr>
											<tr>
											<td>Email</td>
											<td>${email!''}</td>
											</tr>
											<tr>
											<td>Mobile</td>
											<td>${mobile!''}</td>
											</tr>
											<tr>
											<td>Gender</td>
											<td>${gender!''}</td>
											</tr>
											<tr>
											<td>Hobby</td>
											<td>${hobby!''}</td>
											</tr>
											
						
								           <#list address as address>
								           <tr>
								           <td> Address</td>
											<td> ${address.address} , ${address.city} - ${address.pin} , ${address.state}</td>
								</tr>
								     
								           
								           </#list>  
								     
								</table>
							</div>
						</div>
					
				<footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>

				<script src="./resources/assets/vendor/jquery/jquery.min.js"></script>
				<script src="./resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

				<!-- Core plugin JavaScript-->
				<script src="./resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

				<!-- Custom scripts for all pages-->
				<script src="./resources/assets/js/sb-admin-2.min.js"></script>

				<!-- Page level plugins -->
				<script src="./resources/assets/vendor/chart.js/Chart.min.js"></script>

				<!-- Page level custom scripts -->
				<script src="./resources/assets/js/demo/chart-area-demo.js"></script>
				<script src="./resources/assets/js/demo/chart-pie-demo.js"></script>
</body>
</html>