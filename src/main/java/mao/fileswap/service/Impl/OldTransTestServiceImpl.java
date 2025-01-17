package mao.fileswap.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.fileswap.dao.OldTransTestMapper;
import mao.fileswap.entity.OldTransTest;
import mao.fileswap.service.OldTransTestService;
import org.springframework.stereotype.Service;

@Service
public class OldTransTestServiceImpl extends ServiceImpl<OldTransTestMapper, OldTransTest> implements OldTransTestService {
}
