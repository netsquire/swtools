package utils;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;

public class ConnectToRedis {

    public static void main(String[] args) {
        RedisClient redisClient = new RedisClient(RedisURI.create("redis://password@host:port"));
        RedisConnection<String, String> connection = redisClient.connect();

        System.out.println("Connected to Redis");

        connection.close();
        redisClient.shutdown();
    }
}