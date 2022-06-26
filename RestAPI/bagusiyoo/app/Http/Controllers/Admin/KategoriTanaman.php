<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Aktivitas;
use App\Models\Produk;
use App\Models\Rpo;
use App\Models\Tanaman;
use App\Models\Waktutanam;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;
use Illuminate\Validation\Rule;
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
                    data-target="#modalEdit" ><i class="fas fa-edit"></i> Edit</a>';
                    $btn2 = $btn.'<a href="'.route('admin.detailtanaman',$row->id).'" class="btn btn-primary btn-sm m-1"><i class="fas fa-list"></i> Data</a>';
                    $btn3 = $btn2.'<a href="" class="btn btn-danger btn-sm m-1 open-hapusTanaman" data-id="'.$row->id.'" data-nama="'.$row->name.'" data-toggle="modal" data-target="#modalHapus"><i class="fas fa-trash"></i> Hapus</a>';
                    return $btn3;
                })
                ->rawColumns(['action'])
                ->make(true);
        }

        return view('admin.tanaman',array(
            'judul' => "Dashboard Administrator | Diary 1.0",
            'aktifTag' => "admin",
            'tagSubMenu' => "admin",
            'user' => "administrator",
        ));
    }


    public function store(Request $request)
    {
        $rules = [
            'nama' => 'required|max:50|unique:tanamans,name',
            'waktuTanam' => 'required|numeric',
        ];
        $message = [
            'nama.required' => "Nama Tanaman harus diisi !",
            'nama.max' => "Maksimal karakter adalah 50 karakter !",
            'nama.unique' => "Nama sudah pernah digunakan sebelumnya !",
            'waktuTanam.required' => "Waktu Tanam harus diisi !",
            'waktuTanam.numeric' => "Waktu Tanam harus berupa angka!",
        ];
        $request->validateWithBag('tambah', $rules,$message);
        DB::beginTransaction();
        try {
            $tanaman = new Tanaman();
            $tanaman->name = $request->nama;
            $tanaman->save();
            $idTanaman = $tanaman->id;
            $dataWaktu = [];
            $mulai = date('Y-m-d');
            for($i=1; $i <= $request->waktuTanam;$i++){
                $xTanggal = strtotime( "+".$i." day", strtotime($mulai));
                $tanggal = date("Y-m-d", $xTanggal);
                $dataWaktu[] = [
                    'tanaman_id' => $idTanaman,
                    'hari_ke' => $i,
                    'tanggal' => $tanggal,
                    'created_at' => Carbon::now(),
                    'updated_at' => Carbon::now(),
                ];
            }
            Waktutanam::insert($dataWaktu);
            DB::commit();
            return redirect(route('admin.tanaman'))->with(['success' => 'Data tanaman berhasil ditambahkan']);
        } catch (\Exception $e) {
            DB::rollback();
            return redirect(route('admin.tanaman'))->with(['error' => $e->getMessage()]);
        }
    }

    public function update(Request $request)
    {
        $rules = [
            'idTanaman' => 'required|numeric',
            'nama' => [
                'required',
                'max:50',
                Rule::unique('tanamans','name')->ignore($request->idTanaman),
            ],
        ];
        $message = [
            'nama.required' => "Nama Tanaman harus diisi !",
            'nama.max' => "Maksimal karakter adalah 50 karakter !",
            'nama.unique' => "Nama sudah pernah digunakan sebelumnya !",
            'idTanaman.required' => "Id Tanaman tidak ditemukan,silahkan refresh halaman !",
            'idTanaman.numeric' => "Id Tanaman tidak ditemukan,silahkan refresh halaman !",

        ];
        $request->validateWithBag('edit', $rules,$message);
        $tanaman = Tanaman::findOrFail($request->idTanaman);
        DB::beginTransaction();
        try {
            $tanaman->update([
                'name' => $request->nama,
            ]);
            DB::commit();
            return redirect(route('admin.tanaman'))->with(['success' => 'Data tanaman berhasil diperbaharui']);
        } catch (\Exception $e) {
            DB::rollback();
            return redirect(route('admin.tanaman'))->with(['error' => $e->getMessage()]);
        }
    }

    public function destroy(Request $request)
    {
        $rules = [
            'idTanaman' => 'required|numeric',
        ];
        $message = [
            'idTanaman.required' => "Id Tanaman tidak ditemukan,silahkan refresh halaman !",
            'idTanaman.numeric' => "Id Tanaman tidak ditemukan,silahkan refresh halaman !",
        ];
        $request->validateWithBag('hapus', $rules,$message);
        $tanaman = Tanaman::findOrFail($request->idTanaman);
        DB::beginTransaction();
        try {
            $tanaman->delete();
            DB::commit();
            return redirect(route('admin.tanaman'))->with(['success' => 'Data tanaman berhasil dihapus']);
        } catch (\Exception $e) {
            DB::rollback();
            return redirect(route('admin.tanaman'))->with(['error' => $e->getMessage()]);
        }
    }

    public function detail($id)
    {
        Tanaman::findOrFail($id);
//        return $aktivitas = Aktivitas::all();
       $waktuTanam = Waktutanam::with('aktif')->where('tanaman_id',$id)->get();

        return view('admin.detailtanaman', array(
            'judul' => "Dashboard Administrator | Diary 1.0",
            'aktifTag' => "admin",
            'tagSubMenu' => "admin",
            'user' => "administrator",
            'waktuTanam' => $waktuTanam,
            'idTanaman' => $id,
        ));
    }

    public function data_aktivitas(Request $request)
    {
        if ($request->ajax()) {
//            $aktivitas = Aktivitas::all();
            $aktivitas = Aktivitas::with('waktutanam')->where('waktutanam_id',$request->idTanam)->get();
            return DataTables::of($aktivitas)
                ->addIndexColumn()

                ->addColumn('action', function ($row) {
                    $btn = '<button data-id="' . $row->id . '" class="btn btn-sm btn-primary m-1 open-editAktivitas"  data-tanaman="'.$row->waktutanam->tanaman_id.'"data-hari="'.$row->waktutanam_id.'" data-nama="'.$row->name.'"data-toggle="modal" data-target="#modalEditAktivitas" ><i class="fas fa-edit"></i> Edit</button>';
                    $btn = $btn . '<button  data-id="' . $row->id . '" data-tanaman="'.$row->waktutanam->tanaman_id.'" data-hari="'.$row->waktutanam_id.'" class="btn btn-sm btn-danger m-1 open-hapusAktivitas" data-toggle="modal" data-target="#modalHapusAktivitas" ><i class="fas fa-minus-circle"></i> Hapus</button>';
                    return $btn;
                })
                ->rawColumns(['action'])
                ->make(true);
        }
    }

    public function simpan_aktivitas(Request $request,$hari)
    {
       Waktutanam::findOrFail($hari);
        $rules = [
            'nama' => 'required|max:255',
            'idtanaman' => 'required|numeric',
        ];
        $message = [
            'nama.required' => "Nama aktivitas harus diisi !",
            'nama.max' => "Panjang karakter nama aktivitas maksimal adalah 255 karakter !",
        ];
        $request->validateWithBag('tambahAktivitas', $rules,$message);
        DB::beginTransaction();
        try{
            $aktivitas = new Aktivitas();
            $aktivitas->waktutanam_id = $hari;
            $aktivitas->name = $request->nama;
            $aktivitas->save();
            DB::commit();
            return redirect(route('admin.detailtanaman',$request->idtanaman))->with(['success' => 'Data aktivitas berhasil ditambahkan']);
        } catch (\Exception $e) {
            DB::rollback();
            return redirect(route('admin.detailtanaman',$request->idtanaman))->with(['error' => $e->getMessage()]);
        }
    }

    public function edit_aktivitas(Request $request, $hari)
    {
        Waktutanam::findOrFail($hari);
        $rules = [
            'nama' => 'required|max:255',
            'idtanaman' => 'required|numeric',
            'idaktivitas' => 'required|numeric',
        ];
        $message = [
            'nama.required' => "Nama aktivitas harus diisi !",
            'nama.max' => "Panjang karakter nama aktivitas maksimal adalah 255 karakter !",
        ];
        $request->validateWithBag('editAktivitas', $rules,$message);
        DB::beginTransaction();
        try{
            Aktivitas::where('id', $request->idaktivitas)->update([
                'name' => $request->nama,
            ]);
            DB::commit();
            return redirect(route('admin.detailtanaman',$request->idtanaman))->with(['success' => 'Data aktivitas berhasil diperbaharui']);
        } catch (\Exception $e) {
            DB::rollback();
            return redirect(route('admin.detailtanaman',$request->idtanaman))->with(['error' => $e->getMessage()]);
        }
    }

    public function delete_aktivitas(Request $request, $hari)
    {
        Waktutanam::findOrFail($hari);
        $rules = [
            'idtanaman' => 'required|numeric',
            'idaktivitas' => 'required|numeric',
        ];
        $message = [
            'idtanaman.required' => "id tidak ditemukan silahkan refresh halaman!",
            'idtanaman.numeric' => "id tidak ditemukan silahkan refresh halaman!",
            'idaktivitas.required' => "id tidak ditemukan silahkan refresh halaman!",
            'idaktivitas.numeric' => "id tidak ditemukan silahkan refresh halaman!",
        ];
        $request->validateWithBag('deleteAktivitas', $rules,$message);
        DB::beginTransaction();
        try{
            Aktivitas::where('id',$request->idaktivitas)->delete();
            DB::commit();
            return redirect(route('admin.detailtanaman',$request->idtanaman))->with(['hapus' => 'Data aktivitas berhasil dihapus']);
        } catch (\Exception $e) {
            DB::rollback();
            return redirect(route('admin.detailtanaman',$request->idtanaman))->with(['error' => $e->getMessage()]);
        }
    }
}
