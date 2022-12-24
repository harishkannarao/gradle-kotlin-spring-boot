import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	id("java-test-fixtures")
	id("org.springframework.boot").apply(false)
	kotlin("jvm").apply(false)
	kotlin("plugin.spring").apply(false)
}

// variables for gradle.properties
val projectVersion: String by project
val javaVersion: String by project
val kotlinVersion: String by project
val coroutinesVersion: String by project
val reactorKotlinExtensionVersion: String by project
val reactorTestVersion: String by project
val springBootVersion: String by project
val jacksonVersion: String by project
val restAssuredVersion: String by project
val restAssuredCurlLoggerVersion: String by project

group = "com.harishkannarao.springboot.kotlin"
version = ""
java.sourceCompatibility = JavaVersion.toVersion(javaVersion)

allprojects {

	apply(plugin= "java")
	apply(plugin= "java-test-fixtures")
	apply(plugin= "org.jetbrains.kotlin.jvm")

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
		implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
		implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
		implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${coroutinesVersion}")
		implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:${reactorKotlinExtensionVersion}")
		testFixturesImplementation("io.rest-assured:rest-assured:$restAssuredVersion") {
			exclude(group = "com.sun.xml.bind", module = "jaxb-osgi")
		}
		testFixturesImplementation("com.github.dzieciou.testing:curl-logger:$restAssuredCurlLoggerVersion") {
			exclude(module = "slf4j-api")
		}
	}

	testing {
		suites {
			val test by getting(JvmTestSuite::class) {
				dependencies {
					implementation(project())
					implementation(testFixtures(project()))
					implementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
						exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
					}
					implementation("io.projectreactor:reactor-test:$reactorTestVersion")
					implementation("io.rest-assured:rest-assured:$restAssuredVersion") {
						exclude(group = "com.sun.xml.bind", module = "jaxb-osgi")
					}
					implementation("com.github.dzieciou.testing:curl-logger:$restAssuredCurlLoggerVersion") {
						exclude(module = "slf4j-api")
					}
				}
			}
			val integrationTest by registering(JvmTestSuite::class) {
				dependencies {
					implementation(project())
					implementation(testFixtures(project()))
					implementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
						exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
					}
					implementation("io.projectreactor:reactor-test:$reactorTestVersion")
					implementation("io.rest-assured:rest-assured:$restAssuredVersion") {
						exclude(group = "com.sun.xml.bind", module = "jaxb-osgi")
					}
					implementation("com.github.dzieciou.testing:curl-logger:$restAssuredCurlLoggerVersion") {
						exclude(module = "slf4j-api")
					}
				}
				targets {
					all {
						testTask.configure {
							mustRunAfter(test)
						}
					}
				}
			}
			val acceptanceTest by registering(JvmTestSuite::class) {
				dependencies {
					implementation(project())
					implementation(testFixtures(project()))
					implementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
						exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
					}
					implementation("io.projectreactor:reactor-test:$reactorTestVersion")
					implementation("io.rest-assured:rest-assured:$restAssuredVersion") {
						exclude(group = "com.sun.xml.bind", module = "jaxb-osgi")
					}
					implementation("com.github.dzieciou.testing:curl-logger:$restAssuredCurlLoggerVersion") {
						exclude(module = "slf4j-api")
					}
				}
				targets {
					all {
						testTask.configure {
							mustRunAfter(test)
						}
					}
				}
			}
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
		testLogging.events = setOf(TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.PASSED)
		val properties = System.getProperties().entries.associate { it.key.toString() to it.value }
		systemProperties(properties)
	}

	tasks.withType<Jar> {
		archiveVersion.set(projectVersion)
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = javaVersion
		}
	}

	tasks.named("check") {
		dependsOn(testing.suites.named("integrationTest"))
	}
}