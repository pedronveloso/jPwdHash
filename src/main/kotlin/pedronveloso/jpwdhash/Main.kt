package pedronveloso.jpwdhash

import joptsimple.OptionParser
import joptsimple.OptionSet
import pedronveloso.jpwdhash.Parameters.PASS
import pedronveloso.jpwdhash.Parameters.URL
import pedronveloso.jpwdhash.hasher.HashedPassword
import java.util.*


class Main {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val parser = OptionParser()
            parser.accepts(URL, "URL of the domain intended to be hashed").withRequiredArg().required()
            parser.accepts(PASS, "Password to use for the hashing process. If you use this option beware that " +
                    "your command will display your password on the console's history as well as display it on the " +
                    "screen, this should only be used for automation purposes.").withRequiredArg()
            parser.posixlyCorrect(true)

            // Display help if sufficient arguments NOT provided
            if (args.size != 4 && args.size != 2) {
                displayHelp(parser)
                return
            }

            // TODO : Add --help argument

            // User can either provide a password or use one in the command line itself (leaks to history though)
            val options = if (args.size == 2) {
                parser.parse(args.component1(), args.component2())
            } else {
                parser.parse(args.component1(), args.component2(), args.component3(), args.component4())
            }

            if (options.has(PASS)) {
                execute(options, options.valueOf(PASS) as String)
            } else {
                askPassword(options)
            }
        }

        private fun displayHelp(parser: OptionParser) {
            Logger.logDebug("jPwdHash - Command line tool for password hashing.")
            Logger.logDebug("\nOfficial webpage: https://pedronveloso.github.io/jPwdHash/")
            Logger.logDebug("\nUsage:\n")
            parser.printHelpOn(System.out)
        }

        private fun askPassword(options: OptionSet) {
            val c = System.console()
            if (c == null) {
                Logger.logError("Java Console was not found, reading with Scanner (insecure)")
                Logger.logDebug("Enter password:")
                val scanner = Scanner(System.`in`)
                val readPassword = scanner.nextLine()
                execute(options, readPassword)
                return

            }
            val readPassword = c.readPassword("Enter password: ")
            execute(options, String(readPassword))
        }

        private fun execute(options: OptionSet, password: String) {
            val url = options.valueOf(URL) as String

            val hashedPassword = HashedPassword.create(password, url)
            val result = hashedPassword.toString()

            System.out.print(result)
            System.out.print("\n")
        }
    }


}
