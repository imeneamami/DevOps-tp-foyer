pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'M2_HOME'
    }

    stages {
        stage('GIT') {
            steps {
                git branch: 'sahar-ayedi',
                    url: 'https://github.com/imeneamami/DevOps-tp-foyer.git'
            }
        }
        
        stage('Compile Stage') {   // Move the compile stage before the scan
            steps {
                sh 'mvn clean compile'
            }
        }
         stage('Deploy to Nexus') {  // Add the deployment stage
            steps {
                sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.56.10:8081/repository/maven-releases/'
            }
        }
        stage('Scan') {
            steps {
                withSonarQubeEnv('sq1111') {
                    // Add sonar.java.binaries property to point to compiled classes
                    sh 'mvn sonar:sonar'
                }
            }
        }

        
    }
}
