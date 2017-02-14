jPwdHash is an open-source command line tool to generate domain-specific passwords based on the provided domain (URL) and a password.
It implements the method devised by group of researchers at the *Stanford Security Labs* (you can read more about it here: https://crypto.stanford.edu/PwdHash/).

# Usage

Simply type the URL for the domain you intend to generate a hashed password like this:

    $ jPwdHash --url netflix.com

You'll then be asked to **input a password** and you'll get your hashed password. You use this hashed password on the website in question to register 
and login instead of the actual password.

You can also use the shortened version of the url parameter, the above example would then look like this:

    $ jPwdHash -u netflix.com

# Security

Please consult the [official webpage](https://pedronveloso.github.io/jPwdHash/) for of **jPwdHash** for 
an explanation on **how it improves the security** of your passwords
and other important details.

# License

jPwdHash is provided under the GNU GPLv3 license, consult the [LICENSE file](LICENSE) for details about it.

# Contributors

**Main Developer**: Pedro Veloso (Twitter: [@pedronveloso](https://twitter.com/pedronveloso))

Parts of the source code were adapted from the *PwdHash for Android* project, available here: https://github.com/phw/Android-PwdHash
