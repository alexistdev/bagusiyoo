<?php

namespace Database\Seeders;

use App\Models\Tanaman;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Carbon;

class TanamanSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $date = Carbon::now()->format('Y-m-d H:i:s');
        $tanaman = [
            array('name' => 'Tomat','created_at' => $date,'updated_at' => $date),
            array('name' => 'Melon','created_at' => $date,'updated_at' => $date),
        ];
        Tanaman::insert($tanaman);
    }
}
