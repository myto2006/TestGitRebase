在主工程目录下执行以下命令
1.编译打包
gradle build -x test
# 生成image
docker build -t bmc:1.0 .
docker build -t 10.1.234.164:5000/bmc:12.1 .   (每次打镜像版本要+1)
docker build -t 10.248.4.13:5000/billing/baas-bmc:1.8.30 .
docker push 10.1.234.164:5000/bmc:12.1

3. 运行镜像
docker run -d --net=host -e "REST_REGISTRY_ADDR=10.1.130.84:39181" -e "REST_PORT=10774" -e "CONTEXT_PATH=baas-bmc" -e "PROTOCOL=rest" -e "SDK_MODE=1" -e "CCS_NAME=aiopt-baas-bmc" -e "ZK_ADDRESS=10.1.130.84:39181" bmc:1.0
docker run -it --net=host -e "REST_REGISTRY_ADDR=10.1.130.84:39181" -e "REST_PORT=10774" -e "PROTOCOL=rest" -e "SDK_MODE=1" -e "CCS_NAME=aiopt-baas-bmc" -e "ZK_ADDRESS=10.1.130.84:39181" bmc