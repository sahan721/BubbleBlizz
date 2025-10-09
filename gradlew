#!/usr/bin/env sh
APP_HOME=`dirname "$0"`
JAVA_EXE=java
exec "$JAVA_EXE" -cp "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
