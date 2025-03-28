package mao.fileswap.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mao.fileswap.dao.OnlineListMapper;
import mao.fileswap.entity.OnlineList;
import mao.fileswap.service.OnlineListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineListServiceImpl extends ServiceImpl<OnlineListMapper, OnlineList> implements OnlineListService {
    @Autowired
    private OnlineListMapper onlineListMapper;
    @Override
    public void saveOnlineList(OnlineList onlineList) {
        super.baseMapper.insert(onlineList);
    }
}
