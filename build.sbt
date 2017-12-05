name := "scalajs-example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.6"

//enablePlugins(ScalaJSPlugin)

enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

// This is an application with a main method
//scalaJSUseMainModuleInitializer := true

mainClass := Some("example.ScalaJSExample")

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.3",
  "com.lihaoyi" %%% "scalatags" % "0.6.7"
)
