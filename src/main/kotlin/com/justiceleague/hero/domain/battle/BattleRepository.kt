package com.justiceleague.hero.domain.battle

import org.springframework.data.repository.PagingAndSortingRepository

interface BattleRepository : PagingAndSortingRepository<Battle, Long>