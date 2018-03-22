package com.neweagle.api.comm.plugin.mybatisplus;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体父类
 */
@Data
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID , 这里故意演示注解可以无
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 租户ID  saas应用启用
     */
    //private Long tenantId;
    /**
     * 创建时间
      */
    @TableField(value="create_time",fill = FieldFill.INSERT)
    private Long createTime;
    /**
     * 更新时间
     */
    @TableField(value="update_time",fill = FieldFill.UPDATE)
    private Long updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
