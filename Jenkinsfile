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
        
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Scan') {
            steps {
                withSonarQubeEnv('sq1111') {
                    sh 'mvn sonar:sonar -Dsonar.java.binaries=target/classes'
                }
            }
        }
        
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.56.10:8081/repository/maven-releases/'
            }
        }
    }
}
