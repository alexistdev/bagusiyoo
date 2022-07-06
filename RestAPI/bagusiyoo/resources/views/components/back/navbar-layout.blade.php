<div>
    <nav class="main-header navbar navbar-expand navbar-white navbar-light">
        <!-- Left navbar links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
            </li>
            <li class="nav-item d-none d-sm-inline-block">
                <a href="{{route('admin.dashboard')}}" class="nav-link">Home</a>
            </li>
        </ul>

        <!-- Right navbar links -->
        <ul class="navbar-nav ml-auto">
            <!-- Navbar Search -->




            <li class="nav-item">
                <form method="POST" action="{{ route('logout') }}">
                    @csrf
                <a class="nav-link" data-widget="control-sidebar" data-controlsidebar-slide="true" href="#" role="button" onclick="event.preventDefault();
                                                this.closest('form').submit();">
                    <i class="fas fa-power-off"></i>
                </a>
                </form>
            </li>
        </ul>
    </nav>
</div>
