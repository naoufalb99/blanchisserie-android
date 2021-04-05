package iao.master.blanchisserie.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import iao.master.blanchisserie.models.Settings;

@Dao
public interface SettingsDao {
    @Query("INSERT INTO settings (nomBlanchisserie, adresse, email, numeroTelephone) VALUES (:nom,:adresse,:email,:numero)")
    public void insertSetting(String nom, String adresse,String email, String numero);

    @Update
    public void updateSetting(Settings setting);

    @Query("DELETE FROM settings")
    public void deleteSettings();

    @Query("SELECT * FROM settings")
    public Settings[] getAllSettings();
}
