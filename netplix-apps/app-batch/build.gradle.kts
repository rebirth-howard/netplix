dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")

}

val appMainClassName = "com.hw.netplix.NetplixBatchApplication"
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set(appMainClassName)
    archiveClassifier.set("boot")
}
