package iao.master.blanchisserie.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings")
public class Settings {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String nomBlanchisserie;
    private String adresse;
    private String email;
    private String numeroTelephone;

    public Settings(String nomBlanchisserie, String adresse, String email, String numeroTelephone) {
        this.nomBlanchisserie = nomBlanchisserie;
        this.adresse = adresse;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
    }

    public String getNomBlanchisserie() {
        return nomBlanchisserie;
    }

    public void setNomBlanchisserie(String nomBlanchisserie) {
        this.nomBlanchisserie = nomBlanchisserie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
}
