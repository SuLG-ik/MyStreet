package ru.mystreet.errors.domain

interface UniqueIdGenerator {

    fun next(): Long

}