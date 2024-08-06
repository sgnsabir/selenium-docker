pipeline{

    agent any

    stages{

        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }

        stage('Build Image'){
            steps{
                sh "sudo docker build -t=sgnsabir/selenium ."
            }
        }

        stage('Push Image'){
            steps{
                sh "sudo docker push sgnsabir/selenium"
                echo "https://github.com/sgnsabir/selenium-docker.git"
            }
        }
    }

    post {
        always {
            echo "doing clean up"
        }
    }

// sudo docker-compose up
// curl -s0 http://localhost:jnlpJars/agent.jar
// java -jar agent.jar -url http://localhost:8080/ -secret 571e338139a31ec37e2f8cd3d72e1841cdc4d2ab2a520cea72fac8b18e579c40 -name NODE1 -workDir "/home/sabir/workspace/06-jenkins-ci-cd/01-jenkins/volumes/node"
}