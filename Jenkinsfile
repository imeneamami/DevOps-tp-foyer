pipeline {
    agent any

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
                sh "mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.1.12:8081/repository/maven-releases/"
            }
        }

        

        stage('Build image') {
            steps {
                // Build Docker image
                sh 'docker build -f Dockerfile -t sahar212/tp-foyer:1.0.0 .'
            }
        }

        stage('Deploy image') {
            steps {
                // Deploy Docker image
                sh '''
                    docker login -u sahar212 -p Lbm*Zgq@r73WS,.
                    docker push sahar212/tp-foyer:1.0.0
                '''
            }
        }

        stage('Docker Compose') {
            steps {
                // Start services using Docker Compose
                sh 'docker compose up -d'
            }
        }
    }
}
