package org.ejemplo.modelos;

import jakarta.persistence.*;


@Entity
@Table(name="pacientes")
@PrimaryKeyJoinColumn(name = "id")
public class Paciente extends Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Clave primaria autogenerada
    @Column(name="socialWork")
    private String socialWork;
    @Column(name="codeSocialWork")
    private String codeSocialWork;


    //Getter y Setter
    public String getsocialWork() {
        return socialWork;
    }

    public void setsocialWork(String socialWork) {
        this.socialWork = socialWork;
    }

    public String getcodeSocialWork() {
        return codeSocialWork;
    }

    public void setcodeSocialWork(String codeSocialWork) {
        this.codeSocialWork = codeSocialWork;
    }
}
