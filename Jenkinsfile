pipeline {

    agent any

    parameters {
        string(name: 'GITHUB_URL', defaultValue: 'https://github.com/Ibtron/UnirTesis', description: 'URL del repositorio de GitHub')
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Nombre de la rama a clonar')
        string(name: 'ORCHESTRATOR_URL', defaultValue: 'https://cloud.uipath.com/', description: 'URL del Orchestrator')
        string(name: 'ORCHESTRATOR_TENANT', defaultValue: 'uniruipath', description: 'Tenant del Orchestrator')
        string(name: 'UIPCLI_PATH', defaultValue: 'C:\\Users\\Administrator\\Documents\\Recursos\\uipcli.exe', description: 'Ruta del UiPath CLI')
        string(name: 'PROJECT_PATH', defaultValue: 'C:\\Users\\Administrator\\Documents\\Recursos\\UnirTes\\project.json', description: 'Ruta del proyecto UiPath')
        string(name: 'OUTPUT_PATH', defaultValue: 'C:\\Users\\Administrator\\Documents\\Recursos\\Output', description: 'Ruta para empaquetar la salida')
        string(name: 'UIPCLI_API', defaultValue: 'zl?ec*^ahAH*9)4n', description: 'application secret')
        string(name: 'UIPAPP_ID', defaultValue: '822cfea5-c1e9-4c5c-b0aa-56c6802ea52d', description: 'application id')
    }


    stages {

        stage('Clonar repositorio') {

            steps {
                bat """

                git branch: "%BRANCH_NAME%", url: "%GITHUB_URL%"
                """
            }

        }

        stage('Empaquetar proyecto UiPath') {

            steps {

                bat """

                "%UIPCLI_PATH%" package pack "%PROJECT_PATH%" -o "%OUTPUT_PATH%"

                """

            }

        }

        stage('Desplegar en Orchestrator') {

            steps {

                bat """

                "%UIPCLI_PATH%" package deploy "%OUTPUT_PATH%\\UnirTes.1.0.0.nupkg" "%ORCHESTRATOR_URL%" "%ORCHESTRATOR_TENANT%" --applicationId "%UIPAPP_ID%" --applicationSecret "%UIPCLI_API%" --applicationScope "OR.Assets OR.BackgroundTasks OR.Execution OR.Folders OR.Jobs OR.Machines.Read OR.Monitoring OR.Robots.Read OR.Settings.Read OR.TestSetExecutions OR.TestSets OR.TestSetSchedules OR.Users.Read" -o "Unir" --accountForApp "testunir"
                """

            }

        }

    }

    post {

        success {

            echo '¡Despliegue exitoso!'

        }

        failure {

            echo 'Hubo un error en el despliegue.'

        }

    }

}
