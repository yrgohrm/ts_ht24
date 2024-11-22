package se.yrgo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;
import redis.clients.jedis.*;

@Testcontainers
class GenericTest {

    @Container
    private GenericContainer<?> valkey = new GenericContainer<>("valkey/valkey:8.0.1").withExposedPorts(6379);

    @Test
    void testUsingRedis() {
        try (JedisPooled jedis = new JedisPooled(valkey.getHost(), valkey.getMappedPort(6379))) {
            final String name = "Bosse Bredsladd";
            jedis.set("employeeOfTheMonth", name);
            assertEquals(name, jedis.get("employeeOfTheMonth"));
        }
    }
}
