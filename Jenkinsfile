pipeline{

    agent any

    stages{
        stage('Build Jar'){
            steps{
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image'){
            steps{
                sh 'docker build -t=sgnsabir/selenium:latest .'
            }
        }

        stage('Push Image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push sgnsabir/selenium:latest'
                sh "docker tag sgnsabir/selenium:latest sgnsabir/selenium:${env.BUILD_NUMBER}"
                sh "docker push sgnsabir/selenium:${env.BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}