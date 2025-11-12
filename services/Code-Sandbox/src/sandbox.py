import subprocess, tempfile
import os
from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class codeRequest(BaseModel):
    code: str
    language: str
    language_docker_image: str
    timeout: int = 5  # default timeout in seconds
    memory:int = 256  # default memory limit in MB

@app.post("/execute")
def execute_code(request: codeRequest):
    with tempfile.TemporaryFile(suffix="."+request.language, mode = "w", delete=False) as temp:
        temp.write(request.code)
        file_path = temp.name

    file_name = os.path.basename(file_path)

    try:
        docker_command = [
            "docker", "run", "--rm",
            "-m", f"{request.memory}m",
            "--cpus=0.5",
            "--mount", f"type=bind,source={file_path},target=/tmp/{file_name},readonly",
            f"{request.language_docker_image}",
            "python", f"/tmp/{file_name}"
        ]
        print("Executing command:", ' '.join(docker_command))
        result = subprocess.run(
            docker_command,
            text=True,
            capture_output=True,
            timeout=request.timeout
        )
        return {
            "stdout": result.stdout, "stderr": result.stderr, "exit_code": result.returncode
        }

    except subprocess.TimeoutExpired as e:
        return {
            "stdout": "error", "stderr": f"Execution timed out: {e}", "exit_code": -1
        }