package com.justiceleague.hero

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HeroApplication

fun main(args: Array<String>) {
	runApplication<HeroApplication>(*args)
}
