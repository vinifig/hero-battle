package com.justiceleague.hero.port.controller

import com.justiceleague.hero.application.HeroApplicationService
import com.justiceleague.hero.domain.battle.Battle
import com.justiceleague.hero.domain.common.NotFoundException
import com.justiceleague.hero.domain.hero.Hero
import com.justiceleague.hero.port.controller.model.BattleInput
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/heroes")
class HeroController(
    private val heroApplicationService: HeroApplicationService
) {

    @PostMapping
    fun createHero(@RequestBody hero: Hero): ResponseEntity<Hero> {
        val createdHero = heroApplicationService.createHero(hero.name, hero.skills)

        return ResponseEntity(createdHero, HttpStatus.CREATED)
    }

    @PutMapping("{heroId}")
    fun updateHero(@PathVariable heroId: Long, @RequestBody hero: Hero): ResponseEntity<Hero> {
        return try {
            val updatedHero = heroApplicationService.updateHero(heroId, hero.name, hero.skills)

            ResponseEntity(updatedHero, HttpStatus.OK)
        } catch (notFoundException: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("{heroId}")
    fun getHero(@PathVariable heroId: Long): ResponseEntity<Hero> {
        return try {
            val hero = heroApplicationService.getHero(heroId)

            ResponseEntity(hero, HttpStatus.OK)
        } catch (notFoundException: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/battles")
    fun createBattle(@RequestBody battleInput: BattleInput): ResponseEntity<Battle> {
        return try {
            val battle = heroApplicationService.createBattle(
                battleInput.firstHero,
                battleInput.secondHero
            )

            ResponseEntity(battle, HttpStatus.CREATED)
        } catch (notFoundException: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/battles")
    fun getBattles(): List<Battle> {
        return heroApplicationService.getBattles()
    }

}