import subprocess

services = {
    "broker": "./broker",
    "auldfellas": "./auldfellas",
    "dodgygeezers": "./dodgygeezers",
    "girlsallowed": "./girlsallowed"
}

for name, path in services.items():
    print(f"Building {name} from {path}...")
    result = subprocess.run(
        ["docker", "build", "-t", f"{name}:latest", path],
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True
    )
    # noinspection PyInterpreter
    if result.returncode == 0:
        print(result.stdout)
        print(f"{name} built successfully.\n")
    else:
        print(f"Failed to build {name}:\n{result.stderr}")
        break