package lbe.test.iot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import com.google.common.io.Resources;

import software.amazon.awssdk.crt.io.ClientBootstrap;
import software.amazon.awssdk.crt.io.EventLoopGroup;
import software.amazon.awssdk.crt.io.HostResolver;
import software.amazon.awssdk.crt.mqtt.MqttClientConnection;
import software.amazon.awssdk.iot.AwsIotMqttConnectionBuilder;

/**
 * @author lbeuster
 */
class AwsShutdownTest {

    private static final String CERT_RESOURCE = "client.crt";
    private static final String KEY_RESOURCE = "client.key";
    private static final String ENDPOINT = "endpoint-not-set";

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

        String certificate = loadResourceAsString(CERT_RESOURCE);
        String privateKey = loadResourceAsString(KEY_RESOURCE);
        EventLoopGroup eventLoopGroup = new EventLoopGroup(1);
        ClientBootstrap clientBootstrap = new ClientBootstrap(eventLoopGroup, new HostResolver(eventLoopGroup));
        // ClientBootstrap clientBootstrap = new ClientBootstrap(null, null);
        String clientId = "aws-iot-139";

        // @formatter:off
        AwsIotMqttConnectionBuilder builder = AwsIotMqttConnectionBuilder
            .newMtlsBuilder(certificate, privateKey)
            .withEndpoint(ENDPOINT)
            .withClientId(clientId)
            .withBootstrap(clientBootstrap);
        // @formatter:on

        // mqttClient.connect();
        MqttClientConnection mqttClient = builder.build();
        builder.close();
        return mqttClient;
    }

    private String loadResourceAsString(String resource) throws IOException {
        return Resources.asCharSource(Resources.getResource(resource), StandardCharsets.UTF_8).read();
    }
}
