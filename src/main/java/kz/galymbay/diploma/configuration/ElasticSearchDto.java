package kz.galymbay.diploma.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "springboot-test-log*")
public class ElasticSearchDto {
    @Id
    private String id;
    @Field(name = "@timestamp", type = FieldType.Text)
    private String timestamp;
    @Field(type = FieldType.Text)
    private String level;
    @Field(type = FieldType.Text)
    private String thread;
    @Field(name = "@version", type = FieldType.Text)
    private String version;
    @Field(type = FieldType.Text)
    private String logger_name;
    @Field(type = FieldType.Text)
    private String message;
}
