# Kubernetes for the busy Neo4j Developer Workshop

Workshop notes - https://hackmd.io/@ojhughes/k8s-for-busy-neo4j-dev

## How to start the Dropwizard application

1. Run `./mvnw clean package` to build your container
1. Start the application
    - **With Docker**: `docker run --rm -p 8080:8080 dropwizard-jib-example:1`
    - **Without Docker**: `./mvnw exec:java`
1. Check that your application is running at http://localhost:8080

## Health Check

See your application's health at http://localhost:8080/admin/healthcheck

## More information

Learn [more about Jib](https://github.com/GoogleContainerTools/jib).

Learn [more about Dropwizard](https://dropwizard.io).

