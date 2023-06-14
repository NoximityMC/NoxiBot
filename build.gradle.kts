plugins {
    id("java")
}

group = "com.noximity"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("net.dv8tion:JDA:5.0.0-beta.10")
    implementation ("me.carleslc.Simple-YAML:Simple-Yaml:1.8.4")
    testImplementation("ch.qos.logback:logback-classic:1.4.8")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.apache.commons:commons-lang3:3.12.0")
}

tasks.test {
    useJUnitPlatform()
}