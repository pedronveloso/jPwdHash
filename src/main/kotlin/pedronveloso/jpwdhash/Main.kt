package pedronveloso.jpwdhash

import joptsimple.OptionParser
import joptsimple.OptionSet
import pedronveloso.jpwdhash.Parameters.PASS
import pedronveloso.jpwdhash.Parameters.URL
import pedronveloso.jpwdhash.hasher.HashedPassword

class Main {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val parser = OptionParser()
            parser.accepts(URL, "URL of the domain intended to be hashed").withRequiredArg().required()
            parser.accepts(PASS, "Password to use for the hashing process").withRequiredArg().required()
            parser.posixlyCorrect(true)

            // Display help if sufficient arguments NOT provided
            if (args.size != 4) {
                displayHelp(parser)
                return
            }

            val options = parser.parse(args.component1(), args.component2(), args.component3(), args.component4())

            execute(options)
        }

        private fun displayHelp(parser: OptionParser) {
            parser.printHelpOn(System.out)
        }

        private fun execute(options: OptionSet) {
            val url = options.valueOf(URL) as String
            val password = options.valueOf(PASS) as String

            val hashedPassword = HashedPassword.create(password, url)
            val result = hashedPassword.toString()

            System.out.print(result)
            System.out.print("\n")
        }
    }


}
