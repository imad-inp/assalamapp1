package com.assalam.repository.search;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.assalam.domain.Famille;

/**
 * Spring Data Elasticsearch repository for the Imad entity.
 */
public interface FamilleSearchRepository extends ElasticsearchRepository<Famille, Long> {
}