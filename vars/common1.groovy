def call(String stageName){
  
  if ("${stageName}" == "Build")
     {
       sh "mvn clean package"
     }
  else if ("${stageName}" == "SonarQube Report")
     {
       sh "mvn clean sonar:sonar"
     }
  else if ("${stageName}" == "Upload Into Nexus")
     {
       sh "mvn clean deploy"
     }
  else if ("${stageName}" == "Deploy to UAT")
     {
       deploy adapters: [tomcat9(credentialsId: 'tomcat-cred', path: '', url: 'http://18.237.101.171:8080/')], contextPath: null, war: 'target/*.war'
     }
  else if ("${stageName}" == "Approval Gate")
     {
      timeout(time:5, unit:'DAYS'){
      input message: 'Approval for Production'
      }
     }
  else if ("${stageName}" == "Deploy to Prod")
     {
       deploy adapters: [tomcat9(credentialsId: 'tomcat-cred', path: '', url: 'http://18.237.101.171:8080/')], contextPath: null, war: 'target/*.war'
     }
}
