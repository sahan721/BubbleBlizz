package com.example.bubbleblizz.ui.assets
import androidx.annotation.DrawableRes
import com.example.bubbleblizz.R
object Images {
    @DrawableRes fun ofName(name: String): Int = when(name){

        //Fruit Juices
        "img_papaya" -> R.drawable.img_papaya
        "img_orange" -> R.drawable.img_orange
        "img_passion" -> R.drawable.img_passion
        "img_lemon" -> R.drawable.img_lemon
        "img_apple" -> R.drawable.img_apple
        "img_pineapple" -> R.drawable.img_pineapple
        "img_strawberry" -> R.drawable.img_strawberry
        "img_grape" -> R.drawable.img_grape
        "img_mango" -> R.drawable.img_mango
        "img_watermelon" -> R.drawable.img_watermelon

        //Dairy Drinks
        "img_choco_milk" -> R.drawable.img_chocomilk
        "img_strawberry_milk" -> R.drawable.img_strawberrymilk
        "img_banana_milk" -> R.drawable.img_bananamilk
        "img_vanilla_milkshake" -> R.drawable.img_vanillamilkshake
        "img_milo" -> R.drawable.img_milo
        "img_coffee_milk" -> R.drawable.img_coffeemilk
        "img_oreo_shake" -> R.drawable.img_oreoshake
        "img_caramel_latte" -> R.drawable.img_caramellatte



        //Soft Drinks
        "img_coke" -> R.drawable.img_coke
        "img_sprite" -> R.drawable.img_sprite
        "img_pepsi" -> R.drawable.img_pepsi
        "img_fanta" -> R.drawable.img_fanta



        //Energy Drinks
        "img_sting" -> R.drawable.img_sting
        "img_redbull" -> R.drawable.img_redbull
        "img_monster" -> R.drawable.img_monster
        "img_rockstar" -> R.drawable.img_rockstar


        else-> R.drawable.img_placeholder
    }
}