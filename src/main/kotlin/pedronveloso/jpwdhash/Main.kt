package pedronveloso.jpwdhash

import pedronveloso.jpwdhash.hasher.HashedPassword

class Main {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            execute()
        }


        fun execute() {

            val testURL = "https://netflix.com"
            val testPassword = "testpwd"

            val hashedPassword = HashedPassword.create(testPassword, testURL)
            val result = hashedPassword.toString()

            System.out.println("result is $result")
        }
    }


}
