package kz.galymbay.diploma.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@ToString
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_firstname")
    private String firstName;
    @Column(name = "client_lastname")
    private String lastName;
    @Column(name = "client_email")
    private String email;
    @Column(name = "client_phone")
    private String phoneNumber;
    @Column(name = "client_password")
    private String password;
    @Column(name = "client_city")
    private String city;
    @Column(name = "client_country")
    private String country;

    @Transient
    private String confirmPassword;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    @JsonIgnore
    @Column(name = "is_block")
    private boolean isBlock;

    @JsonIgnore
    @Column(name = "activation_code")
    private String activationCode;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_role",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Basket basket;

    public Client(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
