#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
#echo "begin copy sql "
#cp ../sql/yizhanshi-system-202403031520.sql ./mysql/db
#cp ../sql/yizhanshi-nacos-config-202403031522.sql ./mysql/db

# copy html
#echo "begin copy html "
#cp -r ../yizhanshi-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy bishe-gateway "
cp ../target/bishe-gateway.jar ./yizhanshi/gateway/jar

echo "begin copy bishe-auth "
cp ../target/bishe-auth.jar ./yizhanshi/auth/jar

echo "begin copy yizhanshi-visual "
cp ../target/bishe-visual-monitor.jar  ./yizhanshi/visual/monitor/jar

echo "begin copy bishe-modules-system "
cp ../target/bishe-modules-system.jar ./yizhanshi/modules/system/jar

echo "begin copy yizhanshi-place "
cp ../target/yizhanshi-place.jar ./yizhanshi/modules/place/jar

echo "begin copy yizhanshi-course "
cp ../target/yizhanshi-course.jar ./yizhanshi/modules/course/jar

echo "begin copy yizhanshi-talent "
cp ../target/yizhanshi-talent.jar ./yizhanshi/modules/talent/jar

echo "begin copy bishe-modules-file "
cp ../target/bishe-modules-file.jar ./yizhanshi/modules/file/jar
#
#echo "begin copy yizhanshi-modules-job "
#cp ../yizhanshi-modules/yizhanshi-job/target/yizhanshi-modules-job.jar ./yizhanshi/modules/job/jar
#
#echo "begin copy yizhanshi-modules-gen "
#cp ../yizhanshi-modules/yizhanshi-gen/target/yizhanshi-modules-gen.jar ./yizhanshi/modules/gen/jar

