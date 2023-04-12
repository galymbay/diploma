package kz.galymbay.diploma.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
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

    @Transient
    private String confirmPassword;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;
    @Column(name = "is_block")
    private boolean isBlock = true;
    @Column(name = "activation_code")
    private String activationCode;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_role",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Client(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isBlock = true;
    }
}
