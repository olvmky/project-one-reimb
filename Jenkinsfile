pipeline {

  agent {
    kubernetes {
      label 'test-pod' 
      idleMinutes 5  // how long the pod will live after no jobs have run on it
      yamlFile 'build-pod.yaml'  // path to the pod definition relative to the root of our project 
      defaultContainer 'maven'  // define a default container if more than a few stages use it, will default to jnlp container
    }
  }
  stages {
    stage('Build') {
      steps {  // no container directive is needed as the maven container is the default
        sh "mvn clean install -DskipTests"   
      }
    }
    stage('Build Docker Image') {
      steps {
        container('docker') {  
          script {
            sh "docker build -t javaSRE2022/test ."
          }
        }
      }
    }
  }
}
