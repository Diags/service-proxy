# Service proxy 
# Pré-requis
* spring boot
* spring-cloud-starter-netflix-zuul
# Configuration
on ServiceProxyApplication add this annotations 
* EnableZuulProxy
* EnableDiscoveryClient
On application.properties add this 
* server.port=port number
* spring.application.name=your proxy name "this will register this name in eureka"
