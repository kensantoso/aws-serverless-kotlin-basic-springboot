# Kotlin Serverless Spring Boot 3 example
A basic kotlin serverless springboot example based on the [message app tutorial](https://kotlinlang.org/docs/jvm-get-started-spring-boot.html).
This is combined with the aws example for springboot in lambda located [here](https://github.com/aws/serverless-java-container/tree/main/aws-serverless-java-container-springboot3).

Using this pattern of a monolith lambda means you have easy access to local development, debugging, testing in the springboot ecosystem as well as being able to take advantage of all the serverless benefits. 
If you decide to, you can simply migrate to whatever infrastructure you like(ECS, k8s etc) with ease. 

The application can be deployed in an AWS account using the [Serverless Application Model](https://github.com/awslabs/serverless-application-model). The `template.yml` file in the root folder contains the application definition.

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Gradle](https://gradle.org/) or [Maven](https://maven.apache.org/)

## Local Development

Simply run `gradle bootRun`. A `requests.http` file is included to test locally.  


## Deployment Options

This example uses lambda snapstart which in my very limited testing reduced the initial cold start from 5s to around 1.6s. Subsequent executions were around the 100ms range. 
I've also included the tomcat app server in `build.gradle.kts` for local development, the current deployment size is still relatively small but please remove it if not needed. 


## Deployment
In a shell, navigate to the sample's folder and use the SAM CLI to build a deployable package
```
$ sam build
```

This command compiles the application and prepares a deployment package in the `.aws-sam` sub-directory.

To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl`, `requests.http` or a web browser to make a call to the URL

```
...
---------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
---------------------------------------------------------------------------------------------------------
MessageApi - URL for application            https://xxxxxxxxxx.execute-api.${AWS::Region}.amazonaws.com/
---------------------------------------------------------------------------------------------------------

$ curl https://xxxxxxxxxx.execute-api.${AWS::Region}.amazonaws.com/
```