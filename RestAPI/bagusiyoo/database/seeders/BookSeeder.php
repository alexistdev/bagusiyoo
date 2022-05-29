<?php

namespace Database\Seeders;

use App\Models\Book;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Carbon;

class BookSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $date = Carbon::now()->format('Y-m-d H:i:s');
        $book = [
            'user_id' => 2,
            'tanaman_id' => 1,
            'harike' => 1,
            'panen' => "2022-06-30",
            'created_at' => $date,
            'updated_at' => $date
        ];
        Book::insert($book);
    }
}
