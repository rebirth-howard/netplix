dependencies {
    implementation(project(":netplix-core:core-domain"))
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-commons"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-batch")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework:spring-tx")

    runtimeOnly(project(":netplix-core:core-service"))

}

val appMainClassName = "com.hw.netplix.NetplixBatchApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}
