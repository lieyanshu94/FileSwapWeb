package mao.fileswap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("NewUsers_Test")
public class NewUsersTest {
    @TableId("uid")
    private String uid;
    @TableField("cnName")
    private String cnName;
}
