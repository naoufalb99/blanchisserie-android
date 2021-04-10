package iao.master.blanchisserie.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import iao.master.blanchisserie.utils.TimestampConverter;

@Entity(tableName = "commands")
public class Commands {
    public static String STATUS_COMPLETED = "STATUS_COMPLETED";
    public static String STATUS_IN_PROGRESS = "STATUS_IN_PROGRESS";

    public static String SERVICE_WASH_AND_IRON = "SERVICE_WASH_AND_IRON";
    public static String SERVICE_IRONING = "SERVICE_IRONING";
    public static String SERVICE_DRY_CLEANING = "SERVICE_DRY_CLEANING";
    public static String SERVICE_DARNING = "SERVICE_DARNING";

    @PrimaryKey(autoGenerate = true)
    private Long command_id;

    @TypeConverters({TimestampConverter.class})
    private Date created_at;

    private String status;
    private Float total_price;
    private String service;
    private Long client_id;

    public Commands(Date created_at, String status, Float total_price, String service, Long client_id) {
        this.created_at = created_at;
        this.status = status;
        this.total_price = total_price;
        this.service = service;
        this.client_id = client_id;
    }

    public Long getCommand_id() {
        return command_id;
    }

    public void setCommand_id(Long command_id) {
        this.command_id = command_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }
}
