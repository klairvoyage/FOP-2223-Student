@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.algomate)
}

exercise {
    assignmentId.set("h11")
}

submission {
    // ACHTUNG!
    // Setzen Sie im folgenden Bereich Ihre TU-ID (NICHT Ihre Matrikelnummer!), Ihren Nachnamen und Ihren Vornamen
    // in Anführungszeichen (z.B. "ab12cdef" für Ihre TU-ID) ein!
    studentId = "klairvoyage"
    firstName = "klairvoyage"
    lastName = "klairvoyage"

    // Optionally require own tests for mainBuildSubmission task. Default is false
    requireTests = false
    // Optionally require public grader for mainBuildSubmission task. Default is false
    requireGraderPublic = false
}

dependencies {
    implementation("org.junit-pioneer:junit-pioneer:1.7.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("org.tudalgo:algoutils-tutor:0.6.3")
    implementation("org.tudalgo:algoutils-student:0.6.3")
}

jagr {
    graders {
        val graderPublic by getting {
            configureDependencies {
            }
        }
    }
}
