# 需要注意
# 1.使用 dockerfile-maven-plugin 构建镜像需要把Dockerfile文件放在项目根目录下
# 2.使用 docker-maven-plugin 构建镜像，可以通过 <dockerDirectory> 标签指定 Dockerfile 文件地址
# 推荐使用1，2官方已经不再维护了

# 基础镜像
FROM java:8

# 添加参数
#ARG JAR_FILE

# 添加项目包至镜像中，并命名
# 先将项目打成 jar 包，再通过 Dockerfile 构建镜像
# 直接通过 Dockerfile build 的镜像启动时会提示无法访问 jar 包，原因是路径下没有此包
ADD target/blog.jar /blog.jar

# 对外端口
EXPOSE 8080

# 启动
ENTRYPOINT ["java", "-jar","/blog.jar"]
