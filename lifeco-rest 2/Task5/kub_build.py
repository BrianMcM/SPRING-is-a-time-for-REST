import subprocess

dir = "./k8s"
folders = ["auldfellas","dodgygeezers","girlsallowed","broker","monitoring"]
k8s_manifests = {"auldfellas":["auldfellas-deployment.yaml","auldfellas-service.yaml"],
                 "dodgygeezers":["dodgygeezers-deployment.yaml","dodgygeezers-service.yaml"],
                 "girlsallowed":["girlsallowed-deployment.yaml","girlsallowed-service.yaml"],
                 "broker":["broker-deployment.yaml","broker-service.yaml"],
                 "monitoring":["prometheus-config.yaml","prometheus-deployment.yaml","prometheus-service.yaml"]
                 }
for folder in folders:
    for filename in k8s_manifests[folder]:
        manifest = f"{dir}/{folder}/{filename}"
        # print(manifest)
        print(f"Applying Kubernetes manifest: {manifest}")
        result = subprocess.run(["kubectl", "apply", "-f", manifest], capture_output=True, text=True)
        if result.returncode == 0:
            print(result.stdout)
            print(f"Applied {manifest}")
        else:
            print(f"Failed to apply {manifest}:\n{result.stderr}")
            exit(1)

#
# # Step 2: Apply Kubernetes YAMLs
# for manifest in k8s_manifests:
#     print(f"Applying Kubernetes manifest: {manifest}")
#     result = subprocess.run(["kubectl", "apply", "-f", manifest], capture_output=True, text=True)
#     if result.returncode == 0:
#         print(f"Applied {manifest}")
#     else:
#         print(f"Failed to apply {manifest}:\n{result.stderr}")
#         exit(1)