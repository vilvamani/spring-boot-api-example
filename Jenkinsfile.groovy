    pipeline {
        agent {
            label "parts-ecommerces-devl"
        }

        environment {
            dockerImageName = "${aws_account}.dkr.ecr.${aws_region}.amazonaws.com/test-api"
            JOB_NAME = "${JOB_NAME}".toLowerCase()
        }

        stages {
            stage("Git Checkout") {
                steps {
                    script{
                        cleanWs()
                        
                        
            
            echo "------- ${JOB_NAME}"

                        // Cloning the Git Repo
                        checkout scm

                        // Reading first 8 characters from the GIT Commit ID
                        GIT_COMMIT_REV = sh(returnStdout: true, script: "git rev-parse --short HEAD").trim()
                    }
                }
            }

            stage("Build & Run Unit Tests") {
                steps {
                    script {
                        sh "docker-compose -f docker-compose-ci.yml run test-api clean test --info"
                    }
                }
            }
        }
    }
