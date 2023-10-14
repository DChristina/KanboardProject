pipeline {
    agent any

    tools {
        maven "Maven-3.9.5"

    }

    stages {
        stage('Test') {

            steps {
                sh "mvn clean test -Dsuite=DefaultSuite.xml"
                }
        }
    }
}