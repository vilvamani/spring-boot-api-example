println "${env}"
println "7777 *** 6666--- ${scm.branches[0].name}"

pipeline {
    agent any
    
    
  options {
    skipDefaultCheckout(true)
  }

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}
