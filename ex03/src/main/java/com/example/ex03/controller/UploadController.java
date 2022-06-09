package com.example.ex03.controller;
/*
문제점 및 해결 방안
1. 동일한 이름으로 파일이 업로드 되었을 때 기존 파일이 사라지는 문제
2. 이미지 파일의 경우 원본 파일의 용량이 클 때 썸네일 이미지로 생성해야 하는 문제
3. 이미지 파일과 일반 파일을 구분해서 다운로드 혹은 페이지에서 조회할 수 있도록 처리하는 문제
4. 첨부파일 공격에 대비하기 위한 업로드 파일의 확장자 제한
* * */

import com.example.ex03.controller.domain.vo.FileVO;
import lombok.experimental.PackagePrivate;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/upload/*")
public class UploadController {
    @GetMapping("/uploadForm")
    public void uploadForm() { log.info("upload form");}

    @PostMapping("/uploadFormAction")
    public void upload(MultipartFile[] uploadFiles) throws IOException { // form 태그 name과 동일할 것
        String uploadFolder = "C:/upload";
        for(MultipartFile file : uploadFiles) {
            log.info("----------");
            log.info("Upload File Name : " + file.getOriginalFilename());
            log.info("Upload File Size : " + file.getSize());

            File saveFile = new File(uploadFolder, file.getOriginalFilename());
            file.transferTo(saveFile);
        }
    }

    @GetMapping("/uploadAjax")
    public void uploadAjax() { log.info("upload Ajax");}

    @PostMapping("/uploadAjaxAction")
    @ResponseBody
    public List<FileVO> uploadAjaxAction(MultipartFile[] uploadFile) throws IOException {
        String uploadFolder = "C:/upload";
        ArrayList<FileVO> files = new ArrayList();
//        yyyy/MM/dd
        File uploadPath = new File(uploadFolder, getFolder());
        if(!uploadPath.exists()) {uploadPath.mkdirs();}

        for(MultipartFile file : uploadFile){
            FileVO fileVO = new FileVO(); // 화면으로 데이터를 보내주기 위함. for문안에 써주어야 함
            // 매번 새로운 객체를 꽂아주려고!
            // 위에서 썼던거 또 하면 불안하니까
            String uploadFileName = file.getOriginalFilename();

//            UUID
            /*
            네트워크 상에서 각각의 객체들을 식별하기 위해서 사용되었다.
            중복될 가능성이 거의 없다고 인정되기 때문에 많이 사용된다.
            * */

            UUID uuid = UUID.randomUUID();
            uploadFileName = uuid.toString()+"_"+uploadFileName;
            fileVO.setFileName(uploadFileName);
            fileVO.setUuid(uuid.toString());
            fileVO.setUploadPath(getFolder());
            /*
            유유아이디가 클라이언트에 보이면 안돼서 유유아이디만 분리해야됨
            * */


            log.info("--------------------------------");
            log.info("Upload File Name : " + uploadFileName);
            log.info("Upload File Size : " + file.getSize());

            File saveFile = new File(uploadPath, uploadFileName);
            file.transferTo(saveFile);

            if(checkImageType(saveFile)) {
                FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName)); // 텍스트 파일은 버퍼, 이미지 파일은 스트림을 씀. 둘다 버퍼를 쓰지만 클래스 구현 형식이 다를 뿐
                Thumbnailator.createThumbnail(file.getInputStream(), thumbnail, 100, 100); // 컴파일러가 해석하는 순간 해당 경로에 올라감
                // close를 안 해주면 삭제나 수정할 때 파일 열려있다고 안 된다고 함. 그러니 close로 닫아줘야한다.
                thumbnail.close(); // close 메서드가 플러쉬?를 써준다.
                fileVO.setImage(true);
            }
            files.add(fileVO);
        }
        return files;
    }

    @GetMapping("/display") // display쪽으로 요청을 보낸다. html 의 src 쪽에서 요청을 한다.
    @ResponseBody
    public byte[] getFile(String fileName) throws IOException {
        File file = new File("C:/upload", fileName);
        return FileCopyUtils.copyToByteArray(file);
    }

    private boolean checkImageType(File file) throws IOException {
        String contentType = Files.probeContentType(file.toPath());
        return contentType.startsWith("image"); // 혹시 contentType이 image라는 이름으로 시작되니?
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return sdf.format(date);
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName) throws UnsupportedEncodingException { // 헤더에 담는 과정
        Resource resource = new FileSystemResource("C:/upload/" + fileName);
        HttpHeaders header = new HttpHeaders();
        String name = resource.getFilename();
        name = name.substring(name.indexOf("_")+1);
        header.add("Content-Disposition", "attachment;filename=" + new String(name.getBytes("UTF-8"), "ISO-8859-1")); //
        // 사용자가 다운로드 할때 무조건 이 이름으로 하도록 설정해는 것 (new String ( 이 부분 . getBytes())
        return new ResponseEntity<>(resource, header, HttpStatus.OK);
    }
}




