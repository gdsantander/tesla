package com.gsantander.tesla.controllers;

import com.gsantander.tesla.model.TslDocument;
import com.gsantander.tesla.services.DocumentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/global/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public void insertDocument(@RequestBody @Valid TslDocument tslDocument) {
        this.documentService.insertDocument(tslDocument);
    }

    @PutMapping
    public void updateDocument(@RequestBody @Valid TslDocument tslDocument) {
        this.documentService.updateDocument(tslDocument);
    }

    @DeleteMapping("/{id}")
    public void Document(@PathVariable Integer id) {
        this.documentService.deleteDocument(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TslDocument>> getDocuments() {
        List<TslDocument> tlsDocuments = this.documentService.getDocuments();
        return new ResponseEntity<>(tlsDocuments,HttpStatus.OK);
    }

    @GetMapping("/document/{id}")
    public ResponseEntity<TslDocument> getDocument(@PathVariable Integer id) {
        TslDocument tslDocument = this.documentService.getDocument(id);
        return new ResponseEntity<>(tslDocument,HttpStatus.OK);
    }

}
