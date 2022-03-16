pipeline {
  environment {
        registry = 'olvmky/reimb-api'
        dockerHubCreds = 'docker_hub'
        dockerImage = ''
        deploymentFile = 'k8s/deployment.yml'
  }
  
  agent any
  stages {
//             stage('Clone sources') {
//                 steps {
//                     git url: 'https://github.com/olvmky/revature-project/projectOne'
//                 }
//             }
//             stage('SonarQube analysis') {
//                 steps {
// //                   def mvn = tool 'mvn';
// //                       withSonarQubeEnv() {
// //                         sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=project-one"
// //                       }
//                     withSonarQubeEnv('SonarQube') {
//                         sh "./gradlew sonarqube"
//                     }
//                 }
//             }
//             stage("Quality gate") {
//                 steps {
//                     waitForQualityGate abortPipeline: true
//                     withMaven {
//                                 sh 'mvn clean package sonar:sonar'
//                     }
//                 }
//             }
//     stage('Quality Gate') {
//       steps {
//         withSonarQubeEnv(credentialsId: 'b337c1d3dde15cd3f9706462f1a0697e75e8e1b2', installationName: 'sonarqube') {
//           withMaven {
//             sh 'mvn clean package sonar:sonar'
//           }
//         }
//       }
//     }
    stage('Test') {
      steps {
        withMaven {
            sh 'mvn -f pom.xml test'
        }
      }
    }
    
    stage('Build') {
          when {
             branch 'main'
          }
          steps {
            withMaven {
              sh 'mvn -f pom.xml clean install'
              sh 'mvn -f pom.xml clean package -DskipTests'
            }
          }
    }
    
    stage('Docker Build') {
           when {
               branch 'main'
           }
           steps {
               script {
                  echo "$registry:$currentBuild.number"
                  dockerImage = docker.build ("$registry", "-f Dockerfile .")
               }
           }
       }

    stage('Docker Deliver') {
      when {
        branch 'main'
      }
      steps {
        script {
          docker.withRegistry('',dockerHubCreds) {
                    dockerImage.push()
          }
        }
      }
    }

//     stage('Scan') {
//       steps {
//         withSonarQubeEnv(installationName: 'sq1') {
//           sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
//         }
//       }
//     }

    stage('Deploy to GKE') {
            when {
                branch 'main'
            }
            steps{
               sh 'sed -i "s/%TAG%/$BUILD_NUMBER/g" ./k8s/deployment.yml'
               sh 'cat ./k8s/deployment.yml'
               step([$class: 'KubernetesEngineBuilder',
                    projectId: 'active-road-343813',
                    clusterName: 'active-road-343813-gke',
                    zone: 'us-central1',
                    manifestPattern: 'k8s/',
                    credentialsId: 'active-road-343813',
                    verifyDeployments: true
               ])

               cleanWs()

//                discordSend description: "Build #$currentBuild.number",
//                     link: BUILD_URL, result: currentBuild.currentResult,
//                     title: JOB_NAME,
//                     webhookURL: "https://discord.com/api/webhooks/946097550514061343/7IRGxvAsw24cbGPIHXE15gtxCvzQQtRl3e5DEcm7arQpC6x3cVJPXXWZo7UWHKyJumuW"
            }
            }
    }
}
