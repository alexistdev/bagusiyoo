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
            $data = Book::with('tanaman')->where('user_id',$request->idUser)->get();
            if(!$data->isEmpty()){
                $now = time();
                $xData = collect();
                foreach($data as $row){
                    $tanggalDibuat = strtotime($row->created_at);
                    $datediff = $now - $tanggalDibuat;
                    $harikeDiary = (int) round($datediff / (60 * 60 * 24));
                    $datax['id'] = $row->id;
                    $datax['tanaman_id']=$row->tanaman_id;
                    $datax['name']=$row->name;
                    $datax['nama_tanaman']=$row->tanaman->name;
                    $datax['harike']=$harikeDiary;
                    $datax['created_at']=$row->created_at;
                    $datax['panen']=$row->panen;
                    $xData->push($datax);
                }
                return response()->json(array(
                    'status' => true,
                    'message' => 'Data diary berhasil didapatkan',
                    'result' => $xData,
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
                $book = Book::where('id',$request->idBook)->first();

                if($book == null){
                    return response()->json(array(
                        'status' => false,
                        'message' => 'Data Diary tidak ditemukan2!',
                    ),404);
                } else {
                    //Mendapatkan selisih hari dari saat diary dibuat dengan waktu saat ini, untuk mendapatkan data aktivitas hari ke
                    $now = time();
                    $tanggalDibuat = strtotime($book->created_at);
                    $datediff = $now - $tanggalDibuat;
                    $harikeDiary = (int) round($datediff / (60 * 60 * 24));
                    $waktuTanam = Waktutanam::where('tanaman_id',$request->idTanaman)->where('hari_ke',$harikeDiary)->first();
                    $aktivitas = Aktivitas::with('waktutanam')->where('waktutanam_id',$waktuTanam->id)->get();
                    $data=collect();
                    foreach($aktivitas as $row){
                        $datax['id'] =$row->id;
                        $datax['name'] =$row->name;
                        $datax['harike'] =$row->waktutanam->hari_ke;
                        $data->push($datax);
                    }
                    return response()->json(array(
                        'status' => true,
                        'message' => 'Data aktivitas berhasil didapatkan',
                        'harike' => $waktuTanam->hari_ke,
                        'result' => $data,
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
