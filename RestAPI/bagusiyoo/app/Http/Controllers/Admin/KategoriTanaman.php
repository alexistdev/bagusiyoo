<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Produk;
use App\Models\Tanaman;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Yajra\DataTables\DataTables;

class KategoriTanaman extends Controller
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

    public function index(Request $request)
    {
        if ($request->ajax()) {
            $tanaman = Tanaman::orderBy('id','DESC')->get();
            return DataTables::of($tanaman)
                ->addIndexColumn()
                ->addColumn('action', function ($row) {
                    $btn = '<a href="" class="btn btn-success btn-sm m-1 open-editTanaman"
                    data-toggle="modal"
                    data-id="' . $row->id . '"
                    data-nama="' . $row->name . '"
                    data-target="#modalEdit"><i class="fas fa-edit"></i> Edit</a>';
                    $btn2 = $btn.'<a href="" class="btn btn-danger btn-sm m-1 open-hapusProduk" data-id="'.$row->id.'" data-nama="'.$row->name.'" data-toggle="modal" data-target="#modalHapus"><i class="fas fa-trash"></i> Hapus</a>';
                    return $btn2;
                })
                ->rawColumns(['action'])
                ->make(true);
        }

        return view('admin.tanaman',array(
            'judul' => "Dashboard Administrator | SIBEL V.2.0",
            'aktifTag' => "admin",
            'tagSubMenu' => "admin",
            'user' => "administrator",
        ));
    }

    public function store(Request $request)
    {
        $rules = [
            'nama' => 'required|max:50|unique:tanamans,name',
        ];
        $message = [
            'nama.required' => "Nama Tanaman harus diisi !",
            'nama.max' => "Maksimal karakter adalah 50 karakter !",
            'nama.unique' => "Nama sudah pernah digunakan sebelumnya !",
        ];
        $request->validateWithBag('tambah', $rules,$message);
        DB::beginTransaction();
        try {
            $tanaman = new Tanaman();
            $tanaman->name = $request->nama;
            $tanaman->save();
            DB::commit();
            return redirect(route('admin.tanaman'))->with(['success' => 'Data tanaman berhasil ditambahkan']);
        } catch (\Exception $e) {
            DB::rollback();
            return redirect(route('admin.tanaman'))->with(['error' => $e->getMessage()]);
        }
    }
}
