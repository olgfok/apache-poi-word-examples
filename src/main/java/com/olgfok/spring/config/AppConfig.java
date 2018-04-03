package com.olgfok.spring.config;


import com.olgfok.word.creators.FootnoteCreator;
import com.olgfok.word.creators.ListCreator;
import com.olgfok.word.creators.TableCreator;
import com.olgfok.word.helpers.CreateWordDocumentHelper;
import com.olgfok.word.services.CreateWordDocumentServiceImpl;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public XWPFDocument xwpfDocument() {
        return new XWPFDocument();
    }

    @Bean
    public CreateWordDocumentServiceImpl createWordDocumentImpl() {
        return new CreateWordDocumentServiceImpl();
    }

    @Bean
    public CreateWordDocumentHelper createWordDocumentHelper() {
        return new CreateWordDocumentHelper();
    }

    @Bean
    public TableCreator tableCreator() {
        return new TableCreator();
    }

    @Bean
    public FootnoteCreator footnoteCreator() {
        return new FootnoteCreator();
    }

    @Bean
    public ListCreator listCreator() {
        return new ListCreator();
    }

}

