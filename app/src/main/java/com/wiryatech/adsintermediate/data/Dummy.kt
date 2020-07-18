package com.wiryatech.adsintermediate.data

import com.wiryatech.adsintermediate.R

object Dummy {

    var titleMovie  = arrayOf(
        "Avengers: Endgame",
        "A Rainy Day in New York",
        "Sonic the Hedgehog",
        "Master and Commander: The Far Side of the World"
    )

    var descMovie  = arrayOf(
        "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
        "Two young people arrive in New York to spend a weekend, but once they arrive they're met with bad weather and a series of adventures.",
        "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
        "After an abrupt and violent encounter with a French warship inflicts severe damage upon his ship, a captain of the British Royal Navy begins a chase over two oceans to capture or destroy the enemy, though he must weigh his commitment to duty and ferocious pursuit of glory against the safety of his devoted crew, including the ship's thoughtful surgeon, his best friend."
    )

    var genreMovie  = arrayOf(
        "Comedy, Romance",
        "Comedy, Action, Family",
        "Drama, Advanture, Mystery",
        "Action, Time Travel, Avengers"
    )

    var posterMovie  = intArrayOf(
        R.drawable.avenger,
        R.drawable.rainy_day,
        R.drawable.sonic,
        R.drawable.master_commander
    )

    var trailerMovie  = intArrayOf(
        R.raw.videoplayback,
        R.raw.video_a_rainy_day,
        R.raw.video_sonic,
        R.raw.video_sample
    )

    var ratingMovie  = floatArrayOf(
        4.0F,
        3.0F,
        2.0F,
        5.0f
    )

}