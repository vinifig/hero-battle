package com.justiceleague.hero.domain.hero

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HeroRepository : CrudRepository<Hero, Long>