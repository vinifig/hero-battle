package com.justiceleague.hero.application

import com.justiceleague.hero.domain.common.NotFoundException
import com.justiceleague.hero.domain.hero.Hero
import com.justiceleague.hero.domain.hero.HeroRepository
import com.justiceleague.hero.domain.hero.Skill
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class HeroApplicationService(
    private val heroRepository: HeroRepository
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
}