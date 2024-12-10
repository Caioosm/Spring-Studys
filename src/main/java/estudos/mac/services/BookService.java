package estudos.mac.services;

// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import estudos.mac.Entities.Books;
import estudos.mac.Exceptions.RequiredObjectIsNullException;
import estudos.mac.Exceptions.ResourcenNotFoundException;
import estudos.mac.controllers.BookController;
import estudos.mac.data.DTO.v1.BooksDTO;
import estudos.mac.mapper.DozerMapper;
import estudos.mac.repositories.BookRepository;

@Service
public class BookService {
    
    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    BookRepository bookRepository;

    public List<BooksDTO> findAll(){
        logger.info("finding all books!");

        var books = DozerMapper.parseListObjects(bookRepository.findAll(), BooksDTO.class);

        books.stream().forEach(b -> {
            try {
                b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel());
            } catch (Exception e) {
            }
        });

        return books;
    }

    public BooksDTO findById(Long id){
        logger.info("Finding one book");

        var book = bookRepository.findById(id).orElseThrow(() -> new ResourcenNotFoundException("No record found for this ID!"));
        var dto = DozerMapper.parseObject(book, BooksDTO.class);

        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return dto;
    }

    public BooksDTO createBook(BooksDTO books){
        if (books == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one book!");

        var book = DozerMapper.parseObject(books, Books.class);
        var dto = DozerMapper.parseObject(bookRepository.save(book), BooksDTO.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());
        
        return dto;
    }

    public BooksDTO updateBook(BooksDTO books){
        if(books == null) throw new RequiredObjectIsNullException();
        
        logger.info("Updating a Book!");

        Books bookEntity = bookRepository.findById(books.getKey()).orElseThrow(() -> new ResourcenNotFoundException("No records found for this ID!"));

        bookEntity.setAuthor(books.getAuthor());
        bookEntity.setLaunchDate(books.getLaunchDate());
        bookEntity.setPrice(books.getPrice());
        bookEntity.setTitle(books.getTitle());

        var dto = DozerMapper.parseObject(bookRepository.save(bookEntity), BooksDTO.class);

        dto.add(linkTo(methodOn(BookController.class).findById(dto.getKey())).withSelfRel());

        return dto;
    }

    public void deleteBook(Long id){
        logger.info("Deleting a Book!");

        var book = bookRepository.findById(id).orElseThrow(() -> new ResourcenNotFoundException("No Records Found For This ID!"));

        bookRepository.delete(book);
    }
}
