## Docker

* 生成 `image`

```
mvn spring-boot:build-image
```

* 删除 `image`

```
docker rmi <imageID>
```

## Kubernetes

* 应用 yaml 文件

```
kubectl apply -f myweb-deploy.yaml
```

* 查看信息

```
kubectl get all
```

* port-forward

```
kubectl port-forward pod/kubernetes-demo-d844455bf-4cfl7 8080:8080
```

* 删除

```
kubectl delete all --all --all-namespaces
```