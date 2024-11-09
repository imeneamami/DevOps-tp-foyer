pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('GIT') {
            steps {
                git branch: 'ImenAmami',
                    url: 'https://github.com/imeneamami/DevOps-tp-foyer.git'
            }
        }

        stage('Maven Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        // Uncomment these stages if you need them
        // stage('Scan') {
        //     steps {
        //         withSonarQubeEnv('sq1') {
        //             sh 'mvn sonar:sonar'
        //         }
        //     }
        // }

        // stage('Deploy to Nexus') {
        //     steps {
        //         sh 'mvn deploy'
        //     }
        // }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t imen1amami/tp-foyer:5.0.0 .'
                }
            }
        }

        stage('Check Permissions') {
            steps {
                script {
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
                    sh 'docker-compose up -d'
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

        stage('Send Email Notification') {
            steps {
                script {
                    def buildStatus = currentBuild.result ?: 'SUCCESS'
                    def subject = "Build ${buildStatus}: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
                    def body = """
                        The build has ${buildStatus.toLowerCase()}.
                        
                        You can find the OWASP Dependency-Check report at: ${env.BUILD_URL}artifact/target/dependency-check-report.html
                    """
                    // Send the email
                    emailext(
                        to: 'amamiimen566@gmail.com',
                        subject: subject,
                        body: body,
                        attachLog: true,
                        attachmentsPattern: 'target/dependency-check-report.html'
                    )
                }
            }
        }
    }

    post {
        always {
            // Archive the Dependency-Check report if it exists
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/dependency-check-report.html'
        }
    }
}