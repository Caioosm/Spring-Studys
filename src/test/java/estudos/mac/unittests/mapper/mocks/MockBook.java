package estudos.mac.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import estudos.mac.Entities.Books;
import estudos.mac.data.DTO.v1.BooksDTO;


public class MockBook {


    public Books mockEntity() {
        return mockEntity(0);
    }
    
    public BooksDTO mockVO() {
        return mockVO(0);
    }
    
    public List<Books> mockEntityList() {
        @SuppressWarnings("Convert2Diamond")
        List<Books> books = new ArrayList<Books>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BooksDTO> mockVOList() {
        List<BooksDTO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Books mockEntity(Integer number) {
        Books book = new Books();
        book.setId(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

    public BooksDTO mockVO(Integer number) {
        BooksDTO book = new BooksDTO();
        book.setKey(number.longValue());
        book.setAuthor("Some Author" + number);
        book.setLaunchDate(new Date());
        book.setPrice(25D);
        book.setTitle("Some Title" + number);
        return book;
    }

}
