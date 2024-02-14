#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20230706.sql ./mysql/db
cp ../sql/ry_config_20220929.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../yizhanshi-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy yizhanshi-gateway "
cp ../yizhanshi-gateway/target/yizhanshi-gateway.jar ./ruoyi/gateway/jar

echo "begin copy yizhanshi-auth "
cp ../yizhanshi-auth/target/yizhanshi-auth.jar ./ruoyi/auth/jar

echo "begin copy yizhanshi-visual "
cp ../yizhanshi-visual/yizhanshi-monitor/target/yizhanshi-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy yizhanshi-modules-system "
cp ../yizhanshi-modules/yizhanshi-system/target/yizhanshi-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy yizhanshi-modules-file "
cp ../yizhanshi-modules/yizhanshi-file/target/yizhanshi-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy yizhanshi-modules-job "
cp ../yizhanshi-modules/yizhanshi-job/target/yizhanshi-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy yizhanshi-modules-gen "
cp ../yizhanshi-modules/yizhanshi-gen/target/yizhanshi-modules-gen.jar ./ruoyi/modules/gen/jar

