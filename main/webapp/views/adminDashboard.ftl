

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

<style>
.ft{

  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: red;
  text-align: center;
}
</style>
</head>
<body>
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
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="adminDashboard"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>Dashboard</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<li class="nav-item "><a class="nav-link" href="adminProfile">
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

					
				</nav>
				<!-- End of Topbar -->


				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="container-fluid">

						<!-- Page Heading -->
						<div
							class="d-sm-flex align-items-center justify-content-between mb-4">
							<h1 class="h3 mb-0 text-gray-800">Welcome</h1>

						</div>
					</div>
				</div>
				
				<footer class="sticky-footer bg-white ft">
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