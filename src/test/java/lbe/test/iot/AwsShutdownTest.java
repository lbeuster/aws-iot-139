package lbe.test.iot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import software.amazon.awssdk.crt.io.ClientBootstrap;
import software.amazon.awssdk.crt.mqtt.MqttClientConnection;
import software.amazon.awssdk.iot.AwsIotMqttConnectionBuilder;

/**
 * @author lbeuster
 */
class AwsShutdownTest {

    private static final String ENDPOINT = "doesnt.matter";

    @BeforeAll
    static void setUpAwsLogging() {
        System.setProperty("aws.crt.debugnative", "true");
        System.setProperty("aws.crt.log.destination", "File");
        System.setProperty("aws.crt.log.level", "Trace");
        System.setProperty("aws.crt.log.filename", "aws-iot.log");
    }

    /**
     *
     */
    @Test
    void test() throws Exception {
        MqttClientConnection client = newClient();
        client.close();
    }

    private MqttClientConnection newClient() throws Exception {

        ClientBootstrap clientBootstrap = new ClientBootstrap(null, null);
        String clientId = "aws-iot-139";

        // @formatter:off
        AwsIotMqttConnectionBuilder builder = AwsIotMqttConnectionBuilder
            .newDefaultBuilder()
            .withEndpoint(ENDPOINT)
            .withClientId(clientId)
            .withBootstrap(clientBootstrap);
        // @formatter:on

        // mqttClient.connect();
        MqttClientConnection mqttClient = builder.build();
        builder.close();
        return mqttClient;
    }
}
