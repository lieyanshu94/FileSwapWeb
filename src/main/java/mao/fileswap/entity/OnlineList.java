package mao.fileswap.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("onlineList")
public class OnlineList {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField("listNo")
    private String listNo;
    @TableField("systemAbs")
    private String systemAbs;
}
