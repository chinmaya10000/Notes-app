
pipeline {
    agent any
    
    stages {
        stage('Build and Push') {
            steps {
                script {
                    echo "building the docker image.."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                         sh 'docker build -t chinmayapradhan/reddit-app:1.0 .'
                         sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push chinmayapradhan/reddit-app:1.0'
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    echo "Deploy docker image to EC2.."

                    def shellCmd = "bash ./server-cmds.sh"
                    sshagent(['ec2-server-key']) {
                        sh 'scp docker-compose.yml ec2-user@3.19.141.157:/home/ec2-user'
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.19.141.157 '${shellCmd}'"
                    }
                }
            }
        }
    }
}
