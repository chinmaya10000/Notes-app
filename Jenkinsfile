def gv

pipeline {
    agent any
    
    stages {
        stage('init') {
            steps {
                script {
                    gv = load "script.groovy"
            }
        }
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
                    gv.buildImage()
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}
