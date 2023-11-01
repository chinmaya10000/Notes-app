def buildImage() {
    echo "building the docker image.."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t chinmayapradhan/reddit-app:1.0 .'
        sh "echo $PASS | docker login -u $USER --passowrd-stdin"
        sh 'docker push chinmayapradhan/reddit-app:1.0'
    }
}
def deployApp() {
    echo "Deploy docker image to EC2.."

    def dockerCmd = "docker run -d -p 8000:8000 chinmayapradhan/reddit-app:1.0"
    sshagent(['ec2-server-key']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.19.141.157 '${dockerCmd}'"
    }
}
