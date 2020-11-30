package com.justiceleague.hero.domain.hero

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("hero")
data class Hero(

    @Id
    @JsonIgnore
    val id: Long?,

    val name: String,

    @MappedCollection(idColumn = "hero_id")
    val skills: Set<Skill>

) {
    fun update(newName: String, newSkills: Set<Skill>): Hero {
        return this.copy(
            name = newName,
            skills = newSkills
        )
    }

    fun getPower(): Int {
        return this.skills.sumBy { it.power }
    }
}