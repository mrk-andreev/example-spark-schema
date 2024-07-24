name := "code"

version := "0.1"

scalaVersion := "2.12.13"

lazy val javaVersion = "11"
lazy val sparkVersion = "3.1.2"
lazy val typeSafeConfigVersion = "1.4.1"
lazy val scalatestVersion = "3.2.3"

libraryDependencies += "com.typesafe" % "config" % typeSafeConfigVersion

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % Provided
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % Provided

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % Test
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion % Test
libraryDependencies += "org.scalatest" %% "scalatest" % scalatestVersion % Test
