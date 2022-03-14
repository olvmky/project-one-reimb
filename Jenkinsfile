pipeline {
  agent any
  stages {
    stages('Test') {
      steps {
        withMaven {
            sh 'mvn test'
        }
      }
    }
  }
}