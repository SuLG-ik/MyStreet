package ru.sulgik.core.crashlytics

interface Crashlytics {
    fun logMessage(message: String)
    fun sendHandledException(throwable: Throwable)
    fun sendFatalException(throwable: Throwable)
    fun setCustomValue(key: String, value: Any)
    fun setUserId(identifier: String)

    companion object : Crashlytics {
        internal var instance: Crashlytics? = null

        private fun getInstance(): Crashlytics {
            return instance ?: throw IllegalStateException("Crashlycs instance does not set")
        }

        override fun logMessage(message: String) {
            getInstance().logMessage(message)
        }

        override fun sendHandledException(throwable: Throwable) {
            getInstance().sendHandledException(throwable)
        }

        override fun sendFatalException(throwable: Throwable) {
            getInstance().sendFatalException(throwable)
        }

        override fun setCustomValue(key: String, value: Any) {
            getInstance().setCustomValue(key, value)
        }

        override fun setUserId(identifier: String) {
            getInstance().setUserId(identifier)
        }

    }

}