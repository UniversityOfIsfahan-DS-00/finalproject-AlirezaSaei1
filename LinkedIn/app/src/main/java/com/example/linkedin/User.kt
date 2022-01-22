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

    fun giveScore(user2: User): Int {
        var score = 0
        if (this.universityLocation == user2.universityLocation) {
            score += 150
        }
        if (this.field == user2.field) {
            score += 150
        }
        if (this.workplace == user2.workplace) {
            score += 200
        }
        for (i in this.specialties) {
            for (j in user2.specialties) {
                if (i == j) {
                    score += 100
                }
            }
        }
        return score
    }
}
