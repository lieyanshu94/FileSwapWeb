package mao.fileswap.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.fileswap.dao.NewTransTestMapper;
import mao.fileswap.entity.NewTransTest;
import mao.fileswap.service.NewTransTestService;
import org.springframework.stereotype.Service;

@Service
public class NewTransTestServiceImpl extends ServiceImpl<NewTransTestMapper, NewTransTest> implements NewTransTestService {
}
