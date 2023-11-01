pipeline {
    agent any
    
    stages {
        stage('Clone repo') {
            steps {
                script {
                    echo "Clone git repo.."
                    git branch: 'main', url: 'https://github.com/chinmaya10000/Notes-app.git'
                }
            }
        }
        stage('Build and Push') {
            steps {
                script {
                    echo "Build and Push Docker image.."
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
                    echo "Deploy image to EC2.."
                    def dockerCmd = "docker run -d -p 8000:8000 chinmayapradhan/reddit-app:1.0"
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.19.141.157 '${dockerCmd}'"
                    }
                }
            }
        }
    }
}
