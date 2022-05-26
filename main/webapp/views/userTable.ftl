

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Tables</title>

<!-- Custom fonts for this template -->
<link href="./resources/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./resources/assets/css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="./resources/assets/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		// Delete 

		$(document).on("click", ".delete", function() {

			var el = this;
			var deleteid = +this.id;
			
			$.ajax({
				url : 'deleteRecord',
				type : 'POST',
				data : {
					deleteid : deleteid
				},
				success : function(response) {

					// Remove row from HTML Table

					$(el).closest('tr').fadeOut(800, function() {
						$(this).remove();
					});

				}
			});

		});

	});
</script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Nav Item - Charts -->
			<!-- Sidebar - Brand -->
		<a class="sidebar-brand d-flex align-items-center justify-content-center"
				>
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">Admin</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<li class="nav-item active"><a class="nav-link"
				href="adminDashboard"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<li class="nav-item"><a class="nav-link" href="adminProfile">
					<i class="fas fa-fw fa-chart-area"></i> <span>View Profile</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">



			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="viewUser">
					<i class="fas fa-fw fa-table"></i> <span>View User</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">

			<li class="nav-item"><a class="nav-link" href="addUser?adduser=add">
					<i class="fas fa-fw fa-chart-area"></i> <span>Add User</span>
			</a></li>
			<!-- Divider -->
			<hr class="sidebar-divider">


			<li class="nav-item"><a class="nav-link" href="logout">
					<i class="fas fa-fw fa-chart-area"></i> <span>Logout</span>
			</a></li>
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


				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Tables</h1>
					<!--  <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                            href="https://datatables.net">official DataTables documentation</a>.</p> -->

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">DataTables
								Example</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
									
											<th>First Name</th>
											<th>Last Name</th>
											<th>Email</th>
											<th>Gender</th>
											<th>Hobby</th>
											<th>Mobile</th>
											<th>Edit</th>
											<th>Delete</th>
										</tr>
									</thead>

									<tbody>

									
										 <#list list as user>
										 
											<tr>
											
												<td>${user.userFName }</td>
												<td>${user.userLName }</td>
												<td>${user.userEmail }</td>
												<td>${user.userGender }</td>
												<td>${user.userHobby}</td>
												<td>${user.userMobile }</td>
												<td><a href="adminEdit?userId=${user.userId}&user=admin"><button class="btn btn-primary">Edit</button></a></td>
												<td>
													<button id="${user.userId }" class="btn btn-danger delete">Delete</button>
												</td>
											</tr>
										
									  </#list> 
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>


	<!-- Logout Modal-->
	
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				your
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>
	


	<!-- Bootstrap core JavaScript-->
	<script src="./resources/assets/vendor/jquery/jquery.min.js"></script>
	<script src="./resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="./resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="./resources/assets/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="./resources/assets/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="./resources/assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="./resources/assets/js/demo/datatables-demo.js"></script>

</body>

</html>
