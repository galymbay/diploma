package kz.galymbay.diploma.service;

import kz.galymbay.diploma.configuration.ElasticSearchDto;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {
    private final ElasticsearchOperations elasticsearchOperations;

    public LogService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<ElasticSearchDto> getLogs() {
        Criteria criteria = new Criteria();
        Query query = new CriteriaQuery(criteria);
        List<ElasticSearchDto> result = new ArrayList<>();
        List<ElasticSearchDto> resultList = new ArrayList<>();
        int total = 0;
        IndexCoordinates indexCoordinates = IndexCoordinates.of("springboot-test-log");
        SearchHits<ElasticSearchDto> elasticSearchDtoSearchHits = elasticsearchOperations.search(query, ElasticSearchDto.class, indexCoordinates);
        result.addAll(elasticSearchDtoSearchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList()));

        resultList.add(result.get(1));
        resultList.add(result.get(2));
        resultList.add(result.get(3));
        resultList.add(result.get(4));
        resultList.add(result.get(5));
        resultList.add(result.get(6));
        resultList.add(result.get(7));
        resultList.add(result.get(8));
        resultList.add(result.get(9));
        resultList.add(result.get(10));
        resultList.add(result.get(11));

        return resultList;
    }
}
