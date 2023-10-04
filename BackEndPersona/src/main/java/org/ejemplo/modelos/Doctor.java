package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="doctores")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Doctor extends Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    @Column(name="tuition")
    private Long tuition;
  @Column(name = "idDoctor")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idDoctor;


}
