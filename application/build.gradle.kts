import org.springframework.boot.gradle.tasks.run.BootRun

apply(plugin= "org.springframework.boot")
apply(plugin= "org.jetbrains.kotlin.plugin.spring")

tasks.getByName<BootRun>("bootRun") {
    systemProperty("reactor.netty.http.server.accessLogEnabled","true")
}