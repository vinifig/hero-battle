package com.justiceleague.hero.domain.battle

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("battle")
data class Battle(

    @Id
    val id: Long?,

    val winnerHeroId: Long,

    val loserHeroId: Long,

    val timestamp: Long

)