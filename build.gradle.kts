import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.*

plugins {
	id("org.springframework.boot") version "3.2.6"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	kotlin("plugin.jpa") version "1.9.24"
	id("org.liquibase.gradle") version "2.2.1"
}

group = "nl.moukafih"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.liquibase:liquibase-core")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// liquibase
	liquibaseRuntime("org.liquibase:liquibase-core")
	liquibaseRuntime("info.picocli:picocli:4.7.6")
	liquibaseRuntime("org.postgresql:postgresql")
	liquibaseRuntime("org.liquibase.ext:liquibase-hibernate6:4.28.0")
	liquibaseRuntime("org.springframework.boot:spring-boot-starter-data-jpa")
	liquibaseRuntime(sourceSets.getByName("main").output)
	liquibaseRuntime(sourceSets.getByName("main").runtimeClasspath)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

liquibase {
	activities.register("main") {
		arguments = mapOf(
			"changeLogFile" to "src/main/resources/db/changelog/changelog-master.yml",
		)
	}
}

tasks.register("myDiffChangelog") {
	liquibase {
		activities {
			named("main").configure {
				arguments = mapOf(
					"changeLogFile" to "src/main/resources/db/changelog/changes/${
						SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(
							Date()
						)}_changelog.sql",
				)
			}
		}
	}
}

tasks["liquibaseDiff"].dependsOn("compileJava")
tasks["liquibaseDiffChangelog"].dependsOn("myDiffChangelog")