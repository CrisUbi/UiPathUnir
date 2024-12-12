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
                // Usa el comando git para obtener el mensaje del último commit en Windows
                script {
                    def output = bat(
                        script: "git log -1 --pretty=%B",
                        returnStdout: true
                    ).trim()
 
                    // Elimina las líneas innecesarias que incluye `bat`
                    def commitMessage = output.split("\r?\n").drop(1).join("\n").trim()
 
                    echo "Último mensaje de commit: ${commitMessage}"
                }
            }
        }
    }
}
