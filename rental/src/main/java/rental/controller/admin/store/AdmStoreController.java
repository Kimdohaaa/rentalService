package rental.controller.admin.store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.jakartaee.commons.lang3.text.StrBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rental.model.dao.admin.AdminDao;
import rental.model.dto.StoreDto;


@WebServlet("/store/info") // http://localhost:8080/rental/store/info
public class AdmStoreController extends HttpServlet {
	
	
	//[1] 등록
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("확인");
		
		//업로드 경로 가져오기 
		
		String uploadPath = req.getServletContext().getRealPath("/upload");
		
		// 해당 경로가 없으면 만들어주기
		File file = new File( uploadPath);
		if (file.exists()) { }
		else {
			 file.mkdir();
		}
		
		//파일 업로드 설정
		DiskFileItemFactory factory = new DiskFileItemFactory(); //업로드 설정 객체
		factory.setRepository(file);//경로설정
		factory.setSizeThreshold(1024*1024*1024); // 용량제한 설정
		factory.setDefaultCharset("UTF-8"); // 한글인코딩
		
		// 서블릿 업로드 객체 대입
		ServletFileUpload fialeUpload = new ServletFileUpload(factory);
		// http 요청 객체 내 데이터 파싱
		String filename = "default.jpg";
		try {
				
				List<FileItem> fileList = fialeUpload.parseRequest(req);
				// 파싱된 자료 반복문으로 조회
				for(FileItem item : fileList) {
					if(item.isFormField()) {}else {
						if (!item.getName().isEmpty()) {
							// 8. uuid 이용한 첨부파일명 조합
							filename = UUID.randomUUID().toString() +"-"+item.getName().replaceAll("-", "_");
							File uplodFile = new File(uploadPath + "/" + filename);
							//지정한 경로에 업로드
							item.write(uplodFile);
						}
					}
				}//for end
				
				// 텍스트로 dto 직접 파싱
				StoreDto storeDto = new StoreDto();
				storeDto.setSmno(fileList.get(0).getString());
				storeDto.setSaddr(fileList.get(1).getString());
				storeDto.setSname(fileList.get(2).getString());
				storeDto.setLon(fileList.get(4).getString());
				storeDto.setLat(fileList.get(5).getString());
				storeDto.setSimg(filename);
				System.out.println(storeDto);
			
				boolean result = AdminDao.getInstance().name(storeDto);
				
				resp.setContentType("application/json");
				resp.getWriter().print(result);
			
		} catch (Exception e) {
			System.out.println("업로드 실패 : + e");
		}
		
		
	}// dP end
	
	
	//[2] 전체게시물 조회 메소드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doget ok");
		
		ArrayList<StoreDto> result = AdminDao.getInstance().findAll(); // 결과받기
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(result);
		
		// http response
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
		
	}
	
	
	//[ 3 ] 가맹점 수정
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("stor put ok");
		
		ObjectMapper mapper = new ObjectMapper();
		StoreDto storeDto = mapper.readValue( req.getReader() , StoreDto.class);
		
		boolean result = AdminDao.getInstance().update(storeDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	
	
	

	
	
	
}// class end
