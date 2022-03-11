pipeline {
  agent any
  stage('Build') {
      steps {
        withMaven() {
          sh 'mvn test'
        }
      }
  }
}
