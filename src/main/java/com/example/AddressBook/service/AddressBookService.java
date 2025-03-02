package com.example.AddressBook.service;

import com.example.AddressBook.dto.ContactDTO;
import com.example.AddressBook.model.Contact;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {

    private final List<Contact> addressBookList = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Contact addEntry(ContactDTO dto) {
        Contact newEntry = new Contact(counter.getAndIncrement(), dto.getName(), dto.getPhone(), dto.getEmail(),dto.getAddress());
        addressBookList.add(newEntry);
        return newEntry;
    }

    public List<Contact> getAllEntries() {
        return addressBookList;
    }

    public Contact getEntryById(Long id) {
        return addressBookList.stream()
                .filter(entry -> entry.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Contact updateEntry(Long id, ContactDTO dto) {
        for (Contact entry : addressBookList) {
            if (entry.getId().equals(id)) {
                entry.setName(dto.getName());
                entry.setPhone(dto.getPhone());
                entry.setEmail(dto.getEmail());
                entry.setAddress(dto.getAddress());
                return entry;
            }
        }
        return null;
    }

    public boolean deleteEntry(Long id) {
        return addressBookList.removeIf(entry -> entry.getId().equals(id));
    }
}
