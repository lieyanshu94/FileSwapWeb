package mao.fileswap.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.fileswap.dao.NewUsersProMapper;
import mao.fileswap.entity.NewUsersPro;
import mao.fileswap.service.NewUsersProService;
import org.springframework.stereotype.Service;

@Service
public class NewUsersProServiceImpl extends ServiceImpl<NewUsersProMapper, NewUsersPro> implements NewUsersProService {
}
