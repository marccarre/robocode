# Robocode

## Setup

0. [Optional] Build Robocode `1.8.3.0` from sources:

    - Extract `robocode-1.8.3.0-src.zip`
    - Open the resulting directory (`robocode-1.8.3.0`)
    - Run `./mvn.sh clean install -Dmaven.javadoc.skip=true -DskipTests`. 
    Note that you will need Internet connectivity, as the installer downloads dependencies from various websites.
    If everything went well, you should see a message indicating that the build was successful.
    - Open the directory `robocode-1.8.3.0/robocode.distribution/target`
    - You should see a file named `robocode-1.8.3.0-setup.jar` which can then be used for the installation of Robocode `1.8.3.0`, instead of the provided file in the next step.

1. Install Robocode `1.8.3.0`:

    - Run `java -jar ./setup/robocode-1.8.3.0-setup.jar` and follow the installation steps.
    - Ensure you keep a note of where Robocode was installed.

2. Run from IDE:

    - Eclipse: follow the steps described on [this page](http://robowiki.net/wiki/Robocode/Running_from_Eclipse)
    - IntelliJ:

        - Go to `Run` > `Edit Configurations...` > Click `+` (`Add New Configuration`) > Select `Application`
        - Configure with the below parameters:

            - `Name`: `Robocode`
            - `Main class`: `robocode.Robocode`
            - `VM options`: `-Xmx512M -Dsun.io.useCanonCaches=false -Ddebug=true`
            - `Working directory`: `/path/to/robocode` (from previous step)
            - `Use classpath of module`: `robocode`

    - In Robocode, go to `Preferences` > `Development Options`

         - `Add` > `/path/to/project/robocode/target/classes`
         - `Add` > `/path/to/robocode/robots`

## Resources

- [http://robocode.sourceforge.net/docs/robocode/](http://robocode.sourceforge.net/docs/robocode/)
- [http://mark.random-article.com/weber/java/robocode/lesson2.html](http://mark.random-article.com/weber/java/robocode/lesson2.html)
- [http://mark.random-article.com/weber/java/robocode/lesson3.html](http://mark.random-article.com/weber/java/robocode/lesson3.html)
- [http://mark.random-article.com/weber/java/robocode/lesson4.html](http://mark.random-article.com/weber/java/robocode/lesson4.html)
- [http://mark.random-article.com/weber/java/robocode/lesson5.html](http://mark.random-article.com/weber/java/robocode/lesson5.html)
- [http://robowiki.net/wiki/Talk:Wall_Avoidance](http://robowiki.net/wiki/Talk:Wall_Avoidance)
- [http://robowiki.net/wiki/Wall_Smoothing](http://robowiki.net/wiki/Wall_Smoothing)
- [http://robowiki.net/wiki/Wall_Smoothing/Implementations](http://robowiki.net/wiki/Wall_Smoothing/Implementations)
- [http://www.ibm.com/developerworks/library/j-robotips/index.html](http://www.ibm.com/developerworks/library/j-robotips/index.html)
  - [Anti-gravity movement](http://www.ibm.com/developerworks/library/j-antigrav/index.html)
  - [Predictive targeting](http://www.ibm.com/developerworks/library/j-pred-targeting/index.html)
  - [Tracking your opponents' movement](http://www.ibm.com/developerworks/java/library/j-movement/index.html)
  - [Circular targeting](http://www.ibm.com/developerworks/library/j-circular/index.html)
  - [Dodge bullets](http://www.ibm.com/developerworks/library/j-dodge/index.html)
  - [Tracking bullets](http://www.ibm.com/developerworks/library/j-tipbullet.html)
  - [Radar sweeps](http://www.ibm.com/developerworks/library/j-radar/index.html)
  - [Polymorphic enemy cache](http://www.ibm.com/developerworks/library/j-tippoly/)
  - [Robocode strategies](http://www.ibm.com/developerworks/library/j-tipstrats/index.html)
