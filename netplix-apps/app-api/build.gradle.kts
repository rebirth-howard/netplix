dependencies {
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-commons"))

    implementation(project(":netplix-core:core-service"))
    implementation(project(":netplix-adapters:adapter-http"))
    implementation(project(":netplix-adapters:adapter-persistence"))
    implementation(project(":netplix-adapters:adapter-redis"))

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.security:spring-security-oauth2-client")
    implementation("org.springframework.data:spring-data-commons")

    implementation("org.springframework.boot:spring-boot-starter-web")

}

val appMainClassName = "com.hw.netplix.NetplixApiApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}