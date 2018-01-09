package com.CM.pojo;

public class FileCategory {
    private Integer id;

    private Integer filetypeId;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFiletypeId() {
        return filetypeId;
    }

    public void setFiletypeId(Integer filetypeId) {
        this.filetypeId = filetypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}