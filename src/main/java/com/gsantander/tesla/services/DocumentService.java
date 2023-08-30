package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslDocument;
import com.gsantander.tesla.repositories.IDocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final IDocumentRepository documentRepository;

    public DocumentService(IDocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional
    public void insertDocument(TslDocument tslDocument) {
        if(this.documentRepository.existsByDescription(tslDocument.getDescription()))
            throw new TslFoundException();
        this.documentRepository.save(tslDocument);
    }

    @Transactional
    @Modifying
    public void updateDocument(TslDocument tslDocument) {
        if(!this.documentRepository.existsById(tslDocument.getIdDocument()))
            throw new TslNotFoundException();
        if(this.documentRepository.existsByDescriptionAndIdDocumentIsNot(tslDocument.getDescription(),tslDocument.getIdDocument()))
            throw new TslFoundException();
        this.documentRepository.save(tslDocument);
    }

    @Transactional
    @Modifying
    public void deleteDocument(Integer id) {
        if(!this.documentRepository.existsById(id))
            throw new TslNotFoundException();
        this.documentRepository.deleteById(id);
    }

    @Transactional
    public List<TslDocument> getDocuments() {
        return this.documentRepository.findAll();
    }

    public TslDocument getDocument(Integer id) {
        Optional<TslDocument> optTsl = this.documentRepository.findById(id);
        if(!optTsl.isPresent())
            throw new TslNotFoundException();
        return optTsl.get();
    }

}