package com.example.linkedin

data class User(
    val id: String,
    val name: String,
    val dateOfBirth: String,
    val universityLocation: String,
    val field: String,
    val workplace: String,
    val specialties: List<String>,
    val connectionId: List<String>
) {
    var degree: Int = 5
    fun giveScore(user2: User, s: Boolean, f: Boolean, w: Boolean, u: Boolean): Double {
        var score = 0.0
        var sMultiplier = 1.0
        var fMultiplier = 1.0
        var wMultiplier = 1.0
        var uMultiplier = 1.0
        if (s) {
            sMultiplier = 2.0
        }
        if (f) {
            fMultiplier = 2.0
        }
        if (w) {
            wMultiplier = 1.5
        }
        if (u) {
            uMultiplier = 2.0
        }

        if (this.universityLocation == user2.universityLocation) {
            score += (75 * uMultiplier)
        }
        if (this.field == user2.field) {
            score += (75 * fMultiplier)
        }
        if (this.workplace == user2.workplace) {
            score += (100 * wMultiplier)
        }
        val temp: List<String> = this.specialties
        temp.intersect(user2.specialties.toSet())
        score += (150 * temp.size * sMultiplier)

        score += ((5 - user2.degree) * 50)

        return score
    }
}
