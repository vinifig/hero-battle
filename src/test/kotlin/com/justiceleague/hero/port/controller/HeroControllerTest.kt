package com.justiceleague.hero.port.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.justiceleague.hero.application.HeroApplicationService
import com.justiceleague.hero.domain.common.NotFoundException
import com.justiceleague.hero.domain.hero.Hero
import com.justiceleague.hero.domain.hero.Skill
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(HeroController::class)
class HeroControllerTest(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var heroApplicationServiceMock: HeroApplicationService

    @Test
    fun `post heroes must return created`() {
        val name = "hero name"
        val skills = setOf(Skill(null, "hero skill", 1))
        val hero = Hero(null, name, skills)

        `when`(heroApplicationServiceMock.createHero(hero.name, hero.skills)).thenReturn(hero)

        val heroContent = objectMapper.writeValueAsString(hero)
        mockMvc.perform(post("/heroes")
            .content(heroContent)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.name", notNullValue()))
            .andExpect(jsonPath("$.skills", notNullValue()))
            .andExpect(jsonPath("$.skills.length()", equalTo(skills.size)))
    }

    @Test
    fun `get heroes must return a existent hero`() {
        val heroId = 1L
        val name = "hero name"
        val skills = setOf(Skill(null, "hero skill", 1))
        val hero = Hero(heroId, name, skills)

        `when`(heroApplicationServiceMock.getHero(heroId)).thenReturn(hero)

        mockMvc.perform(get("/heroes/$heroId"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name", notNullValue()))
            .andExpect(jsonPath("$.skills", notNullValue()))
            .andExpect(jsonPath("$.skills.length()", equalTo(skills.size)))
    }

    @Test
    fun `get heroes must return not found if the hero not exists`() {
        val heroId = 1L
        val exception = NotFoundException("")

        `when`(heroApplicationServiceMock.getHero(heroId)).thenThrow(exception)

        mockMvc.perform(get("/heroes/$heroId"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `put heroes must update existent hero`() {
        val heroId = 1L
        val name = "hero name"
        val skills = setOf(Skill(null, "hero skill", 1))
        val hero = Hero(heroId, name, skills)

        `when`(heroApplicationServiceMock.updateHero(heroId, hero.name, hero.skills)).thenReturn(hero)

        val heroContent = objectMapper.writeValueAsString(hero)
        mockMvc.perform(put("/heroes/$heroId")
            .content(heroContent)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name", notNullValue()))
            .andExpect(jsonPath("$.skills", notNullValue()))
            .andExpect(jsonPath("$.skills.length()", equalTo(skills.size)))
    }

    @Test
    fun `put heroes must return not found if the hero not exists`() {
        val heroId = 1L
        val name = "hero name"
        val skills = setOf(Skill(null, "hero skill", 1))
        val hero = Hero(heroId, name, skills)
        val exception = NotFoundException("")

        `when`(heroApplicationServiceMock.updateHero(heroId, hero.name, hero.skills)).thenThrow(exception)

        val heroContent = objectMapper.writeValueAsString(hero)
        mockMvc.perform(put("/heroes/$heroId")
            .content(heroContent)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound)
    }
}