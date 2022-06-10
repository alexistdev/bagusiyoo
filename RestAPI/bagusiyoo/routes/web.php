<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Admin\{DashboardController as DashAdm};

Route::get('/', function () {
    return view('welcome');
});

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth'])->name('dashboard');
Route::group(['middleware' => ['web', 'auth', 'roles']], function () {
    Route::group(['roles' => 'admin'], function () {
        Route::get('/admin/dashboard', [DashAdm::class, 'index'])->name('admin.dashboard');
    });
});

require __DIR__.'/auth.php';
