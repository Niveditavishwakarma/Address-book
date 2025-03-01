package com.example.AddressBook.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:8080")
public class AddressController {

    private final List<Contact> contacts = new ArrayList<>();
    private long nextId = 1;

    private Contact convertToModel(ContactDTO contactDTO) {
        return new Contact(contactDTO.getName(), contactDTO.getPhone(), contactDTO.getEmail(), "Default Address");
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        contact.setId(nextId++);
        contacts.add(contact);
        return ResponseEntity.ok(contact);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contacts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        return contact.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setName(updatedContact.getName());
                contact.setPhone(updatedContact.getPhone());
                contact.setEmail(updatedContact.getEmail());
                contact.setAddress(updatedContact.getAddress());
                return ResponseEntity.ok(contact);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        Optional<Contact> contactToRemove = contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();

        if (contactToRemove.isPresent()) {
            contacts.remove(contactToRemove.get());
            return ResponseEntity.ok("Contact deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}




