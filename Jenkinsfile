pipeline {
    agent any

    tools {
        maven "Maven"
    }

    stages {
        stage('Test') {
            steps {
                bat "mvn clean test -Dsuite=DefaultSuite.xml"
                }
             post {
                 success { allure([
                     includeProperties: false,
                     jdk: '',
                     properties: [],
                     reportBuildPolicy: 'ALWAYS',
                     results: [[path: 'target/allure-results']]
                      ])
                 }
             }
        }
    }
}