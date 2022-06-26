<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Aktivitas extends Model
{
    use HasFactory;
    protected $fillable = [
        'waktutanam_id', 'name'
    ];

    public function waktutanam()
    {
        return $this->belongsTo(Waktutanam::class);
    }


}
