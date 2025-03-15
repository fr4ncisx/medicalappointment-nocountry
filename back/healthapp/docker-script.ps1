function Handle-Error {
    param ([string]$message)
    Write-Host "[ERROR] $message" -ForegroundColor Red
    exit 1
}

function Run-Command {
    param ([string]$command, [string]$stepMessage)
    Write-Host "[INFO] $stepMessage" -ForegroundColor Cyan
    $process = Start-Process -FilePath cmd.exe -ArgumentList "/c $command" -PassThru -Wait

    # Verificar si el proceso fue iniciado correctamente
    if ($process -eq $null) {
        Handle-Error "$stepMessage failed to start. No process created."
    }

    # Leer las salidas estándar y de error, si existen
    $processOutput = $null
    $processError = $null

    # Verificar si hay salida estándar y error
    if ($process.StandardOutput) {
        $processOutput = $process.StandardOutput.ReadToEnd()
    }

    if ($process.StandardError) {
        $processError = $process.StandardError.ReadToEnd()
    }

    # Mostrar la salida completa del comando si la hay
    if ($processOutput) {
        Write-Host "[INFO] Output: $processOutput" -ForegroundColor Green
    }
    if ($processError) {
        Write-Host "[ERROR] Error: $processError" -ForegroundColor Red
    }

    # Verificar el código de salida
    if ($process.ExitCode -ne 0) {
        Handle-Error "$stepMessage failed. Check the logs for details."
    }
}

# Leer la versión desde docker-compose.yml (asumiendo que está bajo el nombre de la imagen)
$composeFile = "docker-compose.yml"

# Buscar la línea que contiene la imagen y extraer la versión
$versionLine = Select-String -Path $composeFile -Pattern "image:\s*(dockerfrancisx/healthapp):([^\s]+)" | ForEach-Object { $_.Line }

# Si se encuentra la línea, extraemos la versión de la imagen
if ($versionLine) {
    # Utilizamos una expresión regular para extraer la versión
    $version = ($versionLine -split ":")[-1].Trim()
    Write-Host "[INFO] Version found: $version" -ForegroundColor Cyan
} else {
    Handle-Error "Version not found in docker-compose.yml"
}

# Borrar todas las imágenes de Docker al inicio
Write-Host "[INFO] Deleting all Docker images..." -ForegroundColor Cyan
$dockerImages = docker images -q
if ($dockerImages) {
    Run-Command "docker rmi -f $dockerImages" "Step 0: Deleting all Docker images"
} else {
    Write-Host "[INFO] No Docker images found to delete." -ForegroundColor Yellow
}

# Paso 1: Ejecutar el build de Maven
Write-Host "[INFO] Step 1: Running Maven build..." -ForegroundColor Cyan
Run-Command "mvn clean install -DskipTests" "Step 1: Running Maven build..."

# Paso 2: Ejecutar Docker Compose
Write-Host "[INFO] Step 2: Running Docker Compose..." -ForegroundColor Cyan
Run-Command "docker-compose up --build -d" "Step 2: Running Docker Compose..."

# Paso 3: Detener y eliminar los contenedores
Write-Host "[INFO] Step 3: Stopping and removing containers..." -ForegroundColor Cyan
Run-Command "docker-compose down -v" "Step 3: Stopping and removing containers..."

# Paso 4: Hacer push de la imagen a Docker Hub
Write-Host "[INFO] Step 4: Pushing image to Docker Hub..." -ForegroundColor Cyan
Run-Command "docker push dockerfrancisx/healthapp:$version" "Step 4: Pushing image to Docker Hub..."

Write-Host "[INFO] El proceso se completó con éxito." -ForegroundColor Cyan

# Pausar hasta que el usuario presione cualquier tecla
Write-Host "Presiona cualquier tecla para salir..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")
