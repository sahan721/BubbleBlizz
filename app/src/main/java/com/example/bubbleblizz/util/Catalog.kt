package com.example.bubbleblizz.util

object Catalog {
    val products = listOf(

        // FRUIT JUICES
        Product("papaya","Papaya Juice","500 ml",650,4.5,
            "Naturally sweet and rich in vitamin C and antioxidants.","img_papaya","Fruit Juice"),
        Product("orange","Orange Juice","500 ml",650,4.4,
            "Bright, tangy and refreshing, packed with vitamin C.","img_orange","Fruit Juice"),
        Product("apple","Apple Juice","500 ml",640,4.3,
            "Smooth and sweet, made from ripe apples.","img_apple","Fruit Juice"),
        Product("grape","Grape Juice","500 ml",690,4.2,
            "Deep smooth flavor, rich in antioxidants.","img_grape","Fruit Juice"),
        Product("lemon","Lemon Juice","500 ml",580,4.3,
            "Zesty, refreshing and high in vitamin C.","img_lemon","Fruit Juice"),
        Product("pineapple","Pineapple Juice","500 ml",700,4.5,
            "Vibrant tropical taste with sweetness and acidity.","img_pineapple","Fruit Juice"),
        Product("mango","Mango Juice","500 ml",720,4.6,
            "Thick, sweet and tropical with smooth texture.","img_mango","Fruit Juice"),
        Product("strawberry","Strawberry Juice","500 ml",680,4.4,
            "Bright, fruity and aromatic with a fresh berry finish.","img_strawberry","Fruit Juice"),
        Product("passion","Passion Juice","500 ml",620,4.6,
            "Tangy-sweet flavor with a fragrant aroma.","img_passion","Fruit Juice"),
        Product("watermelon","Watermelon Juice","500 ml",600,4.3,
            "Hydrating and refreshing summer favorite.","img_watermelon","Fruit Juice"),

        // DAIRY DRINKS
        Product("choco_milk","Chocolate Milk","400 ml",550,4.5,
            "Rich creamy milk blended with cocoa.","img_choco_milk","Dairy Drinks"),
        Product("strawberry_milk","Strawberry Milk","400 ml",560,4.4,
            "Sweet strawberry-flavored chilled milk.","img_strawberry_milk","Dairy Drinks"),
        Product("banana_milk","Banana Milk","400 ml",540,4.5,
            "Smooth banana flavor in creamy milk.","img_banana_milk","Dairy Drinks"),
        Product("vanilla_milkshake","Vanilla Milkshake","500 ml",580,4.6,
            "Classic vanilla shake with rich, thick texture.","img_vanilla_milkshake","Dairy Drinks"),
        Product("milo","Milo","500 ml",600,4.7,
            "Chocolate-malt energy drink loved by all.","img_milo","Dairy Drinks"),
        Product("coffee_milk","Coffee Milk","400 ml",590,4.5,
            "Mild coffee taste mixed with milk sweetness.","img_coffee_milk","Dairy Drinks"),
        Product("oreo_shake","Oreo Milkshake","500 ml",620,4.8,
            "Crunchy Oreos blended into creamy shake.","img_oreo_shake","Dairy Drinks"),
        Product("caramel_latte","Caramel Latte","400 ml",650,4.6,
            "Sweet caramel with steamed milk and espresso.","img_caramel_latte","Dairy Drinks"),
        Product("matcha_latte","Matcha Latte","400 ml",660,4.7,
            "Creamy blend of matcha and milk.","img_matcha","Dairy Drinks"),
        Product("hazelnut_milk","Hazelnut Milk","400 ml",640,4.4,
            "Nutty, slightly sweet and refreshing milk.","img_hazelnut_milk","Dairy Drinks"),

        // SOFT DRINKS
        Product("cola","Cola","330 ml",480,4.3,"Classic fizzy cola drink.","img_cola","Soft Drink"),
        Product("sprite","Sprite","330 ml",470,4.4,"Lime-lemon flavored soda.","img_sprite","Soft Drink"),
        Product("fanta","Fanta Orange","330 ml",460,4.2,"Orange-flavored sparkling soft drink.","img_fanta","Soft Drink"),
        Product("soda","Soda Water","330 ml",400,4.1,"Plain sparkling carbonated water.","img_soda","Soft Drink"),
        Product("ginger_beer","Ginger Beer","330 ml",490,4.4,"Spicy and tangy ginger soda.","img_ginger_beer","Soft Drink"),
        Product("tonic","Tonic Water","330 ml",420,4.3,"Quinine-flavored refreshing tonic.","img_tonic","Soft Drink"),
        Product("cream_soda","Cream Soda","330 ml",450,4.2,"Smooth creamy vanilla soda.","img_cream_soda","Soft Drink"),
        Product("root_beer","Root Beer","330 ml",460,4.3,"Classic American herbal soda.","img_root_beer","Soft Drink"),
        Product("lemonade","Lemonade","330 ml",440,4.5,"Sweet-tart lemon sparkling drink.","img_lemonade","Soft Drink"),
        Product("apple_soda","Apple Soda","330 ml",470,4.4,"Fizzy apple-flavored soda.","img_apple_soda","Soft Drink"),

        // ENERGY DRINKS
        Product("redbull","Red Bull","250 ml",650,4.6,"Boosts energy and focus.","img_redbull","Energy Drink"),
        Product("monster","Monster Energy","500 ml",700,4.5,"Bold taste with powerful energy.","img_monster","Energy Drink"),
        Product("sting","Sting","250 ml",500,4.4,"Sweet-tangy flavor energy boost.","img_sting","Energy Drink"),
        Product("reload","Reload","500 ml",650,4.3,"Tropical energy drink.","img_reload","Energy Drink"),
        Product("cloud9","Cloud 9","500 ml",640,4.4,"Smooth taste with caffeine boost.","img_cloud9","Energy Drink"),
        Product("volt","Volt Energy","500 ml",670,4.3,"Electrifying citrus flavor.","img_volt","Energy Drink"),
        Product("powerup","Power Up","500 ml",680,4.5,"Invigorating drink for long days.","img_powerup","Energy Drink"),
        Product("boost","Boost","250 ml",600,4.5,"Chocolate-flavored energy drink.","img_boost","Energy Drink"),
        Product("shark","Shark","500 ml",710,4.6,"Refreshing tropical-flavored energy drink.","img_shark","Energy Drink"),
        Product("burn","Burn","500 ml",720,4.4,"Smooth stimulating blend.","img_burn","Energy Drink")
    ).associateBy { it.id }

    fun get(id: String): Product =
        products[id] ?: Product(
            "unknown",
            "Unknown",
            "",
            0,
            0.0,
            "No description available",
            "img_placeholder",
            "Unknown")

    fun getByCategory(category: String): List<Product> =
        products.values.filter { it.category == category }
}
