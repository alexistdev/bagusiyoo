<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Admin\{DashboardController as DashAdm,KategoriTanaman as TanAdmin};

Route::get('/', function () {
    return view('welcome');
});

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth'])->name('dashboard');
Route::group(['middleware' => ['web', 'auth', 'roles']], function () {
    Route::group(['roles' => 'admin'], function () {
        Route::get('/admin/dashboard', [DashAdm::class, 'index'])->name('admin.dashboard');
        Route::get('/admin/tanaman', [TanAdmin::class, 'index'])->name('admin.tanaman');
        Route::post('/admin/tanaman', [TanAdmin::class, 'store'])->name('admin.savetanaman');
        Route::patch('/admin/tanaman', [TanAdmin::class, 'update'])->name('admin.updatetanaman');
    });
});

require __DIR__.'/auth.php';
