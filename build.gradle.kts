import net.minecrell.pluginyml.bukkit.BukkitPluginDescription


plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "com.matzhilven.staffutilities"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://oss.sonatype.org/content/repositories/central")
}

val exposedVersion: String by project
dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
    
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("mysql:mysql-connector-java:8.0.33")
    
    implementation("org.bstats:bstats-bukkit:3.0.1")
}

bukkit {
    main = "com.matzhilven.staffutilities.StaffUtilities"
    name = "StaffUtilities"
    author = "MatzHilven"
    libraries = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:jdk8",
    )
    
    bukkit.version = version
    
    commands {
        register("punish") {
            description = "Opens the punishment GUI"
            permission = "staffutilities.punish"
            usage = "/punish <player>"
            aliases = listOf("pun")
        }
    }
    
    permissions {
        register("staffutilities.*") {
            description = "Allows the player to use all StaffUtilities commands"
            children = listOf(
                "staffutilities.punish"
            )
            default = BukkitPluginDescription.Permission.Default.OP
        }
        
        register("staffutilities.punish") {
            description = "Allows the player to use the /punish command"
            default = BukkitPluginDescription.Permission.Default.OP
        }
        
    }
}

tasks {
    shadowJar {
        relocate("org.bstats", "com.matzhilven.bstats")
        destinationDirectory.set(file("C:\\Users\\hilve\\Documents\\Servers\\1.8 - Wine\\plugins"))
    }
    compileJava {
        options.compilerArgs.add("-parameters")
        options.isFork = true
        options.forkOptions.executable = "javac"
    }
    compileKotlin {
        kotlinOptions.javaParameters = true
    }
}
