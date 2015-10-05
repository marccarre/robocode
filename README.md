# Robocode - MarcoBot

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
            - `Use classpath of module`: `robocode-marcobot`
