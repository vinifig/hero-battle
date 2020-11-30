package com.justiceleague.hero.config

import com.justiceleague.hero.application.HeroApplicationService
import com.justiceleague.hero.domain.common.NotFoundException
import com.justiceleague.hero.domain.hero.Hero
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
        val hero = heroApplicationService.createHero(hero.name, hero.skills)

        return ResponseEntity(hero, HttpStatus.CREATED)
    }

    @PutMapping("{heroId}")
    fun updateHero(@PathVariable heroId: Long, @RequestBody hero: Hero): ResponseEntity<Hero> {
        return try {
            val hero = heroApplicationService.updateHero(heroId, hero.name, hero.skills)

            ResponseEntity(hero, HttpStatus.OK)
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

}