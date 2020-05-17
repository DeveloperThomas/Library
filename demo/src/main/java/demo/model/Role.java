package demo.model;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(value = EnumType.STRING)
  private RoleName roleName;


  public RoleName getRoleName() {
    return roleName;
  }

  public void setRoleName(RoleName roleName) {
    this.roleName = roleName;
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
