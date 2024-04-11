package ru.mystreet.errors.data

import kotlinx.atomicfu.atomic
import ru.mystreet.errors.domain.UniqueIdGenerator
import kotlin.random.Random

class AtomicUniqueIdGenerator : UniqueIdGenerator {
    private val value = atomic(Random.nextLong(Long.MIN_VALUE, 0))
    override fun next(): Long {
        return value.incrementAndGet()
    }
}