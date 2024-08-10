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
                sh 'docker build -t=sgnsabir/selenium .'
            }
        }

        stage('Push Image'){
            environment{
                // assuming you have stored the credentials with this name
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh 'docker push sgnsabir/selenium'
            }
        }
    }

    post {
        always {
            sh 'docker logout'
        }
    }

}

// Jenkins
// curl -s0 http://localhost:jnlpJars/agent.jar
// java -jar agent.jar -url http://localhost:8080/ -secret 571e338139a31ec37e2f8cd3d72e1841cdc4d2ab2a520cea72fac8b18e579c40 -name NODE1 -workDir "/home/sabir/workspace/06-jenkins-ci-cd/01-jenkins/volumes/node"
}