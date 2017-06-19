package nl.craftsmen.conference;

import io.restassured.RestAssured;

import java.security.KeyStore;
import java.security.KeyStoreException;

import static io.restassured.config.SSLConfig.sslConfig;

public class SslConfig {

    public void setupSSl() throws KeyStoreException {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        RestAssured.config = RestAssured.
                config().
                sslConfig(
                        sslConfig().
                                with().
                                keystoreType("JKS")

                            .and().
                                strictHostnames());
    }
}
