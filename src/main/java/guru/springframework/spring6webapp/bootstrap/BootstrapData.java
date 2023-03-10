package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData  implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric=new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd=new Book();
        ddd.setTitle("Domain driven design");
        ddd.setIsbn("1224");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved= bookRepository.save(ddd);

        Author rod=new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB=new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("122656774");

        Author rodSaved= authorRepository.save(rod);
        Book noEJBSaved= bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        Publisher publisher1=new Publisher();
        publisher1.setPublisherName("Stuart White");
        publisher1.setAdress("Street2");
        publisher1.setCity("New York");
        publisher1.setState("USA");
        publisher1.setZip("567-67");
        Publisher publisher1Saved=publisherRepository.save(publisher1);

        Publisher publisher2=new Publisher();
        publisher2.setPublisherName("John Black");
        publisher2.setAdress("Street6");
        publisher2.setCity("New York");
        publisher2.setState("USA");
        publisher2.setZip("567-67");
        Publisher publisher2Saved=publisherRepository.save(publisher2);

        dddSaved.setPublisher(publisher2Saved); // setting saved publisher to the book, creating relations
        noEJBSaved.setPublisher(publisher2);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: "+ authorRepository.count());
        System.out.println("Book count: "+ bookRepository.count());

        System.out.println("Publisher count: "+publisherRepository.count());






    }
}
