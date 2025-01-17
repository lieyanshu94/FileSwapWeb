package mao.fileswap.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.fileswap.dao.NewUsersTestMapper;
import mao.fileswap.entity.NewUsersTest;
import mao.fileswap.service.NewUsersTestService;
import org.springframework.stereotype.Service;

@Service
public class NewUsersTestServiceImpl extends ServiceImpl<NewUsersTestMapper, NewUsersTest> implements NewUsersTestService {
}
