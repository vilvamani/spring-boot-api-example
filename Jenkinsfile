println "${env}"
println "--- ${scm.branches[0].name}"

pipeline {
    agent any
    
    
  options {
    skipDefaultCheckout(true)
  }

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Code Checkout') {
      steps {
        script {
          checkout scm
            sh "git branch"
        }
      }
    }
        
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
