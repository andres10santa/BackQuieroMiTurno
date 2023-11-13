package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="doctores")
@PrimaryKeyJoinColumn(name = "id")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    @Column(name="tuition")
    private Long tuition;
    @Column(name="specialty")
    private String specialty;

    public Doctor(Long id){
      this.id = id;
    }


    //Getter y Setter
  public Long getTuition() {
    return tuition;
  }

  public void setTuition(Long tuition) {
    this.tuition = tuition;
  }

}
