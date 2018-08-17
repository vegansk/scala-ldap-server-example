import sbt._
import sbt.Keys._

object ScalaLdapServerExample {

  object Config {

    type PC = Project => Project

    // List and comments from here:
    // http://tpolecat.github.io/2017/04/25/scalac-flags.html
    private val commonScalacOptions: List[String] = List(
      "-deprecation",                      // Emit warning and location for usages of deprecated APIs.
      "-encoding", "utf-8",                // Specify character encoding used by source files.
      "-explaintypes",                     // Explain type errors in more detail.
      "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
      "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
      "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
      "-language:higherKinds",             // Allow higher-kinded types
      "-language:implicitConversions",     // Allow definition of implicit functions called views
      "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
      "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
      "-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
      "-Xfuture",                          // Turn on future language features.
      "-Yno-adapted-args",                 // Do not adapt an argument list (either by inserting () or creating a tuple) to match the receiver.
      "-Ypartial-unification",             // Enable partial unification in type constructor inference
      "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
      "-Ywarn-inaccessible",               // Warn about inaccessible types in method signatures.
      "-Ywarn-infer-any",                  // Warn when a type argument is inferred to be `Any`.
      "-Ywarn-nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
      "-Ywarn-nullary-unit",               // Warn when nullary methods return Unit.
      "-Ywarn-numeric-widen",              // Warn when numerics are widened.
      "-Ywarn-unused:implicits",           // Warn if an implicit parameter is unused.
      "-Ywarn-unused:imports",             // Warn if an import selector is not referenced.
      "-Ywarn-unused:locals",              // Warn if a local definition is unused.
      "-Ywarn-unused:params",              // Warn if a value parameter is unused.
      "-Ywarn-unused:patvars",             // Warn if a variable bound in a pattern is unused.
      "-Ywarn-unused:privates",            // Warn if a private member is unused.
      "-Ywarn-value-discard",              // Warn when non-Unit expression results are unused.

      // This is a pain with -Xfatal-warnings when using ??? stubs
      //"-Ywarn-dead-code",                  // Warn when dead code is identified.

      // tpolecat enables only some linting checks, we use all of them.
      "-Xlint",

      // Specific to Typelevel Scala
      // https://github.com/typelevel/scala/issues/158
      // "-Yinduction-heuristics",       // speeds up the compilation of inductive implicit resolution
      // https://github.com/typelevel/scala/issues/167
      // "-Ykind-polymorphism",          // type and method definitions with type parameters of arbitrary kinds
      "-Yliteral-types",              // literals can appear in type position
      "-Xstrict-patmat-analysis"      // more accurate reporting of failures of match exhaustivity
    )

    val base: PC = _.settings(
      scalaVersion := Dependencies.Versions.scala,
      organization := "com.github.vegansk",
      scalacOptions ++= commonScalacOptions
    )

    val root: PC = _.configure(base)

  }

  lazy val root = project.in(file("."))
    .settings(name := "scala-ldap-server-example")
    .configure(Config.root)

}
