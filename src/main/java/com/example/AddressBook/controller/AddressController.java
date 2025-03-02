package com.example.AddressBook.controller;
import java.util.List;
import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.model.Contact;
import com.example.AddressBook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:8080")
public class AddressController {

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping("/add")
    public ResponseEntity<Contact> addContact(@RequestBody ContactDTO addressBookDto) {
        Contact newContact = addressBookService.addEntry(addressBookDto);
        return new ResponseEntity<>(newContact, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = addressBookService.getAllEntries();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable long id) {
        Contact contact = addressBookService.getEntryById(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable long id, @RequestBody ContactDTO addressBookDto) {
        Contact updatedContact = addressBookService.updateEntry(id, addressBookDto);
        return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable long id) {
        addressBookService.deleteEntry(id);
        return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
    }
}




