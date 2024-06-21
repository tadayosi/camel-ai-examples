// camel-k: language=java dependency=ai.djl.pytorch:pytorch-model-zoo:0.28.0 dependency=ai.djl.pytorch:pytorch-engine:0.28.0
// dependency=ai.djl.pytorch:pytorch-jni:2.3.0-0.28.0 dependency=ai.djl.pytorch:pytorch-native-cpu:2.3.0

import org.apache.camel.builder.RouteBuilder;
import java.io.File;
import java.util.*;

public class ssd extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:data/objects?noop=true&recursive=true&include=.*\\.jpg")
            .convertBodyTo(byte[].class)
            .to("djl:cv/object_detection?artifactId=ssd")
            .log("${headers.camelFileName} => ${body}");
    }
}
