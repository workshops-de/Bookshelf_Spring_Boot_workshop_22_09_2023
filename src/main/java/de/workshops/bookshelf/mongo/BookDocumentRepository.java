package de.workshops.bookshelf.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDocumentRepository extends MongoRepository<BookDocument, String> {
}
