package com.fashion.mjysite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fashion.mjysite.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class FileController {
//    @Autowired
//    private FileService fileService;
    @Value("${file.upload.localpath}")
    private String fileUploadpath;
    @RequestMapping("/uploadfaceimg")
    @ResponseBody
    public JSONObject uploadFaceImg(@RequestParam("file")MultipartFile file) throws IOException {
        // TODO Auto-generated method stub
        Date date = new Date();
        JSONObject result = new JSONObject();
        //List<Upfile> list=new ArrayList<Upfile>();
        File classPath = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!file.isEmpty())
        {
            String filename=file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String strDate = sdf.format(date);
//            String fileno=strDate+filename.hashCode();
            String prefix=filename.substring(filename.lastIndexOf("."));
            String serverPath = classPath+fileUploadpath+strDate +prefix;
            System.out.println(serverPath);
            File f=new File(serverPath);
            if(!f.getParentFile().exists())
            {
                f.getParentFile().mkdirs();
                System.out.println(f.getAbsolutePath());
            }
            if(f.exists()){
                f=new File(fileUploadpath+strDate+"-1"+prefix);
                System.out.println(f.getAbsolutePath());
            }
            file.transferTo(f);
            //upfileMapper.saveFile(enfile);
            //list.add(upfile);
            //throw new NullPointerException("±àºÅÎª¿Õ");
            result.put("data", "success");
            result.put("url",f.getAbsolutePath());
        }
        return result;
    }


}
