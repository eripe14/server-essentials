plugins {
    kotlin("jvm") version "2.1.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "com.eripe14"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.panda-lang.org/releases")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
    maven("https://repo.eternalcode.pl/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    implementation("dev.rollczi:litecommands-bukkit:3.9.7")

    // -- adventure --
    implementation("net.kyori:adventure-platform-bukkit:4.3.1")
    implementation("net.kyori:adventure-text-minimessage:4.16.0")
    implementation("net.kyori:adventure-api:4.18.0")

    // -- notifications --
    implementation("com.eternalcode:multification-bukkit:1.1.4")
    implementation("com.eternalcode:multification-okaeri:1.1.4")

    // -- configs --
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.6")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.6")
    implementation("eu.okaeri:okaeri-configs-serdes-commons:5.0.6")

    // -- gui --
    implementation("dev.triumphteam:triumph-gui:3.1.10")
}

tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_21.toString()
    targetCompatibility = JavaVersion.VERSION_21.toString()
}

tasks.runServer {
    version("1.21.4")
}

bukkit {
    main = "com.eripe14.serveressentials.ServerEssentialsPlugin"
    apiVersion = "1.13"
    prefix = "ServerEssentialsPlugin"
    name = "ServerEssentialsPlugin"
    author = "eripe14"
    version = "${project.version}"
}

kotlin {
    jvmToolchain(21)
}