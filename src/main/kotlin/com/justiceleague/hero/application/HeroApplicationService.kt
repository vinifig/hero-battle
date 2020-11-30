package com.justiceleague.hero.application

import com.justiceleague.hero.domain.battle.Battle
import com.justiceleague.hero.domain.battle.BattleRepository
import com.justiceleague.hero.domain.common.NotFoundException
import com.justiceleague.hero.domain.hero.Hero
import com.justiceleague.hero.domain.hero.HeroRepository
import com.justiceleague.hero.domain.hero.Skill
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@Service
class HeroApplicationService(
    private val heroRepository: HeroRepository,
    private val battleRepository: BattleRepository
) {

    @Transactional
    fun createHero(name: String, skills: Set<Skill>): Hero {
        val hero = Hero(null, name, skills)

        return heroRepository.save(hero)
    }

    @Transactional
    fun updateHero(heroId: Long, name: String, skills: Set<Skill>): Hero {
        val hero = heroRepository.findById(heroId)
            .orElseThrow { NotFoundException("hero") }
            .update(name, skills)

        return heroRepository.save(hero)
    }

    @Transactional(readOnly = true)
    fun getHero(heroId: Long): Hero {
        return heroRepository.findById(heroId)
            .orElseThrow { NotFoundException("hero") }
    }

    @Transactional
    fun createBattle(firstHeroId: Long, secondHeroId: Long): Battle {
        val heroes = heroRepository.findAllById(listOf(firstHeroId, secondHeroId))

        val firstHero = heroes.find { it.id == firstHeroId } ?: throw NotFoundException("hero")
        val secondHero = heroes.find { it.id == secondHeroId } ?: throw NotFoundException("hero")

        val (winner, loser) = when(firstHero.getPower() >= secondHero.getPower()) {
            true -> firstHeroId to secondHeroId
            else -> secondHeroId to firstHeroId
        }

        val battle = Battle(
            null,
            winner,
            loser,
            OffsetDateTime.now().toEpochSecond()
        )

        return battleRepository.save(battle)
    }

    fun getBattles(): List<Battle> {
        return battleRepository.findAll().toList()
    }
}