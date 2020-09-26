package com.mall.upload.images.controller;

import com.mall.common.vo.ResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/*
 *   作者：官宣轩
 *   日期：2020-09-25
 */
@CrossOrigin
@RequestMapping("/uploadImages")
@RestController
public class UploadImagesController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "图片上传接口" , notes = "添加商品时上传图片的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的文件", required = true, type = "MultipartFile"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO uploadImg(MultipartFile file, @RequestHeader(required = true) String token) {
        System.out.println(file);
        System.out.println("上传方法被触发");
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        System.out.println(fileName);
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect("47.100.38.50",21);
            boolean state = ftpClient.login("springcloud", "55TCLEmeAb3Sk8RZ");
            int replyCode = ftpClient.getReplyCode();
            System.out.println("replyCode:"+replyCode);
            // 如果响应码在200到299之间，表示与FTP站点的连接是成功的
            if (FTPReply.isPositiveCompletion(replyCode)) {
                // 设置编码UTF-8
                ftpClient.setControlEncoding("UTF-8");
                // 设置被动模式（腾讯云必须添加，阿里云测试时不用添加，其它云待测试
//                ftpClient.enterLocalPassiveMode();

                // a.设置接受文件类型为二进制文件
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                // b.在服务器上创建images文件夹
                //ftpClient.makeDirectory("images");
                // c.切换进入到images文件夹
                ftpClient.changeWorkingDirectory("/www/wwwroot/upload.52bess.com/images");
                // 将文件上传到ftp服务器
                InputStream inputStream = file.getInputStream();
                boolean b = ftpClient.storeFile(fileName, inputStream);
                System.out.println("文件上传结果"+b);

                inputStream.close();
                // 2.退出登录
                ftpClient.logout();
                return new ResultVO(0,"success","https://upload.52bess.com/images/"+fileName);
            } else {
                System.out.println("上传失败了");
                return new ResultVO(0,"fail","http://39.99.143.143/wfx/imgs/fail.png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultVO(0,"fail","http://39.99.143.143/wfx/imgs/fail.png");
    }

}
