import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    application
}

group = "com.matzhilven.staffutilities"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
    implementation("org.bstats:bstats-bukkit:3.0.1")
}

bukkit {
    name = "StaffUtilities"
    main = "com.matzhilven.staffutilities.StaffUtilities"
    author = "MatzHilven"
    libraries = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:1.7.20",
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

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    relocate("org.bstats", "com.matzhilven.bstats")
    archiveClassifier.set("")
    destinationDirectory.set(File("server\\plugins"))
}
