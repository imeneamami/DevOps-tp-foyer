pipeline {
    agent any

    stages {
        stage('Checkout Git') {
            steps {
                // Checkout the code from the new repository
                git credentialsId: 'cred-github', 
                    branch: 'sahar-ayedi', // Change branch name
                    url: 'https://github.com/imeneamami/DevOps-tp-foyer.git' // New repository
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
    }
   

}
