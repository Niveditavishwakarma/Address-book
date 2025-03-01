package com.example.AddressBook.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@CrossOrigin(origins = "http://localhost:8080")
public class AddressController {

        private final List<Contact> contacts = new ArrayList<>();
        private long nextId = 1;

        @PostMapping
        public Contact addContact(@RequestBody Contact contact) {
            contact.setId(nextId++);
            contacts.add(contact);
            return contact;
        }

        @GetMapping
        public List<Contact> getAllContacts() {
            return contacts;
        }

        @GetMapping("/{id}")
        public Contact getContactById(@PathVariable Long id) {
            return contacts.stream()
                    .filter(contact -> contact.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        @PutMapping("/{id}")
        public Contact updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
            for (Contact contact : contacts) {
                if (contact.getId().equals(id)) {
                    contact.setName(updatedContact.getName());
                    contact.setPhone(updatedContact.getPhone());
                    contact.setEmail(updatedContact.getEmail());
                    contact.setAddress(updatedContact.getAddress());
                    return contact;
                }
            }
            return null;
        }

        @DeleteMapping("/{id}")
        public String deleteContact(@PathVariable Long id) {
            Optional<Contact> contactToRemove = contacts.stream()
                    .filter(contact -> contact.getId().equals(id))
                    .findFirst();

            contactToRemove.ifPresent(contacts::remove);
            return "Contact deleted successfully";
        }

        static class Contact {
            private Long id;
            private String name;
            private String phone;
            private String email;
            private String address;

            public Long getId() { return id; }
            public void setId(Long id) { this.id = id; }

            public String getName() { return name; }
            public void setName(String name) { this.name = name; }

            public String getPhone() { return phone; }
            public void setPhone(String phone) { this.phone = phone; }

            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }

            public String getAddress() { return address; }
            public void setAddress(String address) { this.address = address; }
        }
    }


