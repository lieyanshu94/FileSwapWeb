package mao.fileswap.util;

import mao.fileswap.entity.*;
import mao.fileswap.service.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Component
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
        boolean isPro = true;
        switch (fileName) {
            case "FT_TRANSCODE.xlsx":
                return updateOldTrans(is, isPro);
            case "FT_USER_INFO.xlsx":
                return updateOldUsers(is, isPro);
            case "传输代码数据.xlsx":
                return updateNewTrans(is, isPro);
            case "用户数据.xlsx":
                return updateNewUsers(is, isPro);
            default:
                throw new RuntimeException("文件不存在");
        }
    }
    public boolean testFileShunt(String fileName, InputStream is) {
        boolean isPro = false;
        switch (fileName) {
            case "FT_TRANSCODE.xlsx":
                return updateOldTrans(is, isPro);
            case "FT_USER_INFO.xlsx":
                return updateOldUsers(is, isPro);
            case "传输代码数据.xlsx":
                return updateNewTrans(is, isPro);
            case "用户数据.xlsx":
                return updateNewUsers(is, isPro);
            default:
                throw new RuntimeException("文件不存在");
        }
    }
    public boolean onlineListFile(String fileName, InputStream is) {
        List<String> abList = new ArrayList<>();
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream("onlineList.properties")) {
            properties.load(fileInputStream);
            String systemAbs = properties.getProperty("systemAb");
            if (systemAbs != null && systemAbs.length() != 0) {
                abList.addAll(new ArrayList<>(Arrays.asList(systemAbs.split(","))));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Workbook IStoWB(InputStream is) {
        try (Workbook wb = new XSSFWorkbook(is)) {
            return wb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean updateOldTrans(InputStream is, boolean isPro) {
        Workbook wb = IStoWB(is);
        Sheet sheet = wb.getSheetAt(0);
        List<Map<String, String>> list = new ArrayList<>();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            Map<String, String> map = new HashMap<>();
            String tranCode = row.getCell(0).getStringCellValue();
            String putAuth = row.getCell(5).getStringCellValue();
            String getAuth = row.getCell(6).getStringCellValue();
            list.add(makeTransMap(tranCode, getAuth, putAuth));
        }
        if (isPro) {
            List<OldTransPro> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                OldTransPro otp = new OldTransPro();
                String transCode = map.get("transCode");
                String name = map.get("name");
                String getAuth = map.get("getAuth");
                String putAuth = map.get("putAuth");
                l.add(makeTransDao(otp, transCode, name, putAuth, getAuth));
            }
            oldTransProService.saveOrUpdateBatch(l);
        } else {
            List<OldTransTest> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                OldTransTest ott = new OldTransTest();
                String transCode = map.get("transCode");
                String name = map.get("name");
                String getAuth = map.get("getAuth");
                String putAuth = map.get("putAuth");
                l.add(makeTransDao(ott, transCode, name, putAuth, getAuth));
            }
            oldTransTestService.saveOrUpdateBatch(l);

        }
        return false;
    }

    private boolean updateNewTrans(InputStream is, boolean isPro) {
        Workbook wb = IStoWB(is);
        Sheet sheet = wb.getSheetAt(0);
        List<Map<String, String>> list = new ArrayList<>();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            String tranCode = row.getCell(0).getStringCellValue();
            String putAuth = row.getCell(7).getStringCellValue();
            String getAuth = row.getCell(8).getStringCellValue();
            list.add(makeTransMap(tranCode, getAuth, putAuth));
        }
        if (isPro) {
            List<NewTransPro> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                NewTransPro ntp = new NewTransPro();
                String transCode = map.get("transCode");
                String name = map.get("name");
                String getAuth = map.get("getAuth");
                String putAuth = map.get("putAuth");
                l.add(makeTransDao(ntp, transCode, name, putAuth, getAuth));
            }
            newTransProService.saveOrUpdateBatch(l);
        } else {
            List<NewTransTest> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                NewTransTest ntt = new NewTransTest();
                String transCode = map.get("transCode");
                String name = map.get("name");
                String getAuth = map.get("getAuth");
                String putAuth = map.get("putAuth");
                l.add(makeTransDao(ntt, transCode, name, putAuth, getAuth));
            }
            newTransTestService.saveOrUpdateBatch(l);

        }
        return false;
    }


    private boolean updateNewUsers(InputStream is, boolean isPro) {
        Workbook wb = IStoWB(is);
        Sheet sheet = wb.getSheetAt(0);
        List<Map<String, String>> list = new ArrayList<>();
        for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            Map<String, String> map = new HashMap<>();
            String uid = row.getCell(0).getStringCellValue();
            String cnName = row.getCell(2).getStringCellValue();
            map.put("uid", uid);
            map.put("cnName", cnName);
            list.add(map);
        }
        if (isPro) {
            List<NewUsersPro> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                NewUsersPro nup = new NewUsersPro();
                l.add(makeUserDao(nup, map.get("uid"), map.get("cnName")));
            }
            newUsersProService.saveOrUpdateBatch(l);
        } else {
            List<NewUsersTest> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                NewUsersTest nut = new NewUsersTest();
                l.add(makeUserDao(nut, map.get("uid"), map.get("cnName")));
            }
            newUsersTestService.saveOrUpdateBatch(l);

        }
        return false;
    }


    private boolean updateOldUsers(InputStream is, boolean isPro) {
        Workbook wb = IStoWB(is);
        Sheet sheet = wb.getSheetAt(0);
        List<Map<String, String>> list = new ArrayList<>();
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            Map<String, String> map = new HashMap<>();
            String uid = row.getCell(5).getStringCellValue();
            String cnName = row.getCell(1).getStringCellValue();
            map.put("uid", uid);
            map.put("cnName", cnName);
            list.add(map);
        }
        if (isPro) {
            List<OldUsersPro> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                OldUsersPro oup = new OldUsersPro();
                l.add(makeUserDao(oup, map.get("uid"), map.get("cnName")));
            }
            oldUsersProService.saveOrUpdateBatch(l);
        } else {
            List<OldUsersTest> l = new ArrayList<>();
            for (Map<String, String> map : list) {
                OldUsersTest out = new OldUsersTest();
                l.add(makeUserDao(out, map.get("uid"), map.get("cnName")));
            }
            oldUsersTestService.saveOrUpdateBatch(l);

        }
        return false;
    }

    private Map<String, String> makeTransMap(String tranCode, String getAuth, String putAuth) {
        Map<String, String> map = new HashMap<>();
        if (tranCode.equals("666666")) {
            map.put("name", "esb");
            map.put("transCode", tranCode);
            map.put("getAuth", getAuth);
            for (String path : putAuth.split(",")) {
                String name = path.split("=")[0];
                if (!name.equals("esb")) {
                } else {
                    map.put("petAuth", path.substring(path.indexOf("=") + 1, path.length()));
                }
            }
        } else {
            for (String str : putAuth.split(",")) {
                int indexOf = str.indexOf("=");
                if (indexOf == -1) {
                    System.out.println("a");
                }
                String name = str.substring(0, indexOf);
                String path = str.substring(indexOf + 1, str.length());
                if (name.equals("esb")) {
                    continue;
                }
                map.put("name", name);
                map.put("transCode", tranCode);
                map.put("getAuth", getAuth);
                map.put("putAuth", path);
            }
        }
        return map;
    }
    private <T> T makeUserDao(T dao, String uid, String cnName) {
        Class<?> daoClazz = dao.getClass();
        try {
            Method setUid = daoClazz.getDeclaredMethod("setUid", String.class);
            Method setCnName = daoClazz.getDeclaredMethod("setCnName", String.class);
            setUid.invoke(dao, uid);
            setCnName.invoke(dao, cnName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return dao;
    }

    private <T> T makeTransDao(T dao, String transCode, String name, String putAuth, String getAuth) {
        Class<?> daoClazz = dao.getClass();
        try {
            Method setTransCode = daoClazz.getDeclaredMethod("setTransCode", String.class);
            Method setName = daoClazz.getDeclaredMethod("setName", String.class);
            Method setPutAuth = daoClazz.getDeclaredMethod("setPutAuth", String.class);
            Method setGetAuth = daoClazz.getDeclaredMethod("setGetAuth", String.class);
            setTransCode.invoke(dao, transCode);
            setName.invoke(dao, name);
            setPutAuth.invoke(dao, putAuth);
            setGetAuth.invoke(dao, getAuth);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return dao;
    }
}
