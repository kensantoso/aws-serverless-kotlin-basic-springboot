package demo

import com.amazonaws.serverless.exceptions.ContainerInitializationException
import com.amazonaws.serverless.proxy.internal.testutils.Timer
import com.amazonaws.serverless.proxy.model.AwsProxyRequest
import com.amazonaws.serverless.proxy.model.AwsProxyResponse
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import java.io.InputStream
import java.io.OutputStream

class StreamLambdaHandler : RequestStreamHandler {
    init {
        // we enable the timer for debugging. This SHOULD NOT be enabled in production.
        Timer.enable()
    }

    override fun handleRequest(inputStream: InputStream?, outputStream: OutputStream?, context: Context?) {
        handler?.proxyStream(inputStream, outputStream, context)
    }

    companion object {
        private var handler: SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>? = null

        init {
            try {
                handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(
                    Application::class.java
                )

                // we use the onStartup method of the handler to register our custom filter
                handler?.onStartup { servletContext ->
//                    val registration: FilterRegistration.Dynamic = servletContext.addFilter(
//                        "CognitoIdentityFilter",
//                        CognitoIdentityFilter::class.java
//                    )
//                    registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*")
                }
            } catch (e: ContainerInitializationException) {
                // if we fail here. We re-throw the exception to force another cold start
                e.printStackTrace()
                throw RuntimeException("Could not initialize Spring Boot application", e)
            }
        }
    }
}
