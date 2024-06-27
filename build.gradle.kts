plugins {
    id("java")
}

group = "com.pd.iftaas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.github.ralfspoeth", "json", "1.1.1")
}

tasks.test {
    useJUnitPlatform()
}