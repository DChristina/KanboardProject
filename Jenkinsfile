pipeline {
    agent any

    tools {
        maven "Maven"
        jdk "JDK"
    }

    stages {
        stage('Test') {
            steps {
                sh "mvn clean test -Dsuite=DefaultSuite.xml"
                }

        }
    }
}