import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    application
}

val ver = "1.0-SNAPSHOT"

group = "com.matzhilven.staffutilities"
version = ver

repositories {
    mavenCentral()
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-bukkit:3.0.1")
}

bukkit {
    name = "StaffUtilities"
    version = ver
    main = "com.matzhilven.staffutilities.StaffUtilities"
    author = "MatzHilven"
    libraries = listOf(
        "org.jetbrains.kotlin:kotlin-stdlib:1.7.20",
    )
    bukkit.version = version
    
    commands {
//        register("forceoccurrence") {
//            description = "Force an occurrence to happen"
//            permission = "randomoccurrences.forceoccurrence"
//            usage = "/forceoccurrence <occurrence>"
//            aliases = listOf("fo")
//        }
    }
    
    permissions {
//        register("randomoccurrences.*") {
//            children = listOf("randomoccurrences.forceoccurrence", "randomoccurrences.reloadmessages")
//            default = BukkitPluginDescription.Permission.Default.OP
//        }
    }
    
    softDepend = listOf("PlaceholderAPI")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("")
    destinationDirectory.set(File("server\\plugins"))
}
