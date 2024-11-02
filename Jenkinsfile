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
                script {
                    // Set executable permission and run the shell script
                    sh 'chmod +x push_docker_image.sh'
                    sh './push_docker_image.sh'
                }
            }
        }

        // Uncomment this stage if you want to deploy with Docker Compose
         stage('Deploy with Docker Compose') {
             steps {
                script {
        //             // Start the Docker Compose file
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}