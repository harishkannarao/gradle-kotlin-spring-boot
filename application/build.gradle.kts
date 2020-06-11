import org.springframework.boot.gradle.tasks.bundling.BootJar

apply(plugin= "org.springframework.boot")

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}