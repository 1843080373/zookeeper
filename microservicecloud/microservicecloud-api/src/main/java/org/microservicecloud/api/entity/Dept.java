package org.microservicecloud.api.entity;

public class Dept {
    private Integer id;

    private String depName;

    private String dbName;

    public Dept() {
		super();
	}

	public Dept(String depName, String dbName) {
		super();
		this.depName = depName;
		this.dbName = dbName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName == null ? null : depName.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }
}