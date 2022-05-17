

### 环境变量

```shell
export HIVE_HOME=/data/bigdata/hive
export HIVE2MYSQL_HOME=/data/bigdata/hive2mysql
export PATH=$PATH:$HIVE2MYSQL_HOME/bin
```

### 目录结构

hive2mysql

--bin

----hive2mysql.sh

--hive2mysql.jar



hive2mysql.sh

```shell
#!/bin/bash
java -cp $HIVE_HOME/lib/*:$HIVE2MYSQL_HOME/hive2mysql.jar com.example.Main $1
```

配置文件

```properties
hiveConnect=jdbc:hive2://xxx:10000/xxx
hiveUsername=
hivePassword=
hiveTable=xxx
hiveWhere=xxx
mysqlConnect=jdbc:mysql://xxx:3306/xxx
mysqlUsername=xxx
mysqlPassword=xxx
mysqlTable=xxx
columns=xxx,xxx
orderByColumns=xxx
rows=1000
```



### 调用

hive2mysql.sh xxx.properties

