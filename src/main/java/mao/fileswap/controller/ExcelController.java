package mao.fileswap.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/excel")
public class ExcelController {
    @RequestMapping("uploadProExcel")
    private void uploadProExcel(@RequestParam("file")MultipartFile file){
        System.out.println(file);
        
    }

}
