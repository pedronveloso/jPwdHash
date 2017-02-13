package pedronveloso.jpwdhash

object Logger {

    fun logError(text: String) {
        System.err.println(text)
    }

    fun logDebug(text: String) {
        System.out.println(text)
    }
}