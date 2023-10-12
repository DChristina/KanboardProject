pipeline {
    agent any

    tools {
        maven "Maven"
        jdk "JDK"
    }

    stages {
        stage('Test') {
             steps{
                 sh "unset JAVA_HOME && mvn clean install"
             }
            steps {
                sh "mvn clean test -Dsuite=DefaultSuite.xml"
                }
        }
    }
}