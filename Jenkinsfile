pipeline {
    agent any
    environment { 
        REPO_URL = '' 
        COMMIT_MESSAGE = '' 
        BRANCH_NAME = ''
    }
    stages {
        stage('Get Commit and Repo URL') {
            steps {
                script {
                    // Obtener la URL del repositorio
                    REPO_URL = powershell(
                        script: 'git config --get remote.origin.url',
                        returnStdout: true
                    ).trim()

                    // Obtener el mensaje del último commit
                    COMMIT_MESSAGE = powershell(
                        script: 'git log -1 --pretty=format:"%B"',
                        returnStdout: true
                    ).trim()
                    def commitHash = powershell( script: 'git rev-parse HEAD', returnStdout: true ).trim() 
                    // Obtener la rama que contiene el commit 
                    BRANCH_NAME = powershell( script: "git branch -r --contains ${commitHash}", returnStdout: true ).trim()
                }
            }
        }
        stage('Print Variables') { 
            steps { 
                echo "Repositorio URL: ${REPO_URL}" 
                echo "Último mensaje de commit: ${COMMIT_MESSAGE}" 
                echo "Nombre de la rama actual: ${BRANCH_NAME}" 
            } 
        }
    }
}
