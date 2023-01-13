# docker-web-console

### Install Redis
```
......
```

### Install Docker
```
yum install docker
```

Start Docker
```
systemctl start docker
```

### Support TCP
```
vi /lib/systemd/system/docker.service
```
Modify
```
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
```

Restart Docker
```
systemctl daemon-reload
systemctl restart docker
```

Verify Tcp
```
docker -H tcp://127.0.0.1:2375 version
```

Close firewalld
```
systemctl stop firewalld
```