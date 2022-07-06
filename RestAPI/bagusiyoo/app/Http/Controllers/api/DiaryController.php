<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Models\Aktivitas;
use App\Models\Book;
use App\Models\Tanaman;
use App\Models\Waktutanam;
use Carbon\Carbon;
use DateTime;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class DiaryController extends Controller
{
    public function get_diary (Request $request)
    {
        $validation = Validator::make($request->all(),[
            'idUser' => 'required|numeric',
        ]);
        if($validation->fails()){
            return response()->json(array(
                'status' => false,
                'message' => 'Data, tidak lengkap',
            ),404);
        } else {
            $data = Book::where('user_id',$request->idUser)->get();
            if(!$data->isEmpty()){
                return response()->json(array(
                    'status' => true,
                    'message' => 'Data diary berhasil didapatkan',
                    'result' => $data,
                ),200);
            }else{
                return response()->json(array(
                    'status' => false,
                    'message' => 'Gagal, data tidak ditemukan!',
                ),404);
            }
        }
    }

    /**
     * @param Request $request
     * idBook & idTanaman
     */
    public function get_aktivitas(Request $request)
    {
        $validation = Validator::make($request->all(),[
            'idTanaman' => 'required|numeric',
            'idBook' => 'required|numeric',
        ]);
        if($validation->fails()){
            return response()->json(array(
                'status' => false,
                'message' => 'Data, tidak lengkap',
            ),404);
        } else {
            $tanaman = Tanaman::find($request->idTanaman);
            if($tanaman == null){
                return response()->json(array(
                    'status' => false,
                    'message' => 'Data tanaman tidak ditemukan!',
                ),404);
            } else {
                $book = Book::find($request->idBook);
                if($book == null){
                    return response()->json(array(
                        'status' => false,
                        'message' => 'Data Diary tidak ditemukan!',
                    ),404);
                } else {
                    //Mendapatkan selisih hari dari saat diary dibuat dengan waktu saat ini, untuk mendapatkan data aktivitas hari ke
                    $now = time();
                    $tanggalDibuat = strtotime("06-07-2022");
                    $datediff = $now - $tanggalDibuat;
                    $harikeDiary = (int) round($datediff / (60 * 60 * 24));
                    $waktuTanam = Waktutanam::where('tanaman_id',$request->idTanaman)->where('hari_ke',$harikeDiary+1)->first();
                    $aktivitas = Aktivitas::where('waktutanam_id',$waktuTanam->id)->get();
                    return response()->json(array(
                        'status' => true,
                        'message' => 'Data aktivitas berhasil didapatkan',
                        'result' => $aktivitas,
                    ),200);
                }
            }
        }
    }

    public function tambah_diary(Request $request)
    {
        $validation = Validator::make($request->all(),[
            'idTanaman' => 'required|numeric',
            'judul' => 'required|max:255',
            'id_user' => 'required|numeric',
        ]);
        if($validation->fails()){
            return response()->json(array(
                'status' => false,
                'message' => 'Data, tidak lengkap',
            ),404);
        } else {
            $tanaman = Tanaman::find($request->idTanaman);
            if($tanaman == null){
                return response()->json(array(
                    'status' => false,
                    'message' => 'Data tanaman tidak ditemukan!',
                ),404);
            } else {
                $diary = new Book();
                $panen = Waktutanam::where('tanaman_id',$request->idTanaman)->max('tanggal');
                $diary->user_id = $request->id_user;
                $diary->tanaman_id = $request->idTanaman;
                $diary->name = $request->judul;
                $diary->harike = 1;
                $diary->panen = $panen;
                $diary->save();
                return response()->json(array(
                    'status' => true,
                    'message' => 'Data aktivitas berhasil disimpan',
                ),200);
            }
        }
    }

    public function delete_diary(Request $request)
    {
        $validation = Validator::make($request->all(),[
            'idBook' => 'required|numeric',
        ]);
        if($validation->fails()){
            return response()->json(array(
                'status' => false,
                'message' => 'Data, tidak lengkap',
            ),404);
        } else {
            $book = Book::find($request->idBook);
            if($book == null){
                return response()->json(array(
                    'status' => false,
                    'message' => 'Data diary tidak ditemukan!',
                ),404);
            } else {
                $book->delete();
                return response()->json(array(
                    'status' => true,
                    'message' => 'Data diary berhasil dihapus',
                ),200);
            }
        }
    }
}
