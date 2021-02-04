/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.example.foo

import org.jetbrains.kotlin.build.benchmarks.*
import org.jetbrains.kotlin.build.benchmarks.dsl.*
import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw RuntimeException("please specify benchmark project absolute path")
    }
    mainImpl(kotlinBenchmarks(), args.first())
}

fun kotlinBenchmarks() =
    suite {
        val currentProjectPath = File("").absolutePath
        val changeFilesPath = "/src/main/resources/change-files"

        val leafFile = changeableFile("$currentProjectPath$changeFilesPath/leaf_06_353_2/ClassAAAKt")
        val middleFile = changeableFile("$currentProjectPath$changeFilesPath/middle_06_007_7/ClassAAAKt")
        val topFile = changeableFile("$currentProjectPath$changeFilesPath/top_01_21/ClassAAAKt")

        defaultJdk = System.getenv("JAVA_HOME")

        val defaultArguments = arrayOf(
            "--info",
            "--no-build-cache",
            "--watch-fs",
        )

        val parallelBuild = arrayOf(
            *defaultArguments,
            "--parallel",
        )

        val nonParallelBuild = arrayOf(
            *defaultArguments,
            "--no-parallel",
        )

        defaultArguments(*defaultArguments)

        scenario("top incremental compilation with non abi change") {
            arguments(*parallelBuild)
            step {
                runTasks(Tasks.ANDROID_COMPILE)
            }
            step {
                changeFile(topFile, TypeOfChange.ADD_PRIVATE_FUNCTION)
                runTasks(Tasks.ANDROID_COMPILE)
            }
        }

        scenario("top incremental compilation with abi change") {
            arguments(*parallelBuild)
            step {
                runTasks(Tasks.ANDROID_COMPILE)
            }
            step {
                changeFile(topFile, TypeOfChange.ADD_PUBLIC_FUNCTION)
                runTasks(Tasks.ANDROID_COMPILE)
            }
        }


        scenario("middle incremental compilation with non abi change") {
            arguments(*parallelBuild)
            step {
                runTasks(Tasks.ANDROID_COMPILE)
            }
            step {
                changeFile(middleFile, TypeOfChange.ADD_PRIVATE_FUNCTION)
                runTasks(Tasks.ANDROID_COMPILE)
            }
        }

        scenario("middle incremental compilation with abi change") {
            arguments(*parallelBuild)
            step {
                runTasks(Tasks.ANDROID_COMPILE)
            }
            step {
                changeFile(middleFile, TypeOfChange.ADD_PUBLIC_FUNCTION)
                runTasks(Tasks.ANDROID_COMPILE)
            }
        }

        scenario("leaf incremental compilation with non abi change") {
            arguments(*parallelBuild)
            step {
              doNotMeasure()
              runTasks(Tasks.ANDROID_COMPILE)
            }
            step {
                changeFile(leafFile, TypeOfChange.ADD_PRIVATE_FUNCTION)
                runTasks(Tasks.ANDROID_COMPILE)
            }
        }

        scenario("leaf incremental compilation with abi change") {
            arguments(*parallelBuild)
            step {
                doNotMeasure()
                runTasks(Tasks.ANDROID_COMPILE)
            }
            step {
                changeFile(leafFile, TypeOfChange.ADD_PUBLIC_FUNCTION)
                runTasks(Tasks.ANDROID_COMPILE)
            }
        }
    }