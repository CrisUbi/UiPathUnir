pipeline {
    agent any
    stages {
        stage('Get Commit Message') {
            steps {
                script {
                    // Obtener solo el mensaje del último commit sin palabras adicionales
                    def commitMessage = bat(
                        script: 'git log -1 --pretty=%s',
                        returnStdout: true
                    ).trim()
 
                    // Imprimir el mensaje del commit
                    echo "Último mensaje de commit: ${commitMessage}"
                }
            }
        }
    }
}
