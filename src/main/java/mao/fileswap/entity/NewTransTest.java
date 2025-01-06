package mao.fileswap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("NewTrans_Test")
public class NewTransTest {
    @TableId("transCode")
    private Integer transCode;
    @TableField("name")
    private String name;
    @TableField("putAuth")
    private String putAuth;
    @TableField("getAuth")
    private String getAuth;
}
