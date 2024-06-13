pipeline
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
        
          
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/udaysinghc/rezysaveautomation.git'
                    bat "mvn clean test"
                    
                }
            }
        }
                
        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
            steps{
                echo("deploy to PROD")
            }
        }
  stage('Publish Allure Results') {
            steps {
                script {
                    // Publish Allure results to Jenkins
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
        
    }
}