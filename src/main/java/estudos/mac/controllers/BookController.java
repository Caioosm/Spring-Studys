package estudos.mac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudos.mac.data.DTO.v1.BooksDTO;
import estudos.mac.data.DTO.v1.PersonDTO;
import estudos.mac.services.BookService;
import estudos.mac.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/book/v1")
@Tag(name="Books", description="Endpoints For Managing Books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Finds All Books", description="Finds All Books", tags = {"Books"}, responses={
        @ApiResponse(description="Success", responseCode="200", 
            content={
                @Content(
                    mediaType="application/json",
                    array=@ArraySchema(schema=@Schema(implementation=PersonDTO.class))
                )
            }),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Not Found", responseCode="404", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content)
    })
    public List<BooksDTO> findAll() {
        return bookService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Find Book By ID", description="Finding Book Person", tags={"Books"}, responses={
        @ApiResponse(description="Success", responseCode = "200", 
            content=@Content(mediaType="application/json")
        ),
        @ApiResponse(description="No Content", responseCode="204", content=@Content),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Not Found", responseCode="404", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public BooksDTO findById(@PathVariable(value="id") Long id) {
        return bookService.findById(id);
    }
    
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Create a new Book", description="Creating a one Book", tags={"Books"}, responses={
        @ApiResponse(description="Created", responseCode = "200", 
            content=@Content(mediaType="application/json")
        ),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public BooksDTO createBook(@RequestBody BooksDTO books) {
        return bookService.createBook(books);
    }
    
    @PutMapping(value = "/update", produces={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Update a Book", description="Updating a one Book", tags={"Books"}, responses={
        @ApiResponse(description="Updated", responseCode = "200", 
            content=@Content(mediaType="application/json")
        ),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Not Found", responseCode="404", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public BooksDTO updateBooks(@RequestBody BooksDTO books) {
        return bookService.updateBook(books);
    }
    
    @DeleteMapping(value="/delete/{id}")
    @Operation(summary="Delete a Book", description="Deleting a one Book", tags={"Books"}, responses={
        @ApiResponse(description="Deleted", responseCode = "204", content=@Content),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Not Found", responseCode="404", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public ResponseEntity<?> deleteBookById(@PathVariable(value = "id") Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
