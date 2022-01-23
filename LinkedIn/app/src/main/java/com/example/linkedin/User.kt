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

    fun giveScore(user2: User, s: Boolean, f: Boolean, w: Boolean, u: Boolean): Double {
        var score = 0.0
        var sMultiplier = 1.0
        var fMultiplier = 1.0
        var wMultiplier = 1.0
        var uMultiplier = 1.0
        if(s){
            sMultiplier = 2.0
        }
        if(f){
            fMultiplier = 2.0
        }
        if(w){
            wMultiplier = 1.5
        }
        if(u){
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
        for (i in this.specialties) {
            for (j in user2.specialties) {
                if (i == j) {
                    score += (150 * sMultiplier)
                }
            }
        }
        return score
    }
}
