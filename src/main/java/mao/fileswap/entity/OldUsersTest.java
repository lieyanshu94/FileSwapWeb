package mao.fileswap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("OldUsers_Test")
public class OldUsersTest {
    @TableId("uid")
    private String uid;
    @TableField("cnName")
    private String cnName;
}
