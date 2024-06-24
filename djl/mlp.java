// camel-k: language=java dependency=ai.djl:model-zoo:0.28.0 dependency=ai.djl.pytorch:pytorch-engine:0.28.0
// dependency=ai.djl.pytorch:pytorch-jni:2.3.0-0.28.0 dependency=ai.djl.pytorch:pytorch-native-cpu:2.3.0

import org.apache.camel.builder.RouteBuilder;
import java.io.File;
import java.util.*;

public class mlp extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        var num = new Random(System.currentTimeMillis()).nextInt(10);
        fromF("file:data/mnist/%s?noop=true&recursive=true&include=.*\\.png", num)
            .convertBodyTo(byte[].class)
            .to("djl:cv/image_classification?artifactId=mlp")
            .log("${headers.camelFileName} => ${body.best.className}");
    }
}
