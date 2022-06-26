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
        Route::delete('/admin/tanaman', [TanAdmin::class, 'destroy'])->name('admin.deletetanaman');

        Route::get('/admin/{id}/tanaman', [TanAdmin::class, 'detail'])->name('admin.detailtanaman');
//        Route::get('/admin/{hari}/aktivitas', function () {
//            return abort('404');
//        });
        Route::post('/admin/{hari}/aktivitas', [TanAdmin::class, 'simpan_aktivitas'])->name('admin.tambahaktivitas');
        Route::patch('/admin/{hari}/aktivitas', [TanAdmin::class, 'edit_aktivitas'])->name('admin.editaktivitas');
        Route::delete('/admin/{hari}/aktivitas', [TanAdmin::class, 'delete_aktivitas'])->name('admin.deleteaktivitas');


        Route::get('/admin/ajax/aktivitas', [TanAdmin::class, 'data_aktivitas'])->name('admin.aktivitastanaman');
    });
});

require __DIR__.'/auth.php';
