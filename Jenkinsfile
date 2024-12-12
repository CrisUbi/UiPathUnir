pipeline {
    agent any
    stages {
        stage('Get Commit Content') {
            steps {
                script {
                    // Obtener el contenido del último commit
                    def commitContent = bat(
                        script: 'git show -s --format=%b',
                        returnStdout: true
                    ).trim()

                    // Imprimir el contenido del commit
                    echo "Contenido del último commit: ${commitContent}"
                }
            }
        }
    }
}
