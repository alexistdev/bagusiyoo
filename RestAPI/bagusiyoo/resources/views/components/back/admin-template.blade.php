<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminLTE 3 | Dashboard</title>
    <x-back.header-layout />
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

    <!-- Navbar -->
   <x-back.navbar-layout />
    <!-- /.navbar -->

    <!-- Main Sidebar Container -->
    <x-back.sidebar-layout />
    <!-- /Main Sidebar Container -->
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        {{$slot}}
    </div>
    <!-- /.content-wrapper -->
    <x-back.footer-layout />


</div>
<!-- ./wrapper -->


</body>
</html>
