import org.sourcegrade.jagr.gradle.extension.JagrExtension

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.algomate)
}

exercise {
    assignmentId.set("h04")
}

submission {
    // ACHTUNG!
    // Setzen Sie im folgenden Bereich Ihre TU-ID (NICHT Ihre Matrikelnummer!), Ihren Nachnamen und Ihren Vornamen
    // in Anführungszeichen (z.B. "ab12cdef" für Ihre TU-ID) ein!
    studentId = null
    firstName = null
    lastName = null

    // Optionally require own tests for mainBuildSubmission task. Default is false
    requireTests = false
    // Optionally require public grader for mainBuildSubmission task. Default is false
    requireGraderPublic = false
}

dependencies {
    // libs.fopbot method generated from ./gradle/libs.versions.toml
    implementation(libs.fopbot)
}

extensions.getByType<JagrExtension>().apply {
    graders["graderPublic"].configureDependencies {
        implementation("org.tudalgo:algoutils-tutor:0.5.0")
    }
}
