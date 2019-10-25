package edu.asu.diging.rcn.uploader.core.service.impl;

import javax.annotation.PostConstruct;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@PropertySource("classpath:/config.properties")
@Transactional
public class HibernateIndexer {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${_hibernate_index_on_startup}")
    private String indexOnStartup;
    
    @Autowired
    private JpaTransactionManager transactionManager;

    @PostConstruct
    public void init() throws InterruptedException {
        if (indexOnStartup.equals("true")) {
            logger.info("Indexing data...");
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(transactionManager.getEntityManagerFactory().createEntityManager());
            fullTextEntityManager.createIndexer().startAndWait();
            logger.info("Indexing data... done.");
        } else {
            logger.info("Data indexing skipped.");
        }
    }
}
