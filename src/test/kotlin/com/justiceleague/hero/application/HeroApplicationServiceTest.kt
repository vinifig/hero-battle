package com.justiceleague.hero.application

import com.justiceleague.hero.domain.common.NotFoundException
import com.justiceleague.hero.domain.hero.Hero
import com.justiceleague.hero.domain.hero.HeroRepository
import com.justiceleague.hero.domain.hero.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class HeroApplicationServiceTest(
    @Mock private val heroRepositoryMock: HeroRepository
) {

    private val heroApplicationService = HeroApplicationService(heroRepositoryMock)

    @Test
    fun `#createHero must create a new hero`() {
        val name = "hero name"
        val skills = setOf(Skill(1, "hero skill", 1))

        `when`(heroRepositoryMock.save(Mockito.any(Hero::class.java))).then { it.arguments.first() }

        val returnedHero = heroApplicationService.createHero(name, skills)

        assertThat(returnedHero.name).isEqualTo(name)
        assertThat(returnedHero.skills).isEqualTo(skills)

        verify(heroRepositoryMock).save(any(Hero::class.java))
    }

    @Test
    fun `#updateHero must update a existent hero`() {
        val id = 1L
        val name = "hero name"
        val skills = setOf(Skill(1, "hero skill", 1))
        val hero = Hero(id, name, skills)

        `when`(heroRepositoryMock.findById(id)).thenReturn(Optional.of(hero))
        `when`(heroRepositoryMock.save(any(Hero::class.java))).then { it.arguments.first() }

        val returnedHero = heroApplicationService.updateHero(id, name, skills)

        assertThat(returnedHero.name).isEqualTo(name)
        assertThat(returnedHero.skills).isEqualTo(skills)

        verify(heroRepositoryMock).findById(id)
        verify(heroRepositoryMock).save(any(Hero::class.java))
    }

    @Test
    fun `#updateHero must update throw NotFound if the hero not exists`() {
        val id = 1L
        val name = "hero name"
        val skills = setOf(Skill(1, "hero skill", 1))

        `when`(heroRepositoryMock.findById(id)).thenReturn(Optional.empty())

        assertThrows<NotFoundException> {
            heroApplicationService.updateHero(id, name, skills)
        }

        verify(heroRepositoryMock).findById(id)
        verify(heroRepositoryMock, never()).save(any(Hero::class.java))
    }

    @Test
    fun `#getHero must return a existent hero`() {
        val id = 1L
        val name = "hero name"
        val skills = setOf(Skill(1, "hero skill", 1))
        val hero = Hero(id, name, skills)

        `when`(heroRepositoryMock.findById(id)).thenReturn(Optional.of(hero))

        val returnedHero = heroApplicationService.getHero(id)

        assertThat(returnedHero.id).isEqualTo(id)
        assertThat(returnedHero.name).isEqualTo(name)
        assertThat(returnedHero.skills).isEqualTo(skills)

        verify(heroRepositoryMock).findById(id)
    }

    @Test
    fun `#getHero must return a not found if the hero not exists`() {
        val id = 1L
        val name = "hero name"
        val skills = setOf(Skill(1, "hero skill", 1))

        `when`(heroRepositoryMock.findById(id)).thenReturn(Optional.empty())

        assertThrows<NotFoundException> {
            heroApplicationService.getHero(id)
        }

        verify(heroRepositoryMock).findById(id)
    }

}