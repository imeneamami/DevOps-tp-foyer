pipeline {
    agent any
    
    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('GIT') {
            steps {
                // Checkout the specified branch from the Git repository
                git branch: 'ImenAmami',
                    url: 'https://github.com/imeneamami/DevOps-tp-foyer.git'
            }
        }
        
        stage('Maven Compile') {
            steps {
                // Clean and compile the Maven project
                sh 'mvn clean compile'
            }
        }
       
       // stage('Scan') {
           // steps {
               // withSonarQubeEnv('sq1') {
                   // sh 'mvn sonar:sonar'
              //  }
            //}
        //}

       // stage('Deploy to Nexus') {  // Add the deployment stage
         //   steps {
         //       sh 'mvn deploy'
          //  }
       // }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    sh 'docker build -t imen1amami/tp-foyer:5.0.0 .'
                }
            }
        }

        stage('Check Permissions') {
            steps {
                script {
                    // Check permissions of the script
                    sh 'ls -l push_docker_image.sh'
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHubCredentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                        docker push imen1amami/tp-foyer:5.0.0
                    '''
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    // Start the Docker Compose file
                    sh 'docker-compose up -d'
                }
            }
        }

        stage('Deploy Application with Docker Compose') {
            steps {
                script {
                    // Start the application service
                    sh 'docker-compose up -d app-timesheet mysqldb'
                }
            }
        }

        stage('Deploy Prometheus') {
            steps {
                script {
                    // Start Prometheus
                    sh 'docker-compose up -d prometheus'
                }
            }
        }

        stage('Deploy Grafana') {
            steps {
                script {
                    // Start Grafana
                    sh 'docker-compose up -d grafana'
                }
            }
        }
    }
}