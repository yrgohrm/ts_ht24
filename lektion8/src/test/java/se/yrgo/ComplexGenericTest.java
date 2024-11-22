package se.yrgo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.*;
import org.testcontainers.images.builder.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;

import redis.clients.jedis.*;

@Testcontainers
class ComplexGenericTest {

    private static final int VALKEY_PORT = 6379;

    private static final String VALKEY_CONFIG = """
            bind * -::*
            protected-mode no
            port 6379
            tcp-backlog 511
            timeout 0
            tcp-keepalive 300
            daemonize no
            loglevel debug
            logfile ""
            """;

    @Container
    private GenericContainer<?> valkey = new GenericContainer<>("valkey/valkey:8.0.1")
            .withExposedPorts(VALKEY_PORT) // bind 6379 to a random port on the host
            .withEnv("DEBUG", "1") // set environment variable(s)
            .withSharedMemorySize(500L * 1024 * 1024) // set shared memory size to 500 MB
            .withLogConsumer(c -> System.err.println("[LOG] " + c.getUtf8StringWithoutLineEnding())) // redirect all output
            .withCopyToContainer(Transferable.of(VALKEY_CONFIG), "/valkey.conf") // copy this data first
            .withCommand("valkey-server /valkey.conf"); // use this command instead of the default

    @Test
    void testUsingRedis() {
        try (JedisPooled jedis = new JedisPooled(valkey.getHost(), valkey.getMappedPort(VALKEY_PORT))) {
            final String name = "Bosse Bredsladd";
            jedis.set("employeeOfTheMonth", name);
            assertEquals(name, jedis.get("employeeOfTheMonth"));
        }
    }
}
