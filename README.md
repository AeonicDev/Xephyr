Xephyr
==
Xephyr is a plugin engine for Bukkit and Spigot servers, designed for ease of use and speedy development of custom plugins.

It contains many utilities usually only found in proprietary plugins for large servers, including "special" items (as found on a vareity of minigame servers and larger networks), inventory-based menus (shops, warps, etc.), and an extremely extensive command system.

Features
==
Xephyr separates the most plugin actions into modules. These modules can interact with and even depend on eachother. The simplest modules are classes that extend Xephyr's module class and implement the Bukkit Listener interface to handle events. Note that Xephyr will automatically register a module as a listener if it finds it to be an instance of one.

Building
==
Install [Maven](http://maven.apache.org).

Clone the repository.

Run `mvn clean package` or `mvn clean install` (if you want it in your local repo).

Check the `target` directory.

Getting Started
==
[Basic Tutorial](https://github.com/DHLAB-Development/Xephyr/wiki/Writing-plugins-with-Xephyr)

Most general information can be found in the javadocs. As this project is still new, ambiguous/missing docs should be reported in the issue tracker.

Contributing
==
Xephyr is a project in its infancy. It was born out of a distaste for integrating many different small components from different authors to achieve the effects desired. We'd appreciate any help or contributions offered, our goal is to make this framework/library the best it can be.

Our only guidelines are that you thoroughly test your code before submitting a pull request and that you keep the general style consistent with the rest of the source code.

We will be very selective about what is added to Xephyr and what isn't; the project is not meant to be a massive library of random, specific utilities. Our goal is to add solutions to implementing more generic features commonly desired in custom plugins, no matter their size.

Maven Dependency/Repository Information
==
The repository where releases are published.

	<repository>
		<id>hauno-repo</id>
		<url>http://repo.hauno.me/</url>
	</repository>
	
and the dependency info.

	<dependency>
		<groupId>com.dhlab.xephyr</groupId>
		<artifactId>Xephyr</artifactId>
		<version>1.0</version>
		<scope>provided</scope>
	</dependency>
