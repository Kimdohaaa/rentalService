package rental.controller.clean;

import rental.model.dto.PageDto;

public class Pagination {
	// 페이지네이션 계산을 처리하는 메소드
    public static PageDto getPageDto(int page, int display, int totalSize, int btnSize) {
        
        // 총 페이지 계산
        int totalPage = (totalSize % display == 0) ? totalSize / display : totalSize / display + 1;
        
        // 버튼의 시작과 끝 번호 계산
        int startBtn = ((page - 1) / btnSize) * btnSize + 1;
        int endBtn = startBtn + (btnSize - 1);
        if (endBtn > totalPage) endBtn = totalPage;
        
        // PageDto 생성 및 설정
        PageDto pageDto = new PageDto();
        pageDto.setTotalCount(totalSize);
        pageDto.setPage(page);
        pageDto.setTotalpage(totalPage);
        pageDto.setStartbtn(startBtn);
        pageDto.setEndbtn(endBtn);
        pageDto.setStartRow((page - 1) * display);
        
        return pageDto;
    }
}
