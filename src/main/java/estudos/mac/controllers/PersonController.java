package estudos.mac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import estudos.mac.data.DTO.v1.PersonDTO;
import estudos.mac.services.PersonService;
import estudos.mac.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name="Persons", description = "Endpoints For Managing Peoples")
public class PersonController {

    // private final AtomicLong counter = new AtomicLong();
    @Autowired
    private PersonService personService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Finds All Persons", description="Finds All Peaples", tags = {"Persons"}, responses={
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
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Find Person By ID", description="Finding one Person", tags={"Persons"}, responses={
        @ApiResponse(description="Success", responseCode = "200", 
            content=@Content(mediaType="application/json")
        ),
        @ApiResponse(description="No Content", responseCode="204", content=@Content),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Not Found", responseCode="404", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public PersonDTO findById(@PathVariable(value = "id") Long id) throws Exception {
        return personService.findById(id);
    }
    
    @PostMapping(value="/create", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Create a new Person", description="Creating a one Person", tags={"Persons"}, responses={
        @ApiResponse(description="Created", responseCode = "200", 
            content=@Content(mediaType="application/json")
        ),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public PersonDTO createPerson(@RequestBody PersonDTO person) {
        return personService.createPerson(person);
    }
    
    @PutMapping(value="/update", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary="Update a Person", description="Updating a one Person", tags={"Persons"}, responses={
        @ApiResponse(description="Updated", responseCode = "200", 
            content=@Content(mediaType="application/json")
        ),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Not Found", responseCode="404", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public PersonDTO updatePerson(@RequestBody PersonDTO person) {
        return personService.updatePerson(person);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary="Delete a Person", description="Deleting a one Person", tags={"Persons"}, responses={
        @ApiResponse(description="Deleted", responseCode = "204", content=@Content),
        @ApiResponse(description="Bad Request", responseCode="400", content=@Content),
        @ApiResponse(description="Unauthorized", responseCode="401", content=@Content),
        @ApiResponse(description="Not Found", responseCode="404", content=@Content),
        @ApiResponse(description="Internal Error", responseCode="500", content=@Content),
    })
    public ResponseEntity<?> deletePersonByiD(@PathVariable(value = "id") Long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

}
