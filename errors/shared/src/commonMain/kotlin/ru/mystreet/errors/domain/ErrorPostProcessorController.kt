package ru.mystreet.errors.domain

class ErrorPostProcessorController(
    private val nestedProcessors: Array<ErrorPostProcessor>,
) : ErrorPostProcessor {

    override fun process(error: ErrorInfo) {
        nestedProcessors.forEach {
            it.process(error)
        }
    }

}