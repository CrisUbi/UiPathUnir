pipeline {
    agent any
    stages {
        stage('Get Commit Message') {
            steps {
                script {
                    // Ejecutar un comando Git para obtener el mensaje del commit
                    def commitMessage = sh(script: 'git log -1 --pretty=%%B', returnStdout: true).trim()
 
                    // Imprimir el mensaje del commit
                    echo "Commit Message: ${commitMessage}"
                }
            }
        }
    }
}
