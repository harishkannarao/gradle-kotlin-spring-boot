import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	id("org.springframework.boot").apply(false)
	kotlin("jvm").apply(false)
	kotlin("plugin.spring").apply(false)
}

// variables for gradle.properties
val javaVersion: String by project
val kotlinVersion: String by project
val springBootVersion: String by project
val jacksonVersion: String by project
val restAssuredVersion: String by project
val restAssuredCurlLoggerVersion: String by project

group = "com.harishkannarao.springboot.kotlin"
version = ""
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)

allprojects {

	apply(plugin= "java")
	apply(plugin= "org.jetbrains.kotlin.jvm")
	apply(plugin= "org.jetbrains.kotlin.plugin.spring")

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
		implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
		testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
			exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		}
		testImplementation("io.rest-assured:rest-assured:$restAssuredVersion") {
			exclude(group = "com.sun.xml.bind", module = "jaxb-osgi")
		}
		testImplementation("com.github.dzieciou.testing:curl-logger:$restAssuredCurlLoggerVersion") {
			exclude(module = "slf4j-api")
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
		val properties = System.getProperties().entries.map { it.key.toString() to it.value }.toMap()
		systemProperties(properties)
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = javaVersion
		}
	}

	sourceSets {
		create("intTest") {
			compileClasspath += sourceSets.main.get().output
			compileClasspath += sourceSets.test.get().output
			runtimeClasspath += sourceSets.main.get().output
			runtimeClasspath += sourceSets.test.get().output
		}
	}

	val intTestImplementation: Configuration by configurations.getting {
		extendsFrom(
				configurations.implementation.get(),
				configurations.testImplementation.get()
		)
	}

	val intTestRuntimeOnly: Configuration by configurations.getting {
		extendsFrom(
				configurations.runtimeOnly.get(),
				configurations.testRuntimeOnly.get()
		)
	}

	val integrationTest = task<Test>("integrationTest") {
		description = "Runs integration tests."
		group = "verification"

		testClassesDirs = sourceSets["intTest"].output.classesDirs
		classpath = sourceSets["intTest"].runtimeClasspath
		shouldRunAfter("test")
	}

	tasks.check { dependsOn(integrationTest) }

	sourceSets {
		create("accTest") {
			compileClasspath += sourceSets.main.get().output
			compileClasspath += sourceSets.test.get().output
			runtimeClasspath += sourceSets.main.get().output
			runtimeClasspath += sourceSets.test.get().output
		}
	}

	val accTestImplementation: Configuration by configurations.getting {
		extendsFrom(
				configurations.implementation.get(),
				configurations.testImplementation.get()
		)
	}

	val accTestRuntimeOnly: Configuration by configurations.getting {
		extendsFrom(
				configurations.runtimeOnly.get(),
				configurations.testRuntimeOnly.get()
		)
	}

	val acceptanceTest = task<Test>("acceptanceTest") {
		description = "Runs acceptance tests."
		group = "verification"

		testClassesDirs = sourceSets["accTest"].output.classesDirs
		classpath = sourceSets["accTest"].runtimeClasspath
		shouldRunAfter("test")
	}
}