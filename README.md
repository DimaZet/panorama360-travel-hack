```
docker run --rm --name mysql \
    -e MYSQL_ROOT_PASSWORD=admin \
    -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin \
    -p 3306:3306 -d mysql
```
