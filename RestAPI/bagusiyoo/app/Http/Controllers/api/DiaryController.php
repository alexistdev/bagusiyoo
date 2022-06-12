<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Models\Aktivitas;
use App\Models\Book;
use App\Models\Waktutanam;
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

    public function get_aktivitas(Request $request)
    {
        $validation = Validator::make($request->all(),[
            'idTanaman' => 'required|numeric',
        ]);
        if($validation->fails()){
            return response()->json(array(
                'status' => false,
                'message' => 'Data, tidak lengkap',
            ),404);
        } else {
            $tanggal = date("Y-m-d", strtotime(now()));
             $waktuTanam = Waktutanam::where('tanaman_id',$request->idTanaman)->where('tanggal',$tanggal)->first();


            $aktivitas = Aktivitas::where('waktutanam_id',$waktuTanam->id)->get();
            return response()->json(array(
                'status' => true,
                'message' => 'Data aktivitas berhasil didapatkan',
                'result' => $aktivitas,
            ),200);
        }
    }
}
