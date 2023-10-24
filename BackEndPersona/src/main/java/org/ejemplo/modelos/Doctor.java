package org.ejemplo.modelos;

import jakarta.persistence.*;


@Entity
@Table(name="doctores")
@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    @Column(name="tuition")
    private Long tuition;
    @Column(name="specialty")
    private String specialty;


    //Getter y Setter
  public Long getTuition() {
    return tuition;
  }

  public void setTuition(Long tuition) {
    this.tuition = tuition;
  }

}
