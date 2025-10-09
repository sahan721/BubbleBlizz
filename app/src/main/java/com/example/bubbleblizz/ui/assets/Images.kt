package com.example.bubbleblizz.ui.assets
import androidx.annotation.DrawableRes
import com.example.bubbleblizz.R
object Images {
    @DrawableRes fun ofName(name: String): Int = when(name){
        "img_papaya"-> R.drawable.img_papaya; "img_orange"->R.drawable.img_orange; "img_passion"->R.drawable.img_passion;
        "img_lemon"->R.drawable.img_lemon; "img_apple"->R.drawable.img_apple; "img_pineapple"->R.drawable.img_pineapple;
        "img_strawberry"->R.drawable.img_strawberry; "img_grape"->R.drawable.img_grape; "img_mango"->R.drawable.img_mango;
        "img_matcha"->R.drawable.img_matcha; "img_lychee"->R.drawable.img_lychee; else-> R.drawable.img_placeholder
    }
}