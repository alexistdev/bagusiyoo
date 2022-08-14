<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Waktutanam extends Model
{
    use HasFactory;
    protected $fillable =[
        'tanaman_id','hari_ke','tanggal'
    ];

    public function aktif(){
        return $this->hasMany(Aktivitas::class);
    }
    public function rate(){
        return $this->review()->avg('rate'); // chain your query here
    }

}
