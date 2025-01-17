package mao.fileswap.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.fileswap.dao.OldUsersProMapper;
import mao.fileswap.entity.OldUsersPro;
import mao.fileswap.service.OldUsersProService;
import org.springframework.stereotype.Service;

@Service
public class OldUsersProServiceImpl extends ServiceImpl<OldUsersProMapper, OldUsersPro> implements OldUsersProService {
}
