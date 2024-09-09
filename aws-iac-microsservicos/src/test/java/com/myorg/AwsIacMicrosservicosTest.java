// package com.myorg;

// import software.amazon.awscdk.App;
// import software.amazon.awscdk.assertions.Template;
// import java.io.IOException;

// import java.util.HashMap;

// import org.junit.jupiter.api.Test;

// example test. To run these tests, uncomment this file, along with the
// example resource in java/src/main/java/com/myorg/AwsIacMicrosservicosStack.java
// public class AwsIacMicrosservicosTest {

//     @Test
//     public void testStack() throws IOException {
//         App app = new App();
//         AwsIacMicrosservicosStack stack = new AwsIacMicrosservicosStack(app, "test");

//         Template template = Template.fromStack(stack);

//         template.hasResourceProperties("AWS::SQS::Queue", new HashMap<String, Number>() {{
//           put("VisibilityTimeout", 300);
//         }});
//     }
// }
