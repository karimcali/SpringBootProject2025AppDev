$env:JAVA_HOME = "C:\Program Files\Zulu\zulu-21"
$env:MAVEN_HOME = "$env:USERPROFILE\AppData\Local\apache-maven-3.9.6"
$env:Path = "$env:MAVEN_HOME\bin;C:\Program Files\Zulu\zulu-21\bin;" + $env:Path

cd "c:\Users\karim\OneDrive - mycit.ie\Year 4\GenAi_AppDev\space-api"

# Build
Write-Host "Building Spring Boot application..." -ForegroundColor Cyan
mvn clean package -DskipTests

# Run
if ($LASTEXITCODE -eq 0) {
    Write-Host "Build successful! Starting application..." -ForegroundColor Green
    Write-Host "The app will run on: http://localhost:8080" -ForegroundColor Yellow
    Write-Host "Swagger UI: http://localhost:8080/swagger-ui.html" -ForegroundColor Yellow
    Write-Host "H2 Console: http://localhost:8080/h2-console" -ForegroundColor Yellow
    Write-Host "GraphQL: http://localhost:8080/graphql" -ForegroundColor Yellow
    Write-Host ""
    java -jar target/space-api-0.0.1-SNAPSHOT.jar
} else {
    Write-Host "Build failed!" -ForegroundColor Red
    exit 1
}
