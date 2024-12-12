pipeline {
    agent any
    stages {
        stage('Checkout Code') {
            steps {
                // Realiza el checkout del código desde GitHub
                checkout scm
            }
        }
        stage('Get Commit Message') {
            steps {
                // Usa el comando git para obtener el mensaje del último commit
                script {
                    def commitMessage = sh(
                        script: "git log -1 --pretty=%B",
                        returnStdout: true
                    ).trim()
                    echo "Último mensaje de commit: ${commitMessage}"
                }
            }
        }
    }
}
