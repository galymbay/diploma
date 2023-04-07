package kz.galymbay.diploma.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "clothes")
@NoArgsConstructor
public class Clothes {
    @Id
    @Column(name = "clothes_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "clothes_type")
    private String type;
    @Column(name = "clothes_price")
    private int price;
    @Column(name = "clothes_description")
    private String description;
    @Column(name = "clothes_url")
    private String url;
    @JsonIgnore
    @ManyToMany(mappedBy = "clothes")
    private Set<Order> orders = new HashSet<>();

    public Clothes(String type, int price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }
}
