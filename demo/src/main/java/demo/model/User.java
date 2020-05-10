package demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;

  public Long getId() {
        return id;
    }

  public void setId(Long id) {
        this.id = id;
    }

  public String getUsername() {
        return username;
    }

  public void setUsername(String username) {
        this.username = username;
    }

  public String getPassword() {
        return password;
    }

  public void setPassword(String password) {
        this.password = password;
    }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

//  @ManyToMany(mappedBy = "users")
//  private Set<Book> books = new HashSet<>();
//
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Set<Renting> renterRentingSet = new HashSet<>();
}
