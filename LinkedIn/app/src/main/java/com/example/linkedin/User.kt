package com.example.linkedin

data class User (
    val id: String,
    val name: String,
    val dateOfBirth: String,
    val universityLocation: String,
    val field: String,
    val workplace: String,
    val specialties: List<String>,
    val connectionId: List<String>
){

    fun giveScore (user2: User): Int{
        var score: Int = 0
        if(this.universityLocation == user2.universityLocation){
            score += 100
        }
        return score
    }
}
