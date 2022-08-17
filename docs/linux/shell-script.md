# 进程查询

## pid

ps -ef | grep 进程名字 lsof -i:端口号

## port

netstat -nltp | grep 3003

## path

ll /proc/PID

# 解压缩

## 压缩

```shell
tar -cvf jpg.tar *.jpg #将目录里所有jpg文件打包成tar.jpg
tar -zf jpg.tar.gz *.jpg #将目录里所有jpg文件打包成jpg.tar后，并且将其用gzip压缩，生成一个gzip压缩过的包，命名为jpg.tar.gz
tar -cjf jpg.tar.bz2 *.jpg #将目录里所有jpg文件打包成jpg.tar后，并且将其用bzip2压缩，生成一个bzip2压缩过的包，命名为jpg.tar.bz2
tar -cZf jpg.tar.Z *.jpg #将目录里所有jpg文件打包成jpg.tar后，并且将其用compress压缩，生成一个uncompress压缩过的包，命名为jpg.tar.Z
rar a jpg.rar *.jpg #rar格式的压缩，需要先下载rar for linux
zip jpg.zip *.jpg #zip格式的压缩，需要先下载zip for linux
```

## 解压

```shell
tar -xvf file.tar #解压 tar包
tar -xzvf file.tar.gz #解压tar.gz
tar -xjvf file.tar.bz2 #解压 tar.bz2
tar -xZvf file.tar.Z #解压tar.Z
unrar e file.rar #解压rar
unzip file.zip #解压zip

```

# 文件

## 文件排序

```shell
du -sh * | sort -nr | head
du -s * | sort -nr | tail #选出排在后面的10个。
du  -h --max-depth=1| sort -nr | head
```