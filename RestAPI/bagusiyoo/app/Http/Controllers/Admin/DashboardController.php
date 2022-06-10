<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Category;
use App\Models\Question;
use App\Models\Ujian;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class DashboardController extends Controller
{
    protected $users;
    protected $role;
    protected $notif;

    public function __construct()
    {
        $this->middleware(function ($request, $next) {
            $this->users=Auth::user();
            return $next($request);
        });
    }

    public function index()
    {
        return view('admin.dashboard',array(
            'judul' => "Dashboard Administrator | SIBEL V.2.0",
            'aktifTag' => "admin",
            'tagSubMenu' => "admin",
            'user' => "administrator",
        ));
    }
}
