jPwdHash is an open-source command line tool to generate domain-specific passwords based on the provided domain (URL) and a password.
It implements the method devised by group of researchers at the *Stanford Security Labs* (you can read more about it here: https://crypto.stanford.edu/PwdHash/).

# Usage

Simply type the URL for the domain you intend to generate a hashed password like this:

    $ jPwdHash --url netflix.com

You'll then be asked to **input a password** and you'll get your hashed password. You use this hashed password on the website in question to register 
and login instead of the actual password.

You can also use the shortened version of the url parameter, the above example would then look like this:

    $ jPwdHash -u netflix.com

# Preliminary note

The following topics on this document where written with the intention that they could be read
and understood by regular users that do not posses an advanced technical background. One thing that drove me to 
write this tool was that it would be easily accessible and understood by regular people seeking to increase their security.
For those that want to learn beyond and wish to understand the algorithm and its origins please refer to the original [Stanford publication webpage](https://crypto.stanford.edu/PwdHash/).

# How it Works (why it is more secure)


All the websites that need you to input a password need to store that password somehow so they can 
identify you correctly in the future. Sadly most websites use outdated ways to 
store your sensitive information, making it easy to steal once their server is compromised. 
This happens more frequently them most people are aware of and happens with well known websites, just look at the report from the [2016 data breaches](https://www.identityforce.com/blog/2016-data-breaches) 
and you'll find big known companies such as Yahoo, LinkedIn and Verizon amongst others that saw their servers compromised at some point
and along with it a lot of their user's sensitive information got stolen as well. Your **password** can be a *particularly bad thing to be stolen*, because most 
people use the **same or very identical passwords everywhere**. This well known fact is then used to gain access other accounts you might have on other websites. Just in 2016 it happened to [various accounts from
the CEOs of famous tech companies such as Google, 
Twitter and Facebook](http://www.theverge.com/2016/7/9/12134754/ceo-jack-dorseys-twitter-account-hack), 
so don't feel bad if you were also using the same password for everything.

This is where **PwdHash** comes in handy! PwdHash is deterministic hashing algorithm that generates a *hash value* for each website you use.
It operates **completely offline**, and **does not store** your credentials on your computer's storage neither on its memory. 
It only requires you to provide the **URL of the website**
and a **password**, and generates a **unique value** (called a *hash value*) that you then use as 
your actual password on that website. 

This algorithm was design by [Stanford Security Labs](https://crypto.stanford.edu/PwdHash/pwdhash.pdf) security researchers 
and has been initially implemented as a [browser extension](https://www.pwdhash.com/). **jPwdHash** produces the same results since it implements the 
same algorithm, the only difference being that it is tailored for being used in the command line. 

The generated hash value varies for each domain, and since the algorithm is deterministic 
it will always give you the same result for the same password on that domain. 
It considers only the relevant domain portion of the URL, so for example *"amazon.com"* and 
*"amazon.com/login/"* will be accounted as the same.
The algorithm uses a *Pseudo Random Function* for the hashing, something that is commonly used in cryptography 
to ensure the strength of the generated results. It also works like a **one-way street**, in the sense that
it is not possible to arrive at the password if you had the generated hash value and the domain URL. 
This then means you can have different secret credentials 
for each website while having to remember only one password, the one you use for generating them.
By using the hashed value as your password instead of your usual password(s) you never hand your true password to any website.
So if those servers get hacked and data is leaked, your other online credentials will be protected.
This simple mechanism makes it easy so that you **don't need to remember** a dozens of different passwords,
and you also don't have to come up with your own complicated mechanism to fabric domain-specific passwords.

## Further advantages

### Phishing protection

A typical way some websites steal information is by creating *fake* webpages that resemble the actual website,
this is called **Phishing**. Think about you trying to login to a website called *facebouk.com* 
thinking it was the actual facebook webpage. Usually the attacker will make the website look identical, making you even more unaware of this.
Since the **generated password is domain-specific**, even a slightly different *URL* will generate a different hash,
so if by mistake you submit your credentials you won't be sending your the proper hash. Therefore by using PwdHash 
you get a preemptive way to defend against this type of scams.

**Note**: Modern browsers have phishing protection and nowadays this is less of a problem than it was some years ago. Still the browser is not failproof 
and will sometimes fail to prevent against new scams, so it is always nice to have some added protection.

### Reduced exposure

There are a lot of applications that target storing your credentials securely. Some do a better job than others,
and all of them face a lot of hard to solve problems. A lot of them rely on closed-source to maintain
their security, but they can still be reverse-engineered and they eventually have to support multiple platforms which makes
it even easier for their logic to be exploited.
Also, a lot of these applications do a very good job in one specific are such as encrypting the data that gets saved to your device,
but miss crucial security concerns such as retaining information in memory that can easily be read
(typically the operating system could/should guarantee and protect against this, but this is far from reality in any modern operating system, either by exploit or by design as a way for programmers to analyse application runtime; point being, don't trust anything that stays in memory). And a lot more issues exist that have to do with the complexity of this solutions, whereas
PwdHash simplifies the problem by avoiding it altogether. It does **not** store information on your hard drive or memory,
and yet you can access it pretty much on every platform so portability is not sacrificed :)

### Shared Credentials

If you need to have a set of websites that share the same login with various people in a simplistic way, you can handle 1 single password
and everyone can use PwdHash to get the domain-specific credentials.

## So is it foolproof?

Not quite, if you use only 1 master password and an attacker knows you're using PwdHash and he gets a hold of a couple
of your generated hashed passwords it would be possible to figure out your master password. The combination of all of these
things is relatively hard and highly unlikely for automated processes, yet if you use the same password every time be sure to pick a strong one.
Read the excerpt on `Easy extra layer` below for more on this.

### Easy extra layer

There's actually an easy way to make it much harder for an automated process to crack your password which 
is to make simple changes to your password
for each domain. 
Take this simple example to better understand, let's say that for each domain you would add the initial letter of the domain name 
to the end of the password.
So if you base password was **pass** it would go like this:

 * For *netflix.com* you would use *passn*
 * For *google.com* you would use *passg*
 * *and so on ...*

These changes would be easily noticeable if the passwords were used directly on the website, but they
are way harder to figure out when all the attacker has is the hashed version.
If an attacker would get a hold of a couple of passwords it would easily notice this simple pattern, but if he only sees
*V5sU0ZC* and *pREU0pB* it gets **exponentially harder**. The reason why this is way more secure is beyond the target
audience of this simple document, although if you're
you're interested consult the  [white-paper for this algorithm](http://crypto.stanford.edu/PwdHash/pwdhash.pdf) and
look-up how  attacks via *rainbow tables* work.


# License

jPwdHash is provided under the GNU GPLv3 license, consult the [LICENSE file](LICENSE) for details about it.

# Contributors

**Main Developer**: Pedro Veloso (Twitter: [@pedronveloso](https://twitter.com/pedronveloso))

Parts of the source code were adapted from the *PwdHash for Android* project, available here: https://github.com/phw/Android-PwdHash
