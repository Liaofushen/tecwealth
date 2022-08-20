FROM centos
RUN yum -y install wget \
    && wget -O redis.tar.gz "http://download.redis.io/releases/redis-5.0.4.tar.gz" \
    && tar -xvf redis.tar.gz
    && cd ./redis
    && make
    && cd ..
CMD ["sh", "-c", "./redis/redis-server"]