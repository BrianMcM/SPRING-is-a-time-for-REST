import subprocess

# Kubernetes YAML files to apply
k8s_manifests = [
    "auldfellas-deployment.yaml",
    "auldfellas-service.yaml",
    "broker-deployment.yaml",
    "broker-service.yaml",
    "dodgygeezers-deployment.yaml",
    "dodgygeezers-service.yaml",
    "girlsallowed-deployment.yaml",
    "girlsallowed-service.yaml"
]

# Step 2: Apply Kubernetes YAMLs
for manifest in k8s_manifests:
    print(f"Applying Kubernetes manifest: {manifest}")
    result = subprocess.run(["kubectl", "apply", "-f", manifest], capture_output=True, text=True)
    if result.returncode == 0:
        print(f"Applied {manifest}")
    else:
        print(f"Failed to apply {manifest}:\n{result.stderr}")
        exit(1)