dependencies {
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-core:core-port"))

    runtimeOnly(project(":netplix-adapters:adapter-http"))
    runtimeOnly(project(":netplix-adapters:adapter-persistence"))
    runtimeOnly(project(":netplix-adapters:adapter-redis"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.data:spring-data-commons")
}
