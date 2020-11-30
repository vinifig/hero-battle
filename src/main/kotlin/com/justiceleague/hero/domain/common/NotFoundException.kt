package com.justiceleague.hero.domain.common

class NotFoundException(entity: String) : RuntimeException("$entity not found")