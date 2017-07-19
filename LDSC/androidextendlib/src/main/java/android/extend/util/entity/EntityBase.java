package android.extend.util.entity;


import android.extend.data.sqlite.annotation.Column;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * 实体类的基类
 * Created by huangjianxionh on 2016/11/16.
 */
public class EntityBase implements Serializable {
    @Column(isPrimaryKey = true)
    private String id;           //主键
    @Column("created")
    private Date created;     //创建时间
    @Column("modified")
    private Date modified;     //最后更新时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public EntityBase() {
        this.markNew();
    }

    /**
     * 标记为创建新对象
     */
    public void markNew() {
        this.id = UUID.randomUUID().toString();
        this.created = modified = new Date();
    }

    /**
     * 标记更新
     */
    public void markUpdated() {
        this.modified = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBase entityBase = (EntityBase) o;

        return id.equals(entityBase.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
