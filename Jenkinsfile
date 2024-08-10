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
                sh 'sudo docker build -t=sgnsabir/selenium .'
            }
        }

        stage('Push Image'){
            environment{
                // assuming you have stored the credentials with this name
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'sudo echo ${DOCKER_HUB_PSW} | sudo docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'sudo docker push sgnsabir/selenium'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }
}