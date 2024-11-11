pipeline {
    agent any
 environment {
        // Set the email address for notifications
        RECIPIENT = 'ayedisahar3@gmail.com' // Replace with your email address
    }

    stages {
        stage('Checkout Git') {
            steps {
                // Checkout the code from the repository
                git credentialsId: 'cred-github', 
                    branch: 'sahar-ayedi', // Change branch name
                    url: 'https://github.com/imeneamami/DevOps-tp-foyer.git'
            }
        }

        stage('Compiling') {
            steps {
                // Compile the project using Maven
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube') {
            steps {
                // Run SonarQube analysis using Maven
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Lbm*Zgq@r73WS,.'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                // Deploy to Nexus repository
                sh "mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.1.57:8081/repository/maven-releases/"
            }
        }
      stage('OWASP Dependency-Check') {
            steps {
                // Run OWASP Dependency-Check during the build
                sh 'mvn org.owasp:dependency-check-maven:check'
            }
        }

        stage('Build image') {
            steps {
                // Build Docker image
                sh 'sudo docker build -f Dockerfile -t sahar212/tp-foyer:1.0.0 .'
            }
        }

        stage('Deploy image') {
            steps {
                // Deploy Docker image
                sh '''
                    sudo docker login -u sahar212 -p Lbm*Zgq@r73WS,.
                    sudo docker push sahar212/tp-foyer:1.0.0
                '''
            }
        }
        

        stage('Docker Compose') {
            steps {
                // Start services using Docker Compose
                sh 'sudo docker compose up -d'
            }
        }

        stage('Operation Phase: Start Grafana Monitoring') {
            steps {
                echo 'Starting Grafana monitoring...'
                sh 'docker start grafana'
            }
        }
         stage('Send Email Notification') {
            steps {
                script {
                    def buildStatus = currentBuild.result ?: 'SUCCESS'
                    def subject = "Build ${buildStatus}: ${env.JOB_NAME} ${env.BUILD_NUMBER}"
                    def body = """
                        The build has ${buildStatus.toLowerCase()}.
                        
                        You can find the OWASP Dependency-Check report
                    """
                    // Send the email
                    emailext(
                        to: "${RECIPIENT}",
                        subject: subject,
                        body: body,
                        attachLog: true, // Attach the build log if needed
                        attachmentsPattern: 'target/dependency-check-report.html' // Attach the report file
                    )
                }
            }
        }
    }
 post {
        always {
            // Optional: Archive the Dependency-Check report if not already archived
            archiveArtifacts allowEmptyArchive: true, artifacts: 'target/dependency-check-report.html'
        }

    }
}
