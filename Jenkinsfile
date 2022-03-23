pipeline {

  agent {
    kubernetes {
    cloud 'kubernetes'
    label 'reimb-pod'
    idleMinutes 5
    yamlFile 'build-pod.yaml'
    defaultContainer 'maven'
    }
  }

  stages {
    stage('Test') {
      steps {
        sh 'mvn -f pom.xml test'
      }
    }

  stage('Build') {
            steps {
                sh 'mvn -f pom.xml clean install'
                sh 'mvn -f pom.xml clean package -DskipTests'
            }
   }

   stage('Docker Build') {
     steps {
       container('docker') {
          script {
            sh "docker build -t olvmky/reimb-api ."
          }
        }
     }
   }

   stage('Docker Deliver') {
        steps {
          container('docker') {
            script {
              sh "docker login -u olvmky -p a643fb0e-06a3-478c-8cc7-3d4e32ba3fa0"
              sh "docker push olvmky/reimb-api:latest"
            }
          }
        }
    }
      stage('Deploy to GKE') {
              steps {
                withKubeConfig([credentialsId: 'cluster-test',serverUrl: 'https://34.66.168.93',
                                caCertificate: '', clusterName:'jenkins']) {
                  sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'
                  sh 'chmod u+x ./kubectl'
                  sh 'gcloud container clusters get-credentials '
                  sh './kubectl apply -f deployment.yml'
                }

                cleanWs()

                discordSend description: "Build #$currentBuild.number",
                  link: BUILD_URL, result: currentBuild.currentResult,
                  title: JOB_NAME,
                  webhookURL: "https://discord.com/api/webhooks/946097550514061343/7IRGxvAsw24cbGPIHXE15gtxCvzQQtRl3e5DEcm7arQpC6x3cVJPXXWZo7UWHKyJumuW"
              }
      }
  }
}
