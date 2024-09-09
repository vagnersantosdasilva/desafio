package com.myorg;

import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class AwsIacMicrosservicosStack extends Stack {
    public AwsIacMicrosservicosStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public AwsIacMicrosservicosStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "AwsIacMicrosservicosQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();
        // Cria uma VPC
        Vpc vpc = new Vpc(this, "MyVpc");

        // Cria um cluster ECS dentro da VPC
        Cluster cluster = Cluster.Builder.create(this, "EcsCluster")
                .vpc(vpc)
                .build();

        // Cria um banco de dados MySQL no RDS
        DatabaseInstance database = DatabaseInstance.Builder.create(this, "MySQLDatabase")
                .engine(DatabaseInstanceEngine.mysql())
                .vpc(vpc)
                .credentials(Credentials.fromGeneratedSecret("admin")) // Senha gerada automaticamente
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE2, InstanceSize.SMALL))
                .allocatedStorage(20)
                .build();

        // Criar mais componentes como as Tasks do ECS para os microsserviços
                .build());

        app.synth();

        TaskDefinition taskDefinition = TaskDefinition.Builder.create(this, "AppTaskDefinition")
                .compatibility(Compatibility.FARGATE)
                .cpu("512")
                .memoryMiB("1024")
                .build();

        ContainerDefinition pedidoProdutoContainer = taskDefinition.addContainer("PedidoProdutoContainer", ContainerDefinitionOptions.builder()
                .image(ContainerImage.fromRegistry("amazoncorretto:17-alpine-jdk"))
                .memoryLimitMiB(512)
                .cpu(256)
                .build());

        pedidoProdutoContainer.addPortMappings(PortMapping.builder()
                .containerPort(8081)
                .hostPort(8081)
                .build());

        ContainerDefinition pagamentoContainer = taskDefinition.addContainer("PagamentoContainer", ContainerDefinitionOptions.builder()
                .image(ContainerImage.fromRegistry("amazoncorretto:17-alpine-jdk"))
                .memoryLimitMiB(512)
                .cpu(256)
                .build());

        pagamentoContainer.addPortMappings(PortMapping.builder()
                .containerPort(8082)
                .hostPort(8082)
                .build());

        Instance rabbitMqInstance = Instance.Builder.create(this, "RabbitMQInstance")
                .instanceType(InstanceType.of(InstanceClass.BURSTABLE2, InstanceSize.MICRO))
                .machineImage(MachineImage.latestAmazonLinux())
                .vpc(vpc)
                .keyName("your-key-pair") // Nome do par de chaves para SSH
                .build();
    }
}
