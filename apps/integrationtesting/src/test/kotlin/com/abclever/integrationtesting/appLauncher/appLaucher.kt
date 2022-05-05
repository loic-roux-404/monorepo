package com.abclever.integrationtesting.appLauncher

import java.io.BufferedReader
import java.io.InputStreamReader

var root: String = processOutput(
  exec("git rev-parse --show-toplevel"),
  ::bufToString
)

fun bufToString(bufferedReader: BufferedReader) = bufferedReader.lines().toList().joinToString()

fun getAndLogCmd(cmd: String): String {
  val res: String =
    String.format("cd %s && pnpm nx serve %s && cd -", root, cmd)
   println("Starting app : $res")

  return res
}

fun launch(apps: List<String>): Map<String, Process> {
  val res = apps.associateBy(
    keySelector = { it },
    valueTransform = { exec(getAndLogCmd(it)) }
  )

  res.values.forEach {
    processOutput(it, ({ unit -> unit.readLines().forEach(::println) }))
  }

  return res
}

fun restart(processes: Map<String, Process>): Map<String, Process> {
  processes.forEach() {
    it.value.destroy()
    launch(listOf(it.key))
  }

  return processes
}

fun kill(processes: Map<String, Process>) {
  processes.forEach {
    it.value.destroy()
  }
}

fun exec(cmd: String): Process = Runtime.getRuntime().exec(cmd)

internal fun <T> processOutput(p: Process, castFn: (BufferedReader) -> T): T =
  castFn(BufferedReader(InputStreamReader(p.inputStream)))
