pipeline {
    agent any
    stages {
        stage('Get Commit Message') {
            steps {
                script {
                    // Usar git log para obtener solo el mensaje del commit
                    def commitMessage = bat(
                        script: 'git log -1 --abbrev-commit --no-merges --pretty=%s',
                        returnStdout: true
                    ).trim()
 
                    // Imprimir solo el mensaje del commit
                    echo "Último mensaje de commit: ${commitMessage}"
                }
            }
        }
    }
}
