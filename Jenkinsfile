pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            withMaven() {
              sh 'mvn test'
            }

          }
        }

        stage('CheckD') {
          steps {
            sh 'docker images'
          }
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