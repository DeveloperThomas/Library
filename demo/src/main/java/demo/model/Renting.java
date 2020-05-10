package demo.model;

import javax.persistence.*;

@Entity
@Table(name = "RENTING")
public class Renting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @ManyToOne
  private User user;

  @ManyToOne
  private Book book;
}
