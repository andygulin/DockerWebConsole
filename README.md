# docker-web-console

### 安装docker
	yum update
	
	vi /etc/yum.repos.d/docker.repo
	
	[dockerrepo]
	name=Docker Repository
	baseurl=https://yum.dockerproject.org/repo/main/centos/7/
	enabled=1
	gpgcheck=1
	gpgkey=https://yum.dockerproject.org/gpg
	
	yum install docker-engine docker-registry
	
	启动
	systemctl start docker
	
### 支持TCP
	vi /lib/systemd/system/docker.service
	修改
	ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
	
	重新启动docker
	systemctl daemon-reload
	systemctl restart docker
	
	验证tcp
	docker -H tcp://127.0.0.1:2375 version
	
	关闭防火墙
	systemctl stop firewalld