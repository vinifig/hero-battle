package com.justiceleague.hero.domain.hero

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("skill")
data class Skill(

    @Id
    val id: Long?,

    val name: String,

    val power: Int

)
