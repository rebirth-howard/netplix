plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "netplix"

include("netplix-adapters:adapter-http")
include("netplix-adapters:adapter-persistence")

include("netplix-apps:app-api")
include("netplix-apps:app-batch")

include("netplix-commons")

include("netplix-core:core-domain")
include("netplix-core:core-port")
include("netplix-core:core-service")
include("netplix-core:core-usecase")

