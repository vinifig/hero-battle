package com.justiceleague.hero.domain.hero

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("hero")
data class Hero(

    @Id
    val id: Long?,

    val name: String,

    val skills: Set<Skill>

) {
    fun update(newName: String, newSkills: Set<Skill>): Hero {
        return this.copy(
            name = newName,
            skills = newSkills
        )
    }
}