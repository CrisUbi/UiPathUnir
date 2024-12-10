pipeline {
 
    agent any
 
    parameters {
        string(name: 'GITHUB_URL', defaultValue: 'https://github.com/Ibtron/UnirTesis', description: 'URL del repositorio de GitHub')
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Nombre de la rama a clonar')
        string(name: 'ORCHESTRATOR_URL', defaultValue: 'https://cloud.uipath.com/testunir/uniruipath', description: 'URL del Orchestrator')
        string(name: 'ORCHESTRATOR_TENANT', defaultValue: 'uniruipath', description: 'Tenant del Orchestrator')
        string(name: 'UIPCLI_PATH', defaultValue: 'C:\\Users\\Karla\\Downloads\\UiPath.CLI.Windows.24.10.9050.17872\\tools\\uipcli.exe', description: 'Ruta del UiPath CLI')
        string(name: 'PROJECT_PATH', defaultValue: 'C:\\Users\\Karla\\Downloads\\UnirTes.1.0.0\\content\\project.json', description: 'Ruta del proyecto UiPath')
        string(name: 'OUTPUT_PATH', defaultValue: 'C:\\Users\\Karla\\Documents\\UiPath\\Output', description: 'Ruta para empaquetar la salida')
        string(name: 'UIPCLI_API', defaultValue: 'O8$fh!Dr?4pmEkZ6', description: 'application secret')
        string(name: 'UIPAPP_ID', defaultValue: '822cfea5-c1e9-4c5c-b0aa-56c6802ea52d', description: 'application id')
    }
 
    environment {
        USER_KEY = credentials('UIPATH_USER_KEY')  // Almacén seguro de Jenkins
        ORGANIZATION_ID = credentials('UIPATH_ORGANIZATION_ID')  // Almacén seguro de Jenkins
        GITHUB_KEY = credentials('GITHUB_SECRET')
    }
 
    stages {

        stage('Checkout') {
            steps {
                // Clona el repositorio de GitHub
                git credentialsId: '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', url: 'https://github.com/CrisUbi/UiPathUnir'
            }
        }
 
        stage('Clonar repositorio') {
 
            steps {
 
                git branch: "${BRANCH_NAME}", url: "${GITHUB_URL}"
 
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
 
                "%UIPCLI_PATH%" package deploy "%OUTPUT_PATH%\\UnirTes.1.0.0.nupkg" "${ORCHESTRATOR_URL}" "${ORCHESTRATOR_TENANT}" --applicationId "${UIPAPP_ID}" --applicationSecret "${UIPCLI_API}" --applicationScope "OR.Assets OR.BackgroundTasks OR.Execution OR.Folders OR.Jobs OR.Machines.Read OR.Monitoring OR.Robots.Read OR.Settings.Read OR.TestSetExecutions OR.TestSets OR.TestSetSchedules OR.Users.Read" -o "Unir" --accountForApp "testunir"
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