pipeline {
    agent any
    stages {
        stage('Get Commit and Repo URL') {
            steps {
                script {
                    // Obtener la URL del repositorio
                    def repoUrl = bat(
                        script: 'git config --get remote.origin.url',
                        returnStdout: true
                    ).trim()
 
                    // Obtener el mensaje del último commit
                    def commitMessage = bat(
                        script: 'git log -1 --pretty=format:"%B"',
                        returnStdout: true
                    ).trim()
 
                    // Imprimir la URL del repositorio y el mensaje del commit
                    echo "Repositorio URL: ${repoUrl}"
                    echo "Último mensaje de commit: ${commitMessage}"
                }
            }
        }
    }
}
