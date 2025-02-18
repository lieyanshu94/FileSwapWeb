package mao.fileswap.controller;

import com.baomidou.mybatisplus.extension.api.R;
import mao.fileswap.service.NewTransProService;
import mao.fileswap.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelUtil excelUtil;

    @PostMapping("uploadProExcels")
    @ResponseBody
    private R uploadProExcels(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            try (InputStream is = file.getInputStream();) {
                excelUtil.proFileShunt(name, is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return R.ok("success");
    }

    @RequestMapping("uploadTestExcels")
    @ResponseBody
    private R uploadTestExcels(@RequestParam("files") MultipartFile[] files) {
        for (MultipartFile file : files) {
            String name = file.getOriginalFilename();
            try (InputStream is = file.getInputStream();) {
                excelUtil.testFileShunt(name, is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return R.ok("success");

    }

    @RequestMapping("uploadInventory")
    @ResponseBody
    private void uploadInventory(@RequestParam("file") MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            String name = file.getOriginalFilename();
            excelUtil.onlineListFile(name,is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(file);
    }

}
