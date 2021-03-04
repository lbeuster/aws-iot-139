package lbe.test.iot;

import org.junit.jupiter.api.Test;

import software.amazon.awssdk.crt.io.ClientBootstrap;
import software.amazon.awssdk.crt.mqtt.MqttClientConnection;
import software.amazon.awssdk.iot.AwsIotMqttConnectionBuilder;

/**
 * @author lbeuster
 */
class AwsShutdownTest {

    private static final String ENDPOINT = "doesnt.matter";

    /**
     *
     */
    @Test
    void test() throws Exception {
        MqttClientConnection client = newClient();
        // client.connect().get();
        // client.publish(new MqttMessage("test", "MESSAGE".getBytes(), QualityOfService.AT_LEAST_ONCE));
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
