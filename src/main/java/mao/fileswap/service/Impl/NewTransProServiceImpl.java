package mao.fileswap.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.fileswap.dao.NewTransProMapper;
import mao.fileswap.entity.NewTransPro;
import mao.fileswap.service.NewTransProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewTransProServiceImpl extends ServiceImpl<NewTransProMapper, NewTransPro> implements NewTransProService {
}
