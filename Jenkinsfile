pipeline {
    agent any
    stages {
        stage('Get Commit Message and Repo URL') {
            steps {
                script {
                    // Obtener solo el mensaje del último commit sin metadatos
                    def commitMessage = bat(
                        script: 'git log -1 --pretty=%B',
                        returnStdout: true
                    ).trim()
 
                    // Imprimir el mensaje del commit
                    echo "Último mensaje de commit: ${commitMessage}"

                    // Obtener la URL del repositorio
                    def repoUrl = bat(
                        script: 'git config --get remote.origin.url',
                        returnStdout: true
                    ).trim()

                    // Imprimir la URL del repositorio
                    echo "URL del repositorio: ${repoUrl}"
                }
            }
        }
    }
}
