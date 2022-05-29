<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Models\Book;
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
}
