package mao.fileswap.util;

import mao.fileswap.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ExcelUtil {

    @Autowired
    private NewTransProService newTransProService;
    @Autowired
    private NewTransTestService newTransTestService;
    @Autowired
    private NewUsersProService newUsersProService;
    @Autowired
    private NewUsersTestService newUsersTestService;
    @Autowired
    private OldTransProService oldTransProService;
    @Autowired
    private OldTransTestService oldTransTestService;
    @Autowired
    private OldUsersProService oldUsersProService;
    @Autowired
    private OldUsersTestService oldUsersTestService;

    public boolean proFileShunt(String fileName, InputStream is) {
        System.out.println(fileName);
        boolean isPro = true;
        switch (fileName) {
            case "FT_TRANSCODE.xlsx":
                return updateNewTrans(is,isPro);
            case "FT_USER_INFO.xlsx":
                return updateNewUsers(is,isPro);
            case "传输代码数据.xlsx":
                return updateOldTrans(is,isPro);
            case "用户数据.xlsx":
                return updateOldUsers(is,isPro);
            default:
                throw new RuntimeException("文件不存在");
        }
    }
    public boolean updateNewTrans(InputStream is,boolean isPro){
        if (isPro){

        } else {

        }
        return false;
    }
    public boolean updateOldTrans(InputStream is,boolean isPro){
        if (isPro){

        } else {

        }
        return false;
    }
    public boolean updateNewUsers(InputStream is,boolean isPro){
        if (isPro){

        } else {

        }
        return false;
    }
    public boolean updateOldUsers(InputStream is,boolean isPro){
        if (isPro){

        } else {

        }
        return false;
    }
}
