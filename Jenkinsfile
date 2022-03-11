pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        withMaven() {
          sh 'mvn test'
        }

      }
    }

    stage('CheckingDocker') {
      steps {
        sh 'docker images'
      }
    }

  }
}