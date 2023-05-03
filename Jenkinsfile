pipeline {
    agent any 
    stages {
        stage("build") {
            steps {
                script {
                    echo "building the application"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    echo "testing the application"
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    def dockerComposeCmd = "docker-compose down && docker-compose up -d"
                    sshagent(['ec2-server-key']) {
                        sh "scp docker-compose.yml ec2-user@3.137.166.249:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.137.166.249 ${dockerComposeCmd}"
                    }
                }
            }
        }
    }
}
