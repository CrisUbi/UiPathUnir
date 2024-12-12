pipeline {
    agent any
    environment { 
        GITHUB_URL = '' 
        COMMIT_MESSAGE = '' 
        BRANCH_NAME = ''
        FLODER_NAME = ''
        PROJECT_NAME = ''
        OUTPUT_PATH = "${env.OUTPUT_PATH}"
        UIPCLI_API = credentials('SECRET_KEY_UIPATH')
        UIPAPP_ID = "${env.UIPAPP_ID}"
        ORCHESTRATOR_URL = "${env.ORCHESTRATOR_URL}"
        ORCHESTRATOR_TENANT = "${env.ORCHESTRATOR_TENANT}"
        UIPCLI_PATH = "${env.UIPCLI_PATH}"
        OUTPUT_PATH = "${env.OUTPUT_PATH}"
        UI_ORGANIZACION = "${env.UI_ORGANIZACION}"
    }
    
    stages {
        stage('Get Commit, Branch, Folder, Project name and Repo URL') {
            steps {
                script {
                    
                    // Obtener la URL del repositorio
                    GITHUB_URL = powershell( script: 'git config --get remote.origin.url', returnStdout: true ).trim()
                    
                    // Obtener el mensaje del último commit
                    COMMIT_MESSAGE = powershell( script: 'git log -1 --pretty=format:"%B"', returnStdout: true ).trim()
                        // Separar el texto por '#'
                    def parts = COMMIT_MESSAGE.split("#", 2) // Limitar a 2 partes
                        // Asignar a variables antes y después del '#'
                    FOLDER_NAME = parts[0]
                    PROJECT_NAME = parts.length > 1 ? parts[1] : "${FOLDER_NAME}"
                    
                    // Obtener la rama que contiene el commit 
                    def commitHash = powershell( script: 'git rev-parse HEAD', returnStdout: true ).trim() 
                    BRANCH_NAME = powershell( script: "git branch -r --contains ${commitHash}", returnStdout: true ).trim()
                    
                    // Comando de PowerShell para eliminar la carpeta si existe 
                    def deleteFolderCommand = """ if (Test-Path -Path '${OUTPUT_PATH}') { Remove-Item -Path '${OUTPUT_PATH}' -Recurse -Force } """ 
                    // Ejecutar el comando de PowerShell 
                    powershell script: deleteFolderCommand, returnStdout: true
                }
            }
        }
        
        stage('Clone repositorio') {
            steps {
                script {
                    bat """
                    git clone ${GITHUB_URL} ${OUTPUT_PATH}
                    """
                }
            }
        }
        
        stage('Empaquetar proyecto UiPath') {
            steps {
                PROJECT_PATH = "${OUTPUT_PATH}\\${PROJECT_NAME}\\project.json"
                bat """
                "${UIPCLI_PATH}" package pack "${PROJECT_PATH}" -o "${OUTPUT_PATH}"
                """
            }
        }
        stage('Print Variables') { 
            steps { 
                echo "Repositorio URL: ${GITHUB_URL}" 
                echo "Último mensaje de commit: ${COMMIT_MESSAGE}" 
                echo "Nombre de la rama actual: ${BRANCH_NAME}" 
                echo "Nombre de la rama actual: ${FOLDER_NAME}" 
                echo "Nombre de la rama actual: ${PROJECT_NAME}" 
            } 
        }
    }
}
