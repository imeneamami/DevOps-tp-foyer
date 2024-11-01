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
        
        stage('Compile Stage') {
            steps {
                sh 'mvn clean compile'
            }
        }

        // Uncomment this stage if you have SonarQube configured
        //stage('Scan') {
         //  steps {
          //    withSonarQubeEnv('sq1') {
           //       sh 'mvn sonar:sonar'
           //  }
          //}
        //}

         // Uncomment this stage if you want to deploy to Nexus
         //stage('Deploy to Nexus') {
          //   steps {
          //       sh 'mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://192.168.50.2:8081/repository/maven-releases/'
          //  }
         //}
        
         stage('Building Image') {
            steps {
               script {
                    // Commande pour construire l'image Docker
                   sh 'docker build -t imen1amami/tp-foyer:5.0.0 .'
                }
            }
       }

        //stage('Push Docker Image to DockerHub') {
          //  steps {
           //     script {
                    // Connexion à DockerHub
           //         sh '''
          //          docker login -u imen1amami -p pwd
           //         docker push imen1amami/tp-foyer:5.0.0
           //         '''
            //    }
           // }
        //}

       // stage('Deploy with Docker Compose') {
          //  steps {
           //     script {
                    // Lancer le fichier docker-compose
            //        sh 'docker-compose up -d'
            //    }
           // }
       // }

        
    }
}