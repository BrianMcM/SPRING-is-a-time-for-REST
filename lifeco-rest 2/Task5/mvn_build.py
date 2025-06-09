import subprocess
name = "Maven builder"
result = subprocess.run(
    ["mvn", "clean" ,"package"],
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