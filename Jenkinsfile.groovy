    pipeline {
        agent {
            label "parts-ecommerces-devl"
        }

        environment {
            dockerImageName = "${aws_account}.dkr.ecr.${aws_region}.amazonaws.com/test-api"
            def jenkins = Jenkins.getInstance()
            def jobName = "${JOB_NAME}"
            def job = jenkins.getItem(jobName)
            
            JOB_NAME = "${JOB_NAME}".toLowerCase()
        }

        stages {
            stage("Git Checkout") {
                steps {
                    script{
                        cleanWs()
                        
                        println "Job type: ${job.getClass()}"
                        
                        
            
            echo "------- ${JOB_NAME}"
                        sh 'printenv'
                        
                        echo "--------"

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
