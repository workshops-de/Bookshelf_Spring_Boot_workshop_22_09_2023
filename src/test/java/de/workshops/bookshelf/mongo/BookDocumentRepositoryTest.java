package de.workshops.bookshelf.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataMongoTest(properties = "logging.level.org.springframework.data.mongodb.core.MongoTemplate=debug")
class BookDocumentRepositoryTest {

    @Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6");

    @Autowired
    BookDocumentRepository bookDocumentRepository;

    @Test
    void count_should_work() {
        long count = bookDocumentRepository.count();
        assertThat(count).isGreaterThanOrEqualTo(0);
    }
}