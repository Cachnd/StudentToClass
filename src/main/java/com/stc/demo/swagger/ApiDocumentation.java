package com.stc.demo.swagger;

import io.swagger.annotations.*;

@SwaggerDefinition(
        info = @Info(
                version = "V1.0.0",
                title = "API Documentation",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS}
)
public interface ApiDocumentation {
}