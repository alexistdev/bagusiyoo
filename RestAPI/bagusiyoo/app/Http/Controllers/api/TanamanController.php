<?php

namespace App\Http\Controllers\api;

use App\Http\Controllers\Controller;
use App\Models\Tanaman;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class TanamanController extends Controller
{
    public function dataSpinner(Request $request)
    {
        $tanaman = Tanaman::all();
        if($tanaman->isEmpty()){
            return response()->json([
                'status' => false,
                'message' => "Data Kosong",
            ], 404);
        } else {
            return response()->json(array(
                'status' => true,
                'message' => 'Data berhasil didapatkan',
                'result' => $tanaman,
            ),200);
        }
    }
}
