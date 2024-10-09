dependencies {
    implementation(project(":netplix-core:core-usecase"))
    implementation(project(":netplix-adapters:adapter-http"))
    implementation(project(":netplix-adapters:adapter-persistence"))

    runtimeOnly(project(":netplix-core:core-service"))


//    implementation(project(":netplix-apps:app-batch"))
//
//    implementation(project(":netplix-adapters:adapter-http"))
//    implementation(project(":netplix-adapters:adapter-persistence"))
//
//    implementation(project(":netplix-core:core-domain"))
//    implementation(project(":netplix-core:core-port"))
//    implementation(project(":netplix-core:core-service"))
//    implementation(project(":netplix-core:core-usecase"))


    implementation("org.springframework.boot:spring-boot-starter-web")
}
