package kz.galymbay.diploma.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_clothes",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "clothes_id"))
    private Set<Clothes> clothes = new HashSet<>();

    @Column(name = "order_status")
    private String status;

    public Order(Client client, String status) {
        this.client = client;
        this.status = status;
    }
}
